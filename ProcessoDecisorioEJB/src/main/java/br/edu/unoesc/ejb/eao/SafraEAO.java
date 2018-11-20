package br.edu.unoesc.ejb.eao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.edu.unoesc.common.Safra;
import br.edu.unoesc.ejb.jpa.util.GenericEAO;

public class SafraEAO extends GenericEAO<Safra> {

	public List<Safra> buscarTodosPorCultura(Long id) {
		StringBuilder sb = new StringBuilder()
				.append("select obj from Safra obj ")
				.append(" where obj.tipoCultura.idTipoCultura = :idTipoCultura ")
				.append(" order by obj.dataColheira desc");

		TypedQuery<Safra> query = createTypedQuery(sb).setParameter("idTipoCultura", id);

		return query.getResultList();
	}

	public Safra buscarUltimaSafraValida() {
		StringBuilder sb = new StringBuilder()
				.append("select obj from Safra obj ")
				.append(" order by obj.dataColheira desc");
		
		TypedQuery<Safra> query = createTypedQuery(sb).setMaxResults(1);
		
		return getSingleResult(query);
	}

}
