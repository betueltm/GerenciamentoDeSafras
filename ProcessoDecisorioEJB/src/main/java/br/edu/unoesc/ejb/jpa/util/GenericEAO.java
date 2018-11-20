package br.edu.unoesc.ejb.jpa.util;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.unoesc.common.entity.util.AbstractEntity;
import br.edu.unoesc.common.entity.util.EnumEntityState;

public class GenericEAO <T extends AbstractEntity>{

	private EntityManager entityManager;
	private Class<T> entityClass;

	public GenericEAO() {
		entityManager = getEntityManagerWildFly();
		entityClass = getClassTypedOnClassParameter(getClass());
	}
	
	public void save(T entity) {
		EnumEntityState entityState = entity.getEntityState();
		
		switch (entityState) {
		case NEW:{
			entityManager.persist(entity);
		}break;
		case MODIFIED:{
			entityManager.merge(entity);
		}break;
		case DELETED:{
			entityManager.remove(entity);
		}break;
		default:
			break;
		}
	}

	public T find(Long id) {
		return entityManager.find(entityClass, id);
	}
	
	public List<T> findAll() {
		
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		 CriteriaQuery<T> q = cb.createQuery(entityClass);
		 Root<T> c = q.from(entityClass);
		 
		 q.select(c);
		 
		 return entityManager.createQuery(q).getResultList();
	}
	
	public <E> TypedQuery<E> createTypedQuery(StringBuilder qlString, Class<E> clazz){
		return createTypedQuery(qlString.toString(), clazz);
	}
	
	public <E> TypedQuery<E> createTypedQuery(String qlString, Class<E> clazz){
		return entityManager.createQuery(qlString, clazz);
	}
	
	public TypedQuery<T> createTypedQuery(StringBuilder stringBuilder){
		return createTypedQuery(stringBuilder.toString());
	}
	
	public TypedQuery<T> createTypedQuery(String qlString){
		return createTypedQuery(qlString, entityClass);
	}
	
	public T getSingleResult(TypedQuery<T> typedQuery) {
		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}catch (NonUniqueResultException e) {
			throw new RuntimeException("Foi encontrado mais de um resultado para a classe " + entityClass.getSimpleName());
		}
	}
	
	public List<T> getResultList(TypedQuery<T> typedQuery) {
		try {
			return typedQuery.getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}
	
	private EntityManager getEntityManagerWildFly() {
		try {
			return (EntityManager) new InitialContext().lookup("java:app/EntityManager");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public synchronized static <T> Class<T> getClassTypedOnClassParameter(Class<?> clazz) {
		return (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
