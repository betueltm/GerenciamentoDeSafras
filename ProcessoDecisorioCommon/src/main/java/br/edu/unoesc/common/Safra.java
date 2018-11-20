package br.edu.unoesc.common;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.unoesc.common.entity.util.AbstractEntity;

@Entity
public class Safra extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSafra;

	private String descricao;

	@Temporal(TemporalType.DATE)
	private Date dataPlantio;

	@Temporal(TemporalType.DATE)
	private Date dataColheira;

	private Double qtdHectares;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idTipoCultura")
	private TipoCultura tipoCultura;

	public Long getIdSafra() {
		return idSafra;
	}

	public void setIdSafra(Long idSafra) {
		this.idSafra = idSafra;
	}

	public Date getDataPlantio() {
		return dataPlantio;
	}

	public void setDataPlantio(Date dataPlantio) {
		this.dataPlantio = dataPlantio;
	}

	public Date getDataColheira() {
		return dataColheira;
	}

	public void setDataColheira(Date dataColheira) {
		this.dataColheira = dataColheira;
	}

	public Double getQtdHectares() {
		return qtdHectares;
	}

	public void setQtdHectares(Double qtdHectares) {
		this.qtdHectares = qtdHectares;
	}

	@Override
	public Long getId() {
		return this.idSafra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoCultura getTipoCultura() {
		return tipoCultura;
	}

	public void setTipoCultura(TipoCultura tipoCultura) {
		this.tipoCultura = tipoCultura;
	}

}
