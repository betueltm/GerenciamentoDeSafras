package br.edu.unoesc.controller.tipocultura;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unoesc.api.TipoCulturaService;
import br.edu.unoesc.common.TipoCultura;

@ManagedBean
@ViewScoped
public class TipoCulturaFormController {

	@EJB
	private TipoCulturaService tipoCulturaService;

	private TipoCultura tipoCultura;

	@PostConstruct
	public void init() {
		this.tipoCultura = new TipoCultura();
	}
	
	public void salvar() {
		System.out.println("passou");
		tipoCulturaService.salvar(tipoCultura);
	}
	
	public List<TipoCultura> buscarTipoCulturaCadastrado(){
		return tipoCulturaService.buscarTodos();
	}

	public TipoCultura getTipoCultura() {
		return tipoCultura;
	}

	public void setTipoCultura(TipoCultura tipoCultura) {
		this.tipoCultura = tipoCultura;
	}

}
