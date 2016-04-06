package br.com.otimo.reviv.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.PessoaContatoEletronico;

@Component
public class PessoaContatoEletronicoDAO extends GenericoDAO<Long, PessoaContatoEletronico> {

	public PessoaContatoEletronicoDAO(Session session) {
		super(session);
	}

	public List<PessoaContatoEletronico> listarPessoaContatoTelefonico(Long idPessoa) {
		return session.createQuery("From PessoaContatoEletronico pce where pce.pessoa.id = :idPessoa ")
				.setParameter("idPessoa", idPessoa).list();
	}

	public void limparListaEletronico(Long idPessoa) {
		session.createQuery("Delete From PessoaContatoEletronico pce where pce.pessoa.id = :idPessoa ")
				.setParameter("idPessoa", idPessoa).executeUpdate();
	}

}
