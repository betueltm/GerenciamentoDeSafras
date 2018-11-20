package br.edu.unoesc.api;

import java.util.List;

import javax.ejb.Remote;

import br.edu.unoesc.common.PrecoSacaExterior;

@Remote
public interface PrecoSacaExteriorService {
	
	void salvar(PrecoSacaExterior entity);
	
	void alterar(PrecoSacaExterior entity);
	
	void excluir(Long id);
	
	List<PrecoSacaExterior> buscarTodos();

	PrecoSacaExterior buscar(Long id);
	
	List<PrecoSacaExterior> buscarTodosPorTipoCultura(Long idTipoCultura);

}
