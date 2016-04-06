package controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import modelo.Itens;

import dao.ItensDAO;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ItensController {
	private final ItensDAO dao;
	private final Result result;

	public ItensController(ItensDAO dao, Result result) {
		this.dao = dao;
		this.result = result;
	}

	// Lista todos
	@Get("/itens")
	public List<Itens> lista() {
		return dao.listaTudo();
	}

	// Adicionar
	@Post("/itens")
	public void adiciona(final Itens itens) {
		dao.salva(itens);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Novo - Gera a interface para cadastro
	@Get("/itens/novo")
	public void formulario() {
	}

	// Edição
	@Get("/itens/{id}")
	public Itens edita(Long id) {
		return dao.carrega(id);
	}

	// Alteração - Gera a interface para alterar
	// Envia o ID para alterar
	@Put("/itens/{itens.id}")
	public void altera(final Itens itens) {
		dao.atualiza(itens);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Remove
	// Envia o ID para Remoção
	@Delete("/itens/{id}")
	public void remove(Long id) {
		Itens itens = dao.carrega(id);
		dao.remove(itens);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<Itens> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca Json para auto completar
	@Get("/itens/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id")
				.serialize();
	}

}
