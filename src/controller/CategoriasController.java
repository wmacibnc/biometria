package controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.CategoriasDAO;
import modelo.Categorias;

@Resource
public class CategoriasController {
	private final CategoriasDAO dao;
	private final Result result;

	public CategoriasController(CategoriasDAO dao, Result result) {
		this.dao = dao;
		this.result = result;
	}

	// Lista todos
	@Get("/categorias")
	public List<Categorias> lista() {
		return dao.listarTodos();
	}

	// Adicionar
	@Post("/categorias")
	public void adiciona(final Categorias categorias) {
		dao.novo(categorias);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Novo - Gera a interface para cadastro

	@Get("/categorias/novo")
	public void formulario() {
	}

	// Edição

	@Get("/categorias/{id}")
	public Categorias edita(Long id) {
		return dao.obterPorId(id);
	}

	// Alteração - Gera a interface para alterar
	// Envia o ID para alterar

	@Put("/categorias/{categorias.id}")
	public void altera(final Categorias categorias) {
		dao.atualizar(categorias);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Remove
	// Envia o ID para Remoção
	@Delete("/categorias/{id}")
	public void remove(Long id) {
		Categorias categorias = dao.obterPorId(id);
		dao.excluir(categorias);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<Categorias> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca Json para auto completar
	@Get("/categorias/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id").serialize();
	}

}
