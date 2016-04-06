package br.com.otimo.reviv.dao;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.Usuario;

@Component
public class UsuarioDAO extends GenericoDAO<Long, Usuario> {

	public UsuarioDAO(Session session) {
		super(session);
	}

	public Usuario obterUsuarioPorSenha(Usuario usuario) throws NonUniqueResultException {
		return (Usuario) session.createQuery("FROM Usuario u where u.login = :login and u.senha = :senha")
				.setParameter("login", usuario.getLogin()).setParameter("senha", usuario.getSenha()).uniqueResult();
	}

	public Usuario obterUsuarioPorPessoa(Long idPessoa) throws NonUniqueResultException {
		return (Usuario) session.createQuery("FROM Usuario u where u.pessoa.id = :idPessoa")
				.setParameter("idPessoa", idPessoa).uniqueResult();
	}

	public Usuario obterUsuarioPorHashCode(String hashCode) throws NonUniqueResultException {
		return (Usuario) session.createQuery("select b.usuario FROM Biometria b where b.hashCode = :hashCode ")
				.setParameter("hashCode", hashCode).uniqueResult();
	}

	public void excluirHashCode(String hashCode) throws NonUniqueResultException {
		Transaction transaction = session.beginTransaction();
		StringBuilder consulta = new StringBuilder();
		consulta.append("delete from Biometria b ");
		consulta.append("where b.hashCode = ");
		consulta.append(hashCode);

		Query query = session.createQuery(consulta.toString());
		query.executeUpdate();

		transaction.commit();
	}

}
