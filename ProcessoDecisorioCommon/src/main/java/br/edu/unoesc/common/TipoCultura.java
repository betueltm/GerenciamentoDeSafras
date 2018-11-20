package br.edu.unoesc.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.edu.unoesc.common.entity.util.AbstractEntity;

@Entity
public class TipoCultura extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TipoCulturaGenerator", sequenceName = "TipoCulturaGenerator", allocationSize = 1)
	@GeneratedValue(generator = "TipoCulturaGenerator")
	private Long idTipoCultura;

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public Long getId() {
		return this.idTipoCultura;
	}
	
	public boolean isMilho() {
		return this.idTipoCultura.equals(1L);
	}
	
	public boolean isSoja() {
		return this.idTipoCultura.equals(2L);
	}
}
