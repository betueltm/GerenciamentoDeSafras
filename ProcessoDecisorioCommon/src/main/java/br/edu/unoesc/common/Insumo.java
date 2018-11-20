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
public class Insumo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInsumo;

	private String descricao;

	private Double valor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipoCultura")
	private TipoCultura tipoCultura;

	public Long getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Long idInsumo) {
		this.idInsumo = idInsumo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public TipoCultura getTipoCultura() {
		return tipoCultura;
	}

	public void setTipoCultura(TipoCultura tipoCultura) {
		this.tipoCultura = tipoCultura;
	}

	@Override
	public Long getId() {
		return this.idInsumo;
	}

}
