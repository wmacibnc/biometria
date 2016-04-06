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
public class OperacaoEstoque extends EntidadePadrao {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idTipoOperacao")
	private TipoOperacao tipoOperacao;

	@ManyToOne
	@JoinColumn(name = "idEstoque")
	private Estoque estoque;

	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;

	private String numeroNotaFiscal;

	private String justificativa;

	private Short quantidadeDisponivelAnterior;
	private Short quantidadeDisponivelPosterior;

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

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public String getNumeroNotaFiscal() {
		return numeroNotaFiscal;
	}

	public void setNumeroNotaFiscal(String numeroNotaFiscal) {
		this.numeroNotaFiscal = numeroNotaFiscal;
	}

	public Short getQuantidadeDisponivelAnterior() {
		return quantidadeDisponivelAnterior;
	}

	public void setQuantidadeDisponivelAnterior(Short quantidadeDisponivelAnterior) {
		this.quantidadeDisponivelAnterior = quantidadeDisponivelAnterior;
	}

	public Short getQuantidadeDisponivelPosterior() {
		return quantidadeDisponivelPosterior;
	}

	public void setQuantidadeDisponivelPosterior(Short quantidadeDisponivelPosterior) {
		this.quantidadeDisponivelPosterior = quantidadeDisponivelPosterior;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

}
