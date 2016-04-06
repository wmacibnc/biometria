package dao;

import java.util.List;

import modelo.Vendas;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class VendasDAO {

	private final Session session;

	public VendasDAO(Session session) {
		this.session = session;
	}

	public List<Vendas> listaTudo() {
		return this.session.createCriteria(Vendas.class).list();
	}

	public void salva(Vendas vendas) {
		Transaction tx = session.beginTransaction();
		session.save(vendas);
		tx.commit();
	}

	public Vendas carrega(Long id) {
		return (Vendas) this.session.load(Vendas.class, id);
	}

	public void atualiza(Vendas vendas) {
		Transaction tx = session.beginTransaction();
		this.session.update(vendas);
		tx.commit();
	}

	public void remove(Vendas vendas) {
		Transaction tx = session.beginTransaction();
		this.session.delete(vendas);
		tx.commit();
	}

	public List<Vendas> busca(String nome) {
		return session.createCriteria(Vendas.class)
				.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE))
				.list();
	}

	public void recarrega(Vendas vendas) {
		session.refresh(vendas);
	}

	// Finalizar Venda - Busca por id
	public List<Vendas> finalizar(Long id) {
		return session.createCriteria(Vendas.class)
				.add(Restrictions.eq("id", id)).list();
	}
}
