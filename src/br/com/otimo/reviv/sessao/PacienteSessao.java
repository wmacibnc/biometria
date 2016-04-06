package br.com.otimo.reviv.sessao;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.otimo.reviv.modelo.Pessoa;

@Component
@SessionScoped
public class PacienteSessao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa paciente;

	public Pessoa getPaciente() {
		return paciente;
	}

	public void setPaciente(Pessoa paciente) {
		this.paciente = paciente;
	}

}
