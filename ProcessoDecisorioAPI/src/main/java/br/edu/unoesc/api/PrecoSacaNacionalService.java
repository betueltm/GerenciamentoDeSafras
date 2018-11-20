package br.edu.unoesc.api;

import java.util.List;

import javax.ejb.Remote;

import br.edu.unoesc.common.PrecoSacaNacional;

@Remote
public interface PrecoSacaNacionalService {
	
	void salvar(PrecoSacaNacional entity);
	
	void alterar(PrecoSacaNacional entity);
	
	void excluir(Long id);
	
	List<PrecoSacaNacional> buscarTodos();

	PrecoSacaNacional buscar(Long id);
	
	List<PrecoSacaNacional> buscarTodosPorTipoCultura(Long idTipoCultura);

	PrecoSacaNacional buscarUltimaCotacaoPorCultura(Long id);

}
