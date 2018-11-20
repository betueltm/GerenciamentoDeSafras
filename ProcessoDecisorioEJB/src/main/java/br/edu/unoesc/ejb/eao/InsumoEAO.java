package br.edu.unoesc.ejb.eao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.edu.unoesc.common.Insumo;
import br.edu.unoesc.ejb.jpa.util.GenericEAO;

public class InsumoEAO extends GenericEAO<Insumo> {

	public List<Insumo> buscarTodosPorCultura(Long id) {
		StringBuilder sb = new StringBuilder()
				.append("select obj from Insumo obj ")
				.append(" where obj.tipoCultura.idTipoCultura = :idTipoCultura ");

		TypedQuery<Insumo> query = createTypedQuery(sb).setParameter("idTipoCultura", id);

		return query.getResultList();
	}

}
