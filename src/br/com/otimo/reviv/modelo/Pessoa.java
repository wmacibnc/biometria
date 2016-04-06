package br.com.otimo.reviv.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pessoa extends EntidadePadrao {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String nome;

	@ManyToOne
	@JoinColumn(name = "idTipoPessoa")
	private TipoPessoa tipoPessoa;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;

	// Relacionamento com Itens
	@OneToMany(mappedBy = "pessoa", targetEntity = PessoaContatoEletronico.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PessoaContatoEletronico> listaPessoaContatoEletronico;

	// Relacionamento com Itens
	@OneToMany(mappedBy = "pessoa", targetEntity = PessoaContatoTelefonico.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PessoaContatoTelefonico> listaPessoaContatoTelefonico;

	// Relacionamento com Tipo de Documento
	@OneToMany(mappedBy = "pessoa", targetEntity = PessoaTipoDocumento.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<PessoaTipoDocumento> listaPessoaTipoDocumento;

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

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<PessoaContatoEletronico> getListaPessoaContatoEletronico() {
		return listaPessoaContatoEletronico;
	}

	public void setListaPessoaContatoEletronico(List<PessoaContatoEletronico> listaPessoaContatoEletronico) {
		this.listaPessoaContatoEletronico = listaPessoaContatoEletronico;
	}

	public List<PessoaContatoTelefonico> getListaPessoaContatoTelefonico() {
		return listaPessoaContatoTelefonico;
	}

	public void setListaPessoaContatoTelefonico(List<PessoaContatoTelefonico> listaPessoaContatoTelefonico) {
		this.listaPessoaContatoTelefonico = listaPessoaContatoTelefonico;
	}

	public List<PessoaTipoDocumento> getListaPessoaTipoDocumento() {
		return listaPessoaTipoDocumento;
	}

	public void setListaPessoaTipoDocumento(List<PessoaTipoDocumento> listaPessoaTipoDocumento) {
		this.listaPessoaTipoDocumento = listaPessoaTipoDocumento;
	}

}
