package br.com.otimo.reviv.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.otimo.reviv.dao.FuncionalidadeDAO;
import br.com.otimo.reviv.modelo.Funcionalidade;

@Resource
public class FuncionalidadeController {
	private final FuncionalidadeDAO dao;
	private final Result result;

	public FuncionalidadeController(FuncionalidadeDAO dao, Result result) {
		this.dao = dao;
		this.result = result;
	}

	// Lista todos
	@Get("/listaFuncionalidade")
	public List<Funcionalidade> lista() {
		return dao.listarTodos();
	}

	// Adicionar
	@Post("/funcionalidade")
	public void adiciona(final Funcionalidade funcionalidade) {
		dao.novo(funcionalidade);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Novo - Gera a interface para cadastro
	@Get("/funcionalidade/novo")
	public void formulario() {
	}

	// Edição
	@Get("/funcionalidade/{id}")
	public Funcionalidade edita(Long id) {
		return dao.obterPorId(id);
	}

	// Alteração - Gera a interface para alterar
	// Envia o ID para alterar
	@Put("/funcionalidade/{funcionalidade.id}")
	public void altera(final Funcionalidade funcionalidade) {
		dao.atualizar(funcionalidade);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Remove
	// Envia o ID para Remoção
	@Delete("/funcionalidade/{id}")
	public void remove(Long id) {
		Funcionalidade funcionalidades = dao.obterPorId(id);
		dao.excluir(funcionalidades);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<Funcionalidade> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca por nome vendas
	public List<Funcionalidade> busca2(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca Json para auto completar
	@Get("/funcionalidade/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id").serialize();
	}

}
