package dao;

import java.util.List;

import modelo.Itens;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ItensDAO {

	private final Session session;

	public ItensDAO(Session session) {
		this.session = session;
	}

	public List<Itens> listaTudo() {
		return this.session.createCriteria(Itens.class).list();
	}

	public void salva(Itens itens) {
		Transaction tx = session.beginTransaction();
		session.save(itens);
		tx.commit();
	}

	public Itens carrega(Long id) {
		return (Itens) this.session.load(Itens.class, id);
	}

	public void atualiza(Itens itens) {
		Transaction tx = session.beginTransaction();
		this.session.update(itens);
		tx.commit();
	}

	public void remove(Itens itens) {
		Transaction tx = session.beginTransaction();
		this.session.delete(itens);
		tx.commit();
	}

	public List<Itens> busca(String nome) {
		return session.createCriteria(Itens.class)
				.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE))
				.list();
	}

	public void recarrega(Itens itens) {
		session.refresh(itens);
	}
}
