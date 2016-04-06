package br.com.otimo.reviv.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.TipoDocumento;

@Component
public class TipoDocumentoDAO extends GenericoDAO<Long, TipoDocumento> {

	public TipoDocumentoDAO(Session session) {
		super(session);
	}

}
