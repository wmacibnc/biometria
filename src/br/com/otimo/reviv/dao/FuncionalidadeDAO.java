package br.com.otimo.reviv.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.Funcionalidade;

@Component
public class FuncionalidadeDAO extends GenericoDAO<Long, Funcionalidade> {

	public FuncionalidadeDAO(Session session) {
		super(session);
	}

}
