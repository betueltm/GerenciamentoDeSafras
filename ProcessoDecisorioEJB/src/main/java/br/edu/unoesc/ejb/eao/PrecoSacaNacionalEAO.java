package br.edu.unoesc.ejb.eao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.edu.unoesc.common.PrecoSacaNacional;
import br.edu.unoesc.ejb.jpa.util.GenericEAO;

public class PrecoSacaNacionalEAO extends GenericEAO<PrecoSacaNacional> {
	
	public List<PrecoSacaNacional> buscarTodosPorTipoCultura(Long idTipoCultura) {
		StringBuilder sb = new StringBuilder()
				.append("select obj from PrecoSacaNacional obj ")
				.append(" where obj.tipoCultura.idTipoCultura = :idTipoCultura ")
				.append(" order by obj.mes");
		
		TypedQuery<PrecoSacaNacional> query = createTypedQuery(sb).setParameter("idTipoCultura", idTipoCultura);
		
		return query.getResultList();
	}

	public PrecoSacaNacional buscarUltimaCotacaoPorCultura(Long id) {
		StringBuilder sb = new StringBuilder()
				.append("select obj from PrecoSacaNacional obj ")
				.append(" where obj.tipoCultura.idTipoCultura = :idTipoCultura ")
				.append(" order by obj.mes desc");
		
		TypedQuery<PrecoSacaNacional> query = createTypedQuery(sb).setParameter("idTipoCultura", id).setMaxResults(1);
		
		return getSingleResult(query);
	}

}
