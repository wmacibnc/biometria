package dao;

import java.util.List;

import modelo.Produtos;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import br.com.caelum.vraptor.ioc.Component;


@Component
public class ProdutosDAO {
	
		private final Session session;
		
		public ProdutosDAO(Session session){
			this.session = session;
			}
		
		public List<Produtos> listaTudo() {
			return this.session.createCriteria(Produtos.class).list();
			}
			
		public void salva(Produtos produtos) {
			Transaction tx = session.beginTransaction();
			session.save(produtos);
			tx.commit();
		}
		public Produtos carrega(Long id) {
			return (Produtos) this.session.load(Produtos.class, id);
			}
			public void atualiza(Produtos produtos) {
			Transaction tx = session.beginTransaction();
			this.session.update(produtos);
			tx.commit();
			}
			public void remove(Produtos produtos) {
				Transaction tx = session.beginTransaction();
				this.session.delete(produtos);
				tx.commit();
				}
			public List<Produtos> busca(String nome) {
				return session.createCriteria(Produtos.class).add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE)).list();			
			}
			public void recarrega(Produtos produtos) {
				session.refresh(produtos);
			}
}
