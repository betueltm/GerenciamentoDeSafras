package br.edu.unoesc.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.unoesc.api.InsumoService;
import br.edu.unoesc.common.Insumo;
import br.edu.unoesc.common.entity.util.EnumEntityState;
import br.edu.unoesc.ejb.eao.InsumoEAO;

@Stateless
public class InsumoServiceImpl implements InsumoService {

    InsumoEAO eao =  new InsumoEAO();
	
	@Override
	public void salvar(Insumo entity) {
		entity.setEntityState(EnumEntityState.NEW);
		eao.save(entity);
	}

	@Override
	public void alterar(Insumo entity) {
		entity.setEntityState(EnumEntityState.MODIFIED);
		eao.save(entity);
	}

	@Override
	public void excluir(Long id) {
		Insumo insumo = eao.find(id);
		insumo.setEntityState(EnumEntityState.DELETED);
		eao.save(insumo);
	}

	@Override
	public List<Insumo> buscarTodos() {
		return eao.findAll();
	}

	@Override
	public Insumo buscar(Long id) {
		return eao.find(id);
	}

	@Override
	public List<Insumo> buscarTodosPorCultura(Long id) {
		return eao.buscarTodosPorCultura(id);
	}

}
