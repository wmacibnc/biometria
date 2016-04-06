package br.com.otimo.reviv.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.TipoOperacao;

@Component
public class TipoOperacaoDAO extends GenericoDAO<Long, TipoOperacao> {

	public TipoOperacaoDAO(Session session) {
		super(session);
	}

}
