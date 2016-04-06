package br.com.otimo.reviv.sessao;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.otimo.reviv.modelo.Pessoa;

@Component
@SessionScoped
public class PessoaEntregaSessao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoaEntrega;

	public Pessoa getPessoaEntrega() {
		return pessoaEntrega;
	}

	public void setPessoaEntrega(Pessoa pessoaEntrega) {
		this.pessoaEntrega = pessoaEntrega;
	}

}
