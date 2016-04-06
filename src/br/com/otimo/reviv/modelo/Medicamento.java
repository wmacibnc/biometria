package br.com.otimo.reviv.modelo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Medicamento extends EntidadePadrao {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String fabricante;
	private BigDecimal precoCusto;
	private BigDecimal porcentagemLucro;
	private BigDecimal precoVenda;
	private Short quantidadeMinima;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraLog = new Date();
	private String usuarioLog;

	public Date getDataHoraLog() {
		return dataHoraLog;
	}

	public void setDataHoraLog(Date dataHoraLog) {
		this.dataHoraLog = dataHoraLog;
	}

	public String getUsuarioLog() {
		return usuarioLog;
	}

	public void setUsuarioLog(String usuarioLog) {
		this.usuarioLog = usuarioLog;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPorcentagemLucro() {
		return porcentagemLucro;
	}

	public void setPorcentagemLucro(BigDecimal porcentagemLucro) {
		this.porcentagemLucro = porcentagemLucro;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Short getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(Short quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

}
