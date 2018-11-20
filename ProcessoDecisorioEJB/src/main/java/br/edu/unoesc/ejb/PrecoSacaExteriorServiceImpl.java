package br.edu.unoesc.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.unoesc.api.PrecoSacaExteriorService;
import br.edu.unoesc.common.PrecoSacaExterior;
import br.edu.unoesc.common.entity.util.EnumEntityState;
import br.edu.unoesc.ejb.eao.PrecoSacaExteriorEAO;

@Stateless
public class PrecoSacaExteriorServiceImpl implements PrecoSacaExteriorService {

	PrecoSacaExteriorEAO eao = new PrecoSacaExteriorEAO();

	@Override
	public void salvar(PrecoSacaExterior entity) {
		entity.setEntityState(EnumEntityState.NEW);
		eao.save(entity);
	}

	@Override
	public void alterar(PrecoSacaExterior entity) {
		entity.setEntityState(EnumEntityState.MODIFIED);
		eao.save(entity);
	}

	@Override
	public void excluir(Long id) {
		PrecoSacaExterior saca = eao.find(id);
		saca.setEntityState(EnumEntityState.DELETED);
		eao.save(saca);
	}

	@Override
	public List<PrecoSacaExterior> buscarTodos() {
		return eao.findAll();
	}

	@Override
	public PrecoSacaExterior buscar(Long id) {
		return eao.find(id);
	}

	@Override
	public List<PrecoSacaExterior> buscarTodosPorTipoCultura(Long idTipoCultura) {
		return eao.buscarTodosPorTipoCultura(idTipoCultura);
	}
}
