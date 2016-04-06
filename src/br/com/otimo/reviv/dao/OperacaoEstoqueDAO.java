package br.com.otimo.reviv.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.OperacaoEstoque;

@Component
public class OperacaoEstoqueDAO extends GenericoDAO<Long, OperacaoEstoque> {

	public OperacaoEstoqueDAO(Session session) {
		super(session);
	}

	public List<OperacaoEstoque> listarOperacaoEstoqueRelatorio(Long idTipoOperacao, Long idMedico, Date inicio,
			Date fim, Long idPessoa, Date dataInicioValidade, Date dataFimValidade) {

		StringBuilder consulta = new StringBuilder();
		consulta.append("FROM OperacaoEstoque op ");

		// Operação tipo estoque - entrada ou saida
		consulta.append(" where op.tipoOperacao.id = " + idTipoOperacao);

		// Medico
		if (idMedico != null) {
			consulta.append(" and op.estoque.pessoaMedicamento.pessoa.id = ");
			consulta.append(idMedico);
		}

		if (inicio != null && fim != null) {
			consulta.append(" and op.dataHoraLog between ");
			consulta.append(inicio);
			consulta.append(" AND ");
			consulta.append(fim);
		}

		if (idPessoa != null) {
			consulta.append(" and op.pessoa.id = ");
			consulta.append(idPessoa);
		}

		if (dataInicioValidade != null && dataFimValidade != null) {
			consulta.append(" and op.estoque.pessoaMedicamento.dataVencimento between ");
			consulta.append(dataInicioValidade);
			consulta.append(" AND ");
			consulta.append(dataFimValidade);
		}

		return session.createQuery(consulta.toString()).list();
	}

}
