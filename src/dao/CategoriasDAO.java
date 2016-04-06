package dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.dao.GenericoDAO;
import modelo.Categorias;

@Component
public class CategoriasDAO extends GenericoDAO<Long, Categorias> {

	public CategoriasDAO(Session session) {
		super(session);
	}

}
