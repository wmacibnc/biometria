package br.com.otimo.reviv.sessao;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.otimo.reviv.modelo.Pessoa;

@Component
@SessionScoped
public class MedicoSessao implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pessoa medico;

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

}
