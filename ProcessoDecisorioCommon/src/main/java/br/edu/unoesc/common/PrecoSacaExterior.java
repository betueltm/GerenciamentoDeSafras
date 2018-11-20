package br.edu.unoesc.common;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.edu.unoesc.common.entity.util.AbstractEntity;

@Entity
public class PrecoSacaExterior extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPrecoSacaExterior;

	private Double valor;

	private Long mes;

	private Long ano;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipoCultura")
	private TipoCultura tipoCultura;

	public Long getIdPrecoSacaExterior() {
		return idPrecoSacaExterior;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getMes() {
		return mes;
	}

	public void setMes(Long mes) {
		this.mes = mes;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public void setIdPrecoSacaExterior(Long idPrecoSacaExterior) {
		this.idPrecoSacaExterior = idPrecoSacaExterior;
	}

	@Override
	public Long getId() {
		return this.idPrecoSacaExterior;
	}
}
