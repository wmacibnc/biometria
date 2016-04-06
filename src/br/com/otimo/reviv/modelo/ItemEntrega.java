package br.com.otimo.reviv.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class ItemEntrega extends EntidadePadrao {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idTratamento")
    private Tratamento tratamento;

    @ManyToOne
    @JoinColumn(name = "idPessoaRecebedora")
    private Pessoa pessoaRecebedora;

    @ManyToOne
    @JoinColumn(name = "idFarmaceutico")
    private Pessoa farmaceutico;

    private Short quantidadeEntrege;

    @Transient
    private Boolean sim;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrega;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraLog = new Date();

    private String usuarioLog;

    private Long sessao;

    // Dados da aplicação

    private Boolean bolAplicacao;

    @ManyToOne
    @JoinColumn(name = "idEnfermeira")
    private Pessoa enfermeira;

    private Short quantidadeAplicada;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAplicacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }

    public Pessoa getPessoaRecebedora() {
        return pessoaRecebedora;
    }

    public void setPessoaRecebedora(Pessoa pessoaRecebedora) {
        this.pessoaRecebedora = pessoaRecebedora;
    }

    public Pessoa getFarmaceutico() {
        return farmaceutico;
    }

    public void setFarmaceutico(Pessoa farmaceutico) {
        this.farmaceutico = farmaceutico;
    }

    public Short getQuantidadeEntrege() {
        return quantidadeEntrege;
    }

    public void setQuantidadeEntrege(Short quantidadeEntrege) {
        this.quantidadeEntrege = quantidadeEntrege;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

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

    public Boolean getSim() {
        return sim;
    }

    public void setSim(Boolean sim) {
        this.sim = sim;
    }

    public Long getSessao() {
        return sessao;
    }

    public void setSessao(Long sessao) {
        this.sessao = sessao;
    }

    /**
     * @return the bolAplicacao
     */
    public Boolean getBolAplicacao() {
        return bolAplicacao;
    }

    /**
     * @param bolAplicacao
     *            the bolAplicacao to set
     */
    public void setBolAplicacao(Boolean bolAplicacao) {
        this.bolAplicacao = bolAplicacao;
    }

    /**
     * @return the enfermeira
     */
    public Pessoa getEnfermeira() {
        return enfermeira;
    }

    /**
     * @param enfermeira
     *            the enfermeira to set
     */
    public void setEnfermeira(Pessoa enfermeira) {
        this.enfermeira = enfermeira;
    }

    /**
     * @return the quantidadeAplicada
     */
    public Short getQuantidadeAplicada() {
        return quantidadeAplicada;
    }

    /**
     * @param quantidadeAplicada
     *            the quantidadeAplicada to set
     */
    public void setQuantidadeAplicada(Short quantidadeAplicada) {
        this.quantidadeAplicada = quantidadeAplicada;
    }

    /**
     * @return the dataAplicacao
     */
    public Date getDataAplicacao() {
        return dataAplicacao;
    }

    /**
     * @param dataAplicacao
     *            the dataAplicacao to set
     */
    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

}
