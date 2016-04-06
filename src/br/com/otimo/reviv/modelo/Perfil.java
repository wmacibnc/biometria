package br.com.otimo.reviv.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Perfil extends EntidadePadrao {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    public Perfil() {
    }

    public Perfil(Long id) {
        this.id = id;
    }

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

}
