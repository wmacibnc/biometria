package br.com.otimo.reviv.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.Tratamento;

@Component
public class TratamentoDAO extends GenericoDAO<Long, Tratamento> {

	public TratamentoDAO(Session session) {
		super(session);
	}

	public List<Tratamento> listarTodos(Long id) {
		return session.createQuery("FROM Tratamento t where t.paciente.id = :id").setParameter("id", id).list();
	}

	public List<Tratamento> listarTodosTratamentos(Pessoa paciente, Long idSituacaoTratamento) {
		StringBuilder consulta = new StringBuilder();
		consulta.append(" FROM Tratamento t ");
		consulta.append(" where 1=1 ");
		consulta.append(" and t.paciente.id = :idPaciente ");

		if (idSituacaoTratamento != null && idSituacaoTratamento < 3) {
			consulta.append(" and t.tipoSituacaoTratamento.id = ");
			consulta.append(idSituacaoTratamento);
		}

		return session.createQuery(consulta.toString()).setParameter("idPaciente", paciente.getId()).list();
	}

	public List<Tratamento> listarTodosTratamentosParaAplicacao(Pessoa paciente, Long idSituacaoTratamento) {
		StringBuilder consulta = new StringBuilder();
		consulta.append(" select t FROM Tratamento t ");
		consulta.append(" where 1=1 ");
		consulta.append(" and t.paciente.id = :idPaciente ");

		if (idSituacaoTratamento != null && idSituacaoTratamento < 3) {
			consulta.append(" and t.tipoSituacaoTratamento.id = ");
			consulta.append(idSituacaoTratamento);
		}

		return session.createQuery(consulta.toString()).setParameter("idPaciente", paciente.getId()).list();
	}

	public List<Long> buscaEstoqueJson(String id, Long idMedico) {

		StringBuilder consulta = new StringBuilder();
		consulta.append(" select quantidadeDisponivel from Estoque e ");
		consulta.append(" where e.pessoaMedicamento.pessoa.id = :idMedico ");
		consulta.append(" and e.pessoaMedicamento.medicamento.id = :id ");

		return session.createQuery(consulta.toString()).setParameter("idMedico", idMedico)
				.setParameter("id", Long.parseLong(id)).list();
	}

}
