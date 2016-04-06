package br.com.otimo.reviv.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.otimo.reviv.dao.PerfilDAO;
import br.com.otimo.reviv.modelo.Perfil;

@Resource
public class PerfilController {
	private final PerfilDAO dao;
	private final Result result;

	public PerfilController(PerfilDAO dao, Result result) {
		this.dao = dao;
		this.result = result;
	}

	// Lista todos
	@Get("/listaPerfil")
	public List<Perfil> lista() {
		return dao.listarTodos();
	}

	// Adicionar
	@Post("/perfil")
	public void adiciona(final Perfil perfil) {
		dao.novo(perfil);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Novo - Gera a interface para cadastro
	@Get("/perfil/novo")
	public void formulario() {
	}

	// Edição
	@Get("/perfil/{id}")
	public Perfil edita(Long id) {
		return dao.obterPorId(id);
	}

	// Alteração - Gera a interface para alterar
	// Envia o ID para alterar
	@Put("/perfil/{perfil.id}")
	public void altera(final Perfil perfil) {
		dao.atualizar(perfil);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Remove
	// Envia o ID para Remoção
	@Delete("/perfil/{id}")
	public void remove(Long id) {
		Perfil perfils = dao.obterPorId(id);
		dao.excluir(perfils);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<Perfil> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca por nome vendas
	public List<Perfil> busca2(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca Json para auto completar
	@Get("/perfil/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id").serialize();
	}

}
