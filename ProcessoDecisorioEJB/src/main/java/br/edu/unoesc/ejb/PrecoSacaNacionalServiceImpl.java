package br.edu.unoesc.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.unoesc.api.PrecoSacaNacionalService;
import br.edu.unoesc.common.PrecoSacaNacional;
import br.edu.unoesc.common.entity.util.EnumEntityState;
import br.edu.unoesc.ejb.eao.PrecoSacaNacionalEAO;

@Stateless
public class PrecoSacaNacionalServiceImpl implements PrecoSacaNacionalService {

	PrecoSacaNacionalEAO eao = new PrecoSacaNacionalEAO();

	@Override
	public void salvar(PrecoSacaNacional entity) {
		entity.setEntityState(EnumEntityState.NEW);
		eao.save(entity);
	}

	@Override
	public void alterar(PrecoSacaNacional entity) {
		entity.setEntityState(EnumEntityState.MODIFIED);
		eao.save(entity);
	}

	@Override
	public void excluir(Long id) {
		PrecoSacaNacional saca = eao.find(id);
		saca.setEntityState(EnumEntityState.DELETED);
		eao.save(saca);
	}

	@Override
	public List<PrecoSacaNacional> buscarTodos() {
		return eao.findAll();
	}

	@Override
	public PrecoSacaNacional buscar(Long id) {
		return eao.find(id);
	}

	@Override
	public List<PrecoSacaNacional> buscarTodosPorTipoCultura(Long idTipoCultura) {
		return eao.buscarTodosPorTipoCultura(idTipoCultura);
	}

	@Override
	public PrecoSacaNacional buscarUltimaCotacaoPorCultura(Long id) {
		return eao.buscarUltimaCotacaoPorCultura(id);
	}
}
