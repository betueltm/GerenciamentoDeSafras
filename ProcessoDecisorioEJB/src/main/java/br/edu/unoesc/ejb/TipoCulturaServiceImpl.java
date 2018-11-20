package br.edu.unoesc.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.unoesc.api.TipoCulturaService;
import br.edu.unoesc.common.TipoCultura;
import br.edu.unoesc.common.entity.util.EnumEntityState;
import br.edu.unoesc.ejb.eao.TipoCulturaEAO;

@Stateless
public class TipoCulturaServiceImpl implements TipoCulturaService{

	TipoCulturaEAO eao =  new TipoCulturaEAO();
	
	@Override
	public void salvar(TipoCultura entity) {
		entity.setEntityState(EnumEntityState.NEW);
		eao.save(entity);
	}

	@Override
	public void alterar(TipoCultura entity) {
		entity.setEntityState(EnumEntityState.MODIFIED);
		eao.save(entity);
	}

	@Override
	public void excluir(Long id) {
		TipoCultura tipoCultura = eao.find(id);
		tipoCultura.setEntityState(EnumEntityState.DELETED);
		eao.save(tipoCultura);
	}

	@Override
	public List<TipoCultura> buscarTodos() {
		return eao.findAll();
	}

	@Override
	public TipoCultura buscar(Long id) {
		return eao.find(id);
	}

}
