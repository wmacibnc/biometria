package br.com.otimo.reviv.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.Posologia;

@Component
public class PosologiaDAO extends GenericoDAO<Long, Posologia> {

    public PosologiaDAO(Session session) {
        super(session);
    }

}
