package br.com.otimo.reviv.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.math.BigDecimal;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.otimo.reviv.dao.EstoqueDAO;
import br.com.otimo.reviv.dao.MedicamentoDAO;
import br.com.otimo.reviv.dao.PessoaDAO;
import br.com.otimo.reviv.dao.PessoaMedicamentoDAO;
import br.com.otimo.reviv.modelo.Estoque;
import br.com.otimo.reviv.modelo.Medicamento;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.PessoaMedicamento;

@Resource
public class MedicamentoController {
	private final MedicamentoDAO dao;
	private final PessoaDAO pessoaDAO;
	private final PessoaMedicamentoDAO pessoaMedicamentoDAO;
	private final EstoqueDAO estoqueDAO;
	private final Result result;

	public MedicamentoController(MedicamentoDAO dao, PessoaDAO pessoaDAO, PessoaMedicamentoDAO pessoaMedicamentoDAO,
			EstoqueDAO estoqueDAO, Result result) {
		this.dao = dao;
		this.pessoaDAO = pessoaDAO;
		this.pessoaMedicamentoDAO = pessoaMedicamentoDAO;
		this.estoqueDAO = estoqueDAO;
		this.result = result;
	}

	// Lista todos
	@Get("/listaMedicamento")
	public List<Medicamento> lista() {
		return dao.listarTodos();
	}

	// Adicionar
	@Post("/medicamento")
	public void adiciona(final Medicamento medicamento, String precoCusto, String precoVenda) {
		medicamento.setPrecoCusto(retornarValorBigDecimal(precoCusto));
		medicamento.setPrecoVenda(retornarValorBigDecimal(precoVenda));
		dao.novo(medicamento);

		for (Pessoa medico : pessoaDAO.listarTodosMedicos()) {
			PessoaMedicamento pessoaMedicamento = adicionandoPessoaMedicamento(medicamento, medico);
			adicionadoEstoque(pessoaMedicamento);
		}

		// Redireciona para a listagem, ap�s a inser��o.
		result.redirectTo(this).lista();
	}

	private PessoaMedicamento adicionandoPessoaMedicamento(final Medicamento medicamento, Pessoa medico) {
		PessoaMedicamento pessoaMedicamento = new PessoaMedicamento();
		pessoaMedicamento.setDataVencimento(null);
		pessoaMedicamento.setMedicamento(medicamento);
		pessoaMedicamento.setPessoa(medico);
		pessoaMedicamentoDAO.novo(pessoaMedicamento);
		return pessoaMedicamento;
	}

	private void adicionadoEstoque(PessoaMedicamento pessoaMedicamento) {
		Estoque estoque = new Estoque();
		estoque.setPessoaMedicamento(pessoaMedicamento);
		estoque.setQuantidadeDisponivel((short) 0);
		estoqueDAO.novo(estoque);
	}

	// Novo - Gera a interface para cadastro
	@Get("/medicamento/novo")
	public void formulario() {
	}

	// Edi��o
	@Get("/medicamento/{id}")
	public Medicamento edita(Long id) {
		return dao.obterPorId(id);
	}

	// Altera��o - Gera a interface para alterar
	// Envia o ID para alterar
	@Put("/medicamento/{medicamento.id}")
	public void altera(final Medicamento medicamento, String precoCusto, String precoVenda) {
		medicamento.setPrecoCusto(retornarValorBigDecimal(precoCusto));
		medicamento.setPrecoVenda(retornarValorBigDecimal(precoVenda));
		dao.atualizar(medicamento);
		// Redireciona para a listagem, ap�s a inser��o.
		result.redirectTo(this).lista();
	}

	private BigDecimal retornarValorBigDecimal(String preco) {
		return new BigDecimal(preco.replace(".", "").replace(",", "."));
	}

	// Remove
	// Envia o ID para Remo��o
	@Delete("/medicamento/{id}")
	public void remove(Long id) {
		Medicamento medicamentos = dao.obterPorId(id);
		dao.excluir(medicamentos);
		// Redireciona para a listagem, ap�s a inser��o.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<Medicamento> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca por nome vendas
	public List<Medicamento> busca2(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca Json para auto completar
	@Get("/medicamento/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id").serialize();
	}

}
