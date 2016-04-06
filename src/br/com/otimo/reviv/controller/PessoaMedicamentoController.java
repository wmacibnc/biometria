package br.com.otimo.reviv.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.otimo.reviv.dao.EstoqueDAO;
import br.com.otimo.reviv.dao.MedicamentoDAO;
import br.com.otimo.reviv.dao.OperacaoEstoqueDAO;
import br.com.otimo.reviv.dao.PessoaDAO;
import br.com.otimo.reviv.dao.PessoaMedicamentoDAO;
import br.com.otimo.reviv.modelo.Estoque;
import br.com.otimo.reviv.modelo.OperacaoEstoque;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.PessoaMedicamento;
import br.com.otimo.reviv.modelo.TipoOperacao;
import br.com.otimo.reviv.sessao.MedicoSessao;

@Resource
public class PessoaMedicamentoController {
	private static final Short VALOR_ZERO = 0;
	private final PessoaMedicamentoDAO dao;
	private final PessoaDAO pessoaDAO;
	private final MedicamentoDAO medicamentoDAO;
	private final EstoqueDAO estoqueDAO;
	private final OperacaoEstoqueDAO operacaoEstoqueDAO;
	private final MedicoSessao medicoSessao;
	private final Result result;

	public PessoaMedicamentoController(PessoaMedicamentoDAO dao, PessoaDAO pessoaDAO, MedicamentoDAO medicamentoDAO,
			EstoqueDAO estoqueDAO, OperacaoEstoqueDAO operacaoEstoqueDAO, MedicoSessao medicoSessao, Result result) {
		this.dao = dao;
		this.pessoaDAO = pessoaDAO;
		this.medicamentoDAO = medicamentoDAO;
		this.estoqueDAO = estoqueDAO;
		this.operacaoEstoqueDAO = operacaoEstoqueDAO;
		this.medicoSessao = medicoSessao;
		this.result = result;
	}

	// Lista todos
	@Get("/listaPessoaMedicamento")
	public List<Estoque> lista() {
		result.include("medico", medicoSessao.getMedico());
		result.include("medicamentoList", medicamentoDAO.listarTodos());
		return estoqueDAO.listarTodos(medicoSessao.getMedico().getId());
	}

	@Get("/listaMedicos")
	public void listaMedicos() {
	}

	// Adicionar
	@Post("/pessoaMedicamento")
	public void adiciona(final PessoaMedicamento pessoaMedicamento, final Long quantidadeDisponivel) {

		dao.novo(pessoaMedicamento);
		preencherDadosEstoque(pessoaMedicamento, quantidadeDisponivel.shortValue());

		// Redireciona para a listagem, ap�s a inser��o.
		result.redirectTo(this).lista();
	}

	private void preencherDadosEstoque(final PessoaMedicamento pessoaMedicamento, final Short quantidadeDisponivel) {
		Estoque estoque = gravarDadosEstoque(pessoaMedicamento, quantidadeDisponivel);
		gravarDadosOperacaoEstoque(quantidadeDisponivel, estoque);
	}

	private void gravarDadosOperacaoEstoque(final Short quantidadeDisponivel, Estoque estoque) {
		OperacaoEstoque operacaoEstoque = new OperacaoEstoque();
		operacaoEstoque.setEstoque(estoque);
		operacaoEstoque.setTipoOperacao(new TipoOperacao(TipoOperacao.ENTRADA));
		operacaoEstoque.setQuantidadeDisponivelAnterior(VALOR_ZERO);
		operacaoEstoque.setQuantidadeDisponivelPosterior(quantidadeDisponivel);
		operacaoEstoqueDAO.novo(operacaoEstoque);
	}

	private Estoque gravarDadosEstoque(final PessoaMedicamento pessoaMedicamento, final Short quantidadeDisponivel) {
		Estoque estoque = new Estoque();
		estoque.setPessoaMedicamento(pessoaMedicamento);
		estoque.setQuantidadeDisponivel(quantidadeDisponivel);
		estoqueDAO.novo(estoque);
		return estoque;
	}

	@Post("definirMedico")
	public void definirMedico(Pessoa pessoa) {
		if (pessoa != null && pessoa.getId() != null) {
			medicoSessao.setMedico(pessoaDAO.obterPorId(pessoa.getId()));
			result.redirectTo(this).lista();
		} else {
			return;
		}
	}

	// Novo - Gera a interface para cadastro
	@Get("/pessoaMedicamento/novo")
	public void formulario() {
	}

	// Edi��o
	@Get("/pessoaMedicamento/{id}")
	public PessoaMedicamento edita(Long id) {
		result.include("medico", medicoSessao.getMedico());
		result.include("medicamentoList", medicamentoDAO.listarTodos());
		return dao.obterPorId(id);
	}

	// Altera��o - Gera a interface para alterar
	// Envia o ID para alterar
	@Put("/pessoaMedicamento/{pessoaMedicamento.id}")
	public void altera(final PessoaMedicamento pessoaMedicamento) {
		pessoaMedicamento.setPessoa(medicoSessao.getMedico());
		dao.atualizar(pessoaMedicamento);
		// Redireciona para a listagem, ap�s a inser��o.
		result.redirectTo(this).lista();
	}

	// Remove
	// Envia o ID para Remo��o
	@Delete("/pessoaMedicamento/{id}")
	public void remove(Long id) {
		PessoaMedicamento pessoaMedicamentos = dao.obterPorId(id);
		dao.excluir(pessoaMedicamentos);
		// Redireciona para a listagem, ap�s a inser��o.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<PessoaMedicamento> busca(String nome) {
		result.include("nome", nome);
		return dao.busca2(nome);
	}

	// Busca por nome vendas
	public List<PessoaMedicamento> busca2(String nome) {
		result.include("nome", nome);
		return dao.busca2(nome);
	}

	// Busca Json para auto completar
	@Get("/pessoaMedicamento/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(medicamentoDAO.busca(q)).exclude("id").serialize();
	}

}
