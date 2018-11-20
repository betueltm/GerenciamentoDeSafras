package br.edu.unoesc.api;

import java.util.List;

import javax.ejb.Remote;

import br.edu.unoesc.common.TipoCultura;

@Remote
public interface TipoCulturaService {
	
	void salvar(TipoCultura entity);
	
	void alterar(TipoCultura entity);
	
	void excluir(Long id);
	
	List<TipoCultura> buscarTodos();

	TipoCultura buscar(Long id);
}
