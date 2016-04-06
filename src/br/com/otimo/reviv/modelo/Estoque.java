package br.com.otimo.reviv.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Estoque extends EntidadePadrao {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idPessoaMedicamento")
	private PessoaMedicamento pessoaMedicamento;

	private Short quantidadeDisponivel;

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

	public PessoaMedicamento getPessoaMedicamento() {
		return pessoaMedicamento;
	}

	public void setPessoaMedicamento(PessoaMedicamento pessoaMedicamento) {
		this.pessoaMedicamento = pessoaMedicamento;
	}

	public Short getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(Short quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

}
