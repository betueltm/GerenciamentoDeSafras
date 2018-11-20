package br.edu.unoesc.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.unoesc.api.SafraService;
import br.edu.unoesc.common.Safra;
import br.edu.unoesc.common.entity.util.EnumEntityState;
import br.edu.unoesc.ejb.eao.SafraEAO;

@Stateless
public class SafraServiceImpl implements SafraService{
	
	SafraEAO eao = new SafraEAO();

	@Override
	public void salvar(Safra entity) {
		entity.setEntityState(EnumEntityState.NEW);
		eao.save(entity);
	}

	@Override
	public void alterar(Safra entity) {
		entity.setEntityState(EnumEntityState.MODIFIED);
		eao.save(entity);
	}

	@Override
	public void excluir(Long id) {
		Safra safra = eao.find(id);
		safra.setEntityState(EnumEntityState.DELETED);
		eao.save(safra);
	}

	@Override
	public List<Safra> buscarTodos() {
		return eao.findAll();
	}

	@Override
	public Safra buscar(Long id) {
		return eao.find(id);
	}

	@Override
	public List<Safra> buscarTodosPorCultura(Long id) {
		return eao.buscarTodosPorCultura(id);
	}

	@Override
	public Safra buscarUltimaSafraValida() {
		return eao.buscarUltimaSafraValida();
	}

}
