package br.com.otimo.reviv.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.Perfil;

@Component
public class PerfilDAO extends GenericoDAO<Long, Perfil> {

	public PerfilDAO(Session session) {
		super(session);
	}

}
