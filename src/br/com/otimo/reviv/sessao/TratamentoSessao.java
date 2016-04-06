package br.com.otimo.reviv.sessao;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.otimo.reviv.modelo.Tratamento;

@Component
@SessionScoped
public class TratamentoSessao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Tratamento tratamento;

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

}
