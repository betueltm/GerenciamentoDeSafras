package br.edu.unoesc.api;

import java.util.Date;

import javax.ejb.Remote;

@Remote
public interface IndexService {

	void salvarTeste(Date date);
	
	void alterarTeste(Date date);
	
	void excluirTeste();
}
