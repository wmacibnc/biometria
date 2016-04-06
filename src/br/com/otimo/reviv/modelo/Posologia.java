package br.com.otimo.reviv.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Posologia extends EntidadePadrao {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    public Posologia() {
    }

    public Posologia(Long id) {
        this.id = id;
    }

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

}
