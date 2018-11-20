package br.edu.unoesc.api;

import java.util.List;

import javax.ejb.Remote;

import br.edu.unoesc.common.Insumo;
import br.edu.unoesc.common.Safra;

@Remote
public interface InsumoService {
	
	void salvar(Insumo entity);
	
	void alterar(Insumo entity);
	
	void excluir(Long id);
	
	List<Insumo> buscarTodos();

	Insumo buscar(Long id);
	
	List<Insumo> buscarTodosPorCultura(Long id);
}
