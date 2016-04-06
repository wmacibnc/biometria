package br.com.otimo.reviv.modelo;

import java.math.BigDecimal;
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
import javax.persistence.Transient;

@Entity
public class Tratamento extends EntidadePadrao {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private BigDecimal valorDesconto;
    private BigDecimal valorAcrescimo;
    private BigDecimal valorTotalCusto;
    private BigDecimal valorTotal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;

    private Long quantidadeTotal;

    @Transient
    private Integer totalEntregue;

    @ManyToOne
    @JoinColumn(name = "idPosologia")
    private Posologia posologia;

    @ManyToOne
    @JoinColumn(name = "idTipoSituacaoTratamento")
    private TipoSituacaoTratamento tipoSituacaoTratamento;

    @ManyToOne
    @JoinColumn(name = "idMedicamento")
    private Medicamento medicamento;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Pessoa paciente;

    @ManyToOne
    @JoinColumn(name = "idMedico")
    private Pessoa medico;

    // Relacionamento com Itens
    @OneToMany(mappedBy = "tratamento", targetEntity = ItemEntrega.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemEntrega> listaItemEntrega;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraLog = new Date();

    private String usuarioLog;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     *            the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the valorDesconto
     */
    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    /**
     * @param valorDesconto
     *            the valorDesconto to set
     */
    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    /**
     * @return the valorAcrescimo
     */
    public BigDecimal getValorAcrescimo() {
        return valorAcrescimo;
    }

    /**
     * @param valorAcrescimo
     *            the valorAcrescimo to set
     */
    public void setValorAcrescimo(BigDecimal valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
    }

    /**
     * @return the valorTotalCusto
     */
    public BigDecimal getValorTotalCusto() {
        return valorTotalCusto;
    }

    /**
     * @param valorTotalCusto
     *            the valorTotalCusto to set
     */
    public void setValorTotalCusto(BigDecimal valorTotalCusto) {
        this.valorTotalCusto = valorTotalCusto;
    }

    /**
     * @return the valorTotal
     */
    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal
     *            the valorTotal to set
     */
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio
     *            the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim
     *            the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the quantidadeTotal
     */
    public Long getQuantidadeTotal() {
        return quantidadeTotal;
    }

    /**
     * @param quantidadeTotal
     *            the quantidadeTotal to set
     */
    public void setQuantidadeTotal(Long quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    /**
     * @return the posologia
     */
    public Posologia getPosologia() {
        return posologia;
    }

    /**
     * @param posologia
     *            the posologia to set
     */
    public void setPosologia(Posologia posologia) {
        this.posologia = posologia;
    }

    /**
     * @return the tipoSituacaoTratamento
     */
    public TipoSituacaoTratamento getTipoSituacaoTratamento() {
        return tipoSituacaoTratamento;
    }

    /**
     * @param tipoSituacaoTratamento
     *            the tipoSituacaoTratamento to set
     */
    public void setTipoSituacaoTratamento(TipoSituacaoTratamento tipoSituacaoTratamento) {
        this.tipoSituacaoTratamento = tipoSituacaoTratamento;
    }

    /**
     * @return the medicamento
     */
    public Medicamento getMedicamento() {
        return medicamento;
    }

    /**
     * @param medicamento
     *            the medicamento to set
     */
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * @return the paciente
     */
    public Pessoa getPaciente() {
        return paciente;
    }

    /**
     * @param paciente
     *            the paciente to set
     */
    public void setPaciente(Pessoa paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the medico
     */
    public Pessoa getMedico() {
        return medico;
    }

    /**
     * @param medico
     *            the medico to set
     */
    public void setMedico(Pessoa medico) {
        this.medico = medico;
    }

    /**
     * @return the dataHoraLog
     */
    public Date getDataHoraLog() {
        return dataHoraLog;
    }

    /**
     * @param dataHoraLog
     *            the dataHoraLog to set
     */
    public void setDataHoraLog(Date dataHoraLog) {
        this.dataHoraLog = dataHoraLog;
    }

    /**
     * @return the usuarioLog
     */
    public String getUsuarioLog() {
        return usuarioLog;
    }

    /**
     * @param usuarioLog
     *            the usuarioLog to set
     */
    public void setUsuarioLog(String usuarioLog) {
        this.usuarioLog = usuarioLog;
    }

    public List<ItemEntrega> getListaItemEntrega() {
        return listaItemEntrega;
    }

    public void setListaItemEntrega(List<ItemEntrega> listaItemEntrega) {
        this.listaItemEntrega = listaItemEntrega;
    }

    public Integer getTotalEntregue() {
        if (listaItemEntrega != null) {
            return listaItemEntrega.size();
        }
        return 0;
    }

    public void setTotalEntregue(Integer totalEntregue) {
        this.totalEntregue = totalEntregue;
    }

}
