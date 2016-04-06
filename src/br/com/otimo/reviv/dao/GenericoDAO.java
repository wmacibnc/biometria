package br.com.otimo.reviv.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("unchecked")
public class GenericoDAO<PK, T> {

	protected final Session session;

	public GenericoDAO(Session session) {
		this.session = session;
	}

	public T obterPorId(Serializable pk) {
		return (T) session.get(getTypeClass(), pk);
	}

	public void novo(T entity) {
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();

	}

	public void atualizar(T entity) {
		Transaction tx = session.beginTransaction();
		this.session.update(entity);
		tx.commit();

	}

	public void excluir(T entity) {
		Transaction tx = session.beginTransaction();
		this.session.delete(entity);
		tx.commit();
	}

	public List<T> listarTodos() {
		return session.createQuery(("FROM " + getTypeClass().getName())).list();
	}

	public List<T> busca(String nome) {
		return session.createCriteria(getTypeClass()).add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE)).list();
	}

	public void recarrega(T entity) {
		session.refresh(entity);
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		return clazz;
	}
}