package br.edu.unoesc.api;

import java.util.List;

import javax.ejb.Remote;

import br.edu.unoesc.common.Safra;

@Remote
public interface SafraService {

	void salvar(Safra entity);
	
	void alterar(Safra entity);
	
	void excluir(Long id);
	
	List<Safra> buscarTodos();

	Safra buscar(Long id);

	List<Safra> buscarTodosPorCultura(Long id);

	Safra buscarUltimaSafraValida();
	
}
