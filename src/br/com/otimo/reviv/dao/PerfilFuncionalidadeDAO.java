package br.com.otimo.reviv.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.Estoque;
import br.com.otimo.reviv.modelo.Funcionalidade;
import br.com.otimo.reviv.modelo.PerfilFuncionalidade;

@Component
public class PerfilFuncionalidadeDAO extends GenericoDAO<Long, PerfilFuncionalidade> {

	public PerfilFuncionalidadeDAO(Session session) {
		super(session);
	}

	public List<Funcionalidade> listarTodos(Long idPerfil) {
		return session
				.createQuery("Select pf.funcionalidade From PerfilFuncionalidade pf where pf.perfil.id = :idPerfil")
				.setParameter("idPerfil", idPerfil).list();
	}

	public List<Estoque> busca(String nome, Long idMedico) {
		return session
				.createQuery(
						"From Estoque e where e.pessoaMedicamento.pessoa.id = :idMedico and e.pessoaMedicamento.medicamento.nome like :nome")
				.setParameter("idMedico", idMedico).setParameter("nome", "%" + nome + "%").list();
	}

	public Estoque obterEstoquePorMedicoMedicamento(Long idMedico, Long idMedicamento) {
		StringBuilder consulta = new StringBuilder();
		consulta.append(" From Estoque e ");
		consulta.append(" where e.pessoaMedicamento.pessoa.id = :idMedico");
		consulta.append(" and e.pessoaMedicamento.medicamento.id = :idMedicamento");

		return (Estoque) session.createQuery(consulta.toString()).setParameter("idMedico", idMedico)
				.setParameter("idMedicamento", idMedicamento).setMaxResults(1).uniqueResult();
	}

}
