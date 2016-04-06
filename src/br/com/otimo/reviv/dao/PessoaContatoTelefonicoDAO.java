package br.com.otimo.reviv.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.PessoaContatoTelefonico;

@Component
public class PessoaContatoTelefonicoDAO extends GenericoDAO<Long, PessoaContatoTelefonico> {

	public PessoaContatoTelefonicoDAO(Session session) {
		super(session);
	}

	public List<PessoaContatoTelefonico> listarPessoaContatoTelefonico(Long idPessoa) {
		return session.createQuery("From PessoaContatoTelefonico pct where pct.pessoa.id = :idPessoa ").setParameter("idPessoa", idPessoa).list();
	}

	public void limparListaTelefonico(Long idPessoa) {
		session.createQuery("Delete From PessoaContatoTelefonico pct where pct.pessoa.id = :idPessoa ").setParameter("idPessoa", idPessoa).executeUpdate();
	}

}
