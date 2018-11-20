package br.edu.unoesc.controller.insumo;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.edu.unoesc.api.InsumoService;
import br.edu.unoesc.api.TipoCulturaService;
import br.edu.unoesc.common.Insumo;
import br.edu.unoesc.common.TipoCultura;

@ManagedBean
@ViewScoped
public class InsumoFormController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private InsumoService insumoService;

	@EJB
	private TipoCulturaService tipoCulturaService;

	private Insumo insumo;

	@PostConstruct
	public void init() {
		insumo = new Insumo();
	}
	
	public void salvar() {

		if (insumo.getId() == null) {
			insumoService.salvar(insumo);
		} else {
			insumoService.alterar(insumo);
		}
		
		insumo = new Insumo();
	}

	public void editar(Insumo insumo) {
		this.insumo = insumo;
	}

	public void excluir(Long id) {
		insumoService.excluir(id);
	}
	
	public List<TipoCultura> buscarListaTipoCultura() {
		return tipoCulturaService.buscarTodos();
	}

	public List<Insumo> buscarInsumoCadastrado() {
		return insumoService.buscarTodos();
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

}
