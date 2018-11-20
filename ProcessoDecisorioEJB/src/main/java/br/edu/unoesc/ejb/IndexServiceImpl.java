package br.edu.unoesc.ejb;

import java.util.Date;

import javax.ejb.Stateless;

import br.edu.unoesc.api.IndexService;
import br.edu.unoesc.common.Teste;
import br.edu.unoesc.common.entity.util.EnumEntityState;
import br.edu.unoesc.ejb.eao.TesteEAO;

@Stateless
public class IndexServiceImpl implements IndexService {
	
	private TesteEAO testeEAO = new TesteEAO();
	
	@Override
	public void salvarTeste(Date date) {
		
		Teste teste = new Teste();
		teste.setEntityState(EnumEntityState.NEW);
		teste.setDate(date);
		testeEAO.save(teste);
	}

	@Override
	public void alterarTeste(Date date) {
		Teste teste = testeEAO.find(1L);
		teste.setEntityState(EnumEntityState.MODIFIED);
		teste.setDate(date);
		testeEAO.save(teste);
		
	}

	@Override
	public void excluirTeste() {
		Teste teste = testeEAO.find(1L);
		teste.setEntityState(EnumEntityState.DELETED);
		testeEAO.save(teste);
	}

}
