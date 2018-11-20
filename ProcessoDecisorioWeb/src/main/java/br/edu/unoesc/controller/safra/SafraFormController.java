package br.edu.unoesc.controller.safra;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unoesc.api.SafraService;
import br.edu.unoesc.api.TipoCulturaService;
import br.edu.unoesc.common.Safra;
import br.edu.unoesc.common.TipoCultura;

@ManagedBean
@ViewScoped
public class SafraFormController implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private SafraService safraService;

	@EJB
	private TipoCulturaService tipoCulturaService;

	private Safra safra;

	@PostConstruct
	public void init() {
		safra = new Safra();
	}

	public void salvar() {

		if (safra.getId() == null) {
			safraService.salvar(safra);
		} else {
			safraService.alterar(safra);
		}
		
		safra = new Safra();
	}

	public void editar(Safra safra) {
		this.safra = safra;
	}

	public void excluir(Long id) {
		safraService.excluir(id);
	}

	public List<TipoCultura> buscarListaTipoCultura() {
		return tipoCulturaService.buscarTodos();
	}

	public List<Safra> buscarSafraCadastrado() {
		return safraService.buscarTodos();
	}

	public Safra getSafra() {
		return safra;
	}

	public void setSafra(Safra safra) {
		this.safra = safra;
	}
}
