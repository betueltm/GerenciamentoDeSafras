package br.edu.unoesc.ejb.eao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.edu.unoesc.common.PrecoSacaExterior;
import br.edu.unoesc.ejb.jpa.util.GenericEAO;

public class PrecoSacaExteriorEAO extends GenericEAO<PrecoSacaExterior> {
	
	public List<PrecoSacaExterior> buscarTodosPorTipoCultura(Long idTipoCultura) {
		StringBuilder sb = new StringBuilder()
				.append("select obj from PrecoSacaExterior obj ")
				.append(" where obj.tipoCultura.idTipoCultura = :idTipoCultura ")
				.append(" order by obj.mes");
		
		TypedQuery<PrecoSacaExterior> query = createTypedQuery(sb).setParameter("idTipoCultura", idTipoCultura);
		
		return query.getResultList();
	}

}
