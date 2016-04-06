package br.com.otimo.reviv.controller;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.otimo.reviv.dao.EstoqueDAO;
import br.com.otimo.reviv.dao.OperacaoEstoqueDAO;
import br.com.otimo.reviv.modelo.Estoque;
import br.com.otimo.reviv.modelo.OperacaoEstoque;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.TipoOperacao;

@Resource
public class RelatorioController {
	private final EstoqueDAO estoqueDAO;
	private final OperacaoEstoqueDAO operacaoEstoqueDAO;
	private final Result result;

	public RelatorioController(EstoqueDAO estoqueDAO, OperacaoEstoqueDAO operacaoEstoqueDAO, Result result) {
		super();
		this.estoqueDAO = estoqueDAO;
		this.operacaoEstoqueDAO = operacaoEstoqueDAO;
		this.result = result;
	}

	// Estoque disponível
	@Get("/estoqueDisponivel")
	public List<Estoque> estoqueDisponivel(Pessoa pessoa) {
		if (pessoa != null && pessoa.getId() != null) {
			return estoqueDAO.listarTodos(pessoa.getId());
		} else {
			return estoqueDAO.listarTodos();
		}
	}

	// Entradas no estoque
	@Get("/entradaEstoque")
	public List<OperacaoEstoque> entradaEstoque(Pessoa medico, Date inicio, Date fim, Pessoa funcionario,
			Date dataInicioValidade, Date dataFimValidade) {
		Long idMedico = null;
		Long idPessoa = null;

		idMedico = verificarPessoaNULL(medico, idMedico);
		idPessoa = verificarPessoaNULL(funcionario, idPessoa);

		return operacaoEstoqueDAO.listarOperacaoEstoqueRelatorio(TipoOperacao.ENTRADA, idMedico, inicio, fim, idPessoa,
				dataInicioValidade, dataFimValidade);
	}

	private Long verificarPessoaNULL(Pessoa medico, Long idMedico) {
		if (medico != null && medico.getId() != null) {
			idMedico = medico.getId();
		}
		return idMedico;
	}

	// Saúdas no estoque
	@Get("/saidaEstoque")
	public List<OperacaoEstoque> saidaEstoque(Pessoa medico, Date inicio, Date fim, Pessoa funcionario,
			Date dataInicioValidade, Date dataFimValidade) {

		Long idMedico = null;
		Long idPessoa = null;

		idMedico = verificarPessoaNULL(medico, idMedico);
		idPessoa = verificarPessoaNULL(funcionario, idPessoa);

		return operacaoEstoqueDAO.listarOperacaoEstoqueRelatorio(TipoOperacao.SAIDA, idMedico, inicio, fim, idPessoa,
				dataInicioValidade, dataFimValidade);
	}

}
