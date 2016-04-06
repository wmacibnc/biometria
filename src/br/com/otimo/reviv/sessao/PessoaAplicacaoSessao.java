package br.com.otimo.reviv.sessao;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.otimo.reviv.modelo.Pessoa;

@Component
@SessionScoped
public class PessoaAplicacaoSessao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa pessoaAplicacao;

	public Pessoa getPessoaAplicacao() {
		return pessoaAplicacao;
	}

	public void setPessoaAplicacao(Pessoa pessoaAplicacao) {
		this.pessoaAplicacao = pessoaAplicacao;
	}

}
