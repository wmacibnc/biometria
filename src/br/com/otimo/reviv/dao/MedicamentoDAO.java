package br.com.otimo.reviv.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.Medicamento;

@Component
public class MedicamentoDAO extends GenericoDAO<Long, Medicamento> {

	public MedicamentoDAO(Session session) {
		super(session);
	}

}
