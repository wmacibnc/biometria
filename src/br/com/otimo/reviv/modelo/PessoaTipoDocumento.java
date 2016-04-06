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
public class PessoaTipoDocumento extends EntidadePadrao {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idPessoa")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "idTipoDocumento")
	private TipoDocumento tipoDocumento;

	private String numeroDocumento;

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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
