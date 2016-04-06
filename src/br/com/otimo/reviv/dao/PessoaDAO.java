package br.com.otimo.reviv.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.Biometria;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.TipoPessoa;
import br.com.otimo.reviv.modelo.Usuario;

@Component
public class PessoaDAO extends GenericoDAO<Long, Pessoa> {

	public PessoaDAO(Session session) {
		super(session);
	}

	public List<Pessoa> listarTodosMedicos() {

		StringBuilder consulta = new StringBuilder();
		consulta.append("Select p From Pessoa p");
		consulta.append(" where p.tipoPessoa.id = :idTipoPessoa order by p.nome desc");
		return session.createQuery(consulta.toString()).setParameter("idTipoPessoa", TipoPessoa.MEDICO).list();

	}

	public List<Pessoa> listarTodosPacientes() {
		return session.createQuery("From Pessoa p where p.tipoPessoa.id = :idTipoPessoa order by p.nome desc")
				.setParameter("idTipoPessoa", TipoPessoa.PACIENTE).list();

	}

	public List<Pessoa> listarTodosFuncionarios() {
		return session.createQuery("From Pessoa p where p.tipoPessoa.id in(2,3,5,6) ").list();

	}

	public List<Pessoa> buscaPaciente(String nome) {
		return session.createCriteria(Pessoa.class).add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE))
				.add(Restrictions.eq("tipoPessoa.id", TipoPessoa.PACIENTE)).list();
	}

	public List<Pessoa> buscaPessoaPorCPF(String cpf) {
		return session
				.createQuery("Select p From PessoaTipoDocumento ptd join ptd.pessoa p where ptd.numeroDocumento = :cpf")
				.setParameter("cpf", cpf).list();
	}

	public void inserirBiometria(Long idUsuario, String hashCode) {
		excluirBiometriaAntiga(idUsuario);

		Biometria biometria = new Biometria();
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);

		biometria.setUsuario(usuario);
		biometria.setHashCode(hashCode);

		Transaction tx = session.beginTransaction();
		session.save(biometria);
		tx.commit();
	}

	public void excluirBiometriaAntiga(Long idUsuario) {
		Transaction transaction = session.beginTransaction();

		StringBuilder consulta = new StringBuilder();
		consulta.append("delete from Biometria b ");
		consulta.append("where b.usuario.id = ");
		consulta.append(idUsuario);

		Query query = session.createQuery(consulta.toString());
		query.executeUpdate();
		transaction.commit();
	}
}
