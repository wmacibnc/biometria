package br.com.otimo.reviv.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.TipoPessoa;

@Component
public class TipoPessoaDAO extends GenericoDAO<Long, TipoPessoa> {

	public TipoPessoaDAO(Session session) {
		super(session);
	}

	public List<TipoPessoa> listarTipoPessoaFuncionario() {
		return session.createQuery("FROM TipoPessoa where id in (2,3,5,6)").list();

	}

}
