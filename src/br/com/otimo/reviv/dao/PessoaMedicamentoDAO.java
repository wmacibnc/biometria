package br.com.otimo.reviv.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.PessoaMedicamento;

@Component
public class PessoaMedicamentoDAO extends GenericoDAO<Long, PessoaMedicamento> {

	public PessoaMedicamentoDAO(Session session) {
		super(session);
	}

	public List<PessoaMedicamento> busca2(String nome) {
		return session.createCriteria("From PessoaMedicamento pm WHERE pm.medicamento.nome like '%" + nome + "%' ")
				.list();
	}

}