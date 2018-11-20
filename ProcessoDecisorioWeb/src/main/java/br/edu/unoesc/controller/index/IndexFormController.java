package br.edu.unoesc.controller.index;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unoesc.api.IndexService;
import br.edu.unoesc.api.PrecoSacaExteriorService;
import br.edu.unoesc.common.PrecoSacaExterior;

@ManagedBean
@ViewScoped
public class IndexFormController {

	@EJB
	private IndexService indexService;
	
	@EJB
	private PrecoSacaExteriorService precoSacaExteriorService;
	
	private Date data;
	
	@PostConstruct
	public void init() {
		
		PrecoSacaExterior saca = new PrecoSacaExterior();
		saca.setAno(2018L);
		saca.setMes(1L);
		saca.setValor(32.15);
		
		precoSacaExteriorService.salvar(saca);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void clicarBotao() {
		System.out.println("clicou par salvar com a data " + data);
		indexService.excluirTeste();
	}
}
