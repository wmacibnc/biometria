package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import modelo.Clientes;

@Component
public class ClientesDAO {

	private final Session session;

	public ClientesDAO(Session session) {
		this.session = session;
	}

	public List<Clientes> listaTudo() {
		return this.session.createCriteria(Clientes.class).list();
	}

	public void salva(Clientes clientes) {
		Transaction tx = session.beginTransaction();
		session.save(clientes);
		tx.commit();
	}

	public Clientes carrega(Long id) {
		return (Clientes) this.session.load(Clientes.class, id);
	}

	public void atualiza(Clientes clientes) {
		Transaction tx = session.beginTransaction();
		this.session.update(clientes);
		tx.commit();
	}

	public void remove(Clientes clientes) {
		Transaction tx = session.beginTransaction();
		this.session.delete(clientes);
		tx.commit();
	}

	public List<Clientes> busca(String nome) {
		return session.createCriteria(Clientes.class).add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE)).list();
	}

	public void recarrega(Clientes clientes) {
		session.refresh(clientes);
	}
}
