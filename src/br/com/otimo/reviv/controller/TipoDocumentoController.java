package br.com.otimo.reviv.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.otimo.reviv.dao.TipoDocumentoDAO;
import br.com.otimo.reviv.modelo.TipoDocumento;

@Resource
public class TipoDocumentoController {
	private final TipoDocumentoDAO dao;
	private final Result result;

	public TipoDocumentoController(TipoDocumentoDAO dao, Result result) {
		this.dao = dao;
		this.result = result;
	}

	// Lista todos
	@Get("/listaTipoDocumento")
	public List<TipoDocumento> lista() {
		return dao.listarTodos();
	}

	// Adicionar
	@Post("/tipoDocumento")
	public void adiciona(final TipoDocumento tipoDocumento) {
		dao.novo(tipoDocumento);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Novo - Gera a interface para cadastro
	@Get("/tipoDocumento/novo")
	public void formulario() {
	}

	// Edição
	@Get("/tipoDocumento/{id}")
	public TipoDocumento edita(Long id) {
		return dao.obterPorId(id);
	}

	// Alteração - Gera a interface para alterar
	// Envia o ID para alterar
	@Put("/tipoDocumento/{tipoDocumento.id}")
	public void altera(final TipoDocumento tipoDocumento) {
		dao.atualizar(tipoDocumento);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Remove
	// Envia o ID para Remoção
	@Delete("/tipoDocumento/{id}")
	public void remove(Long id) {
		TipoDocumento tipoDocumentos = dao.obterPorId(id);
		dao.excluir(tipoDocumentos);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<TipoDocumento> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca por nome vendas
	public List<TipoDocumento> busca2(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca Json para auto completar
	@Get("/tipoDocumento/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id").serialize();
	}

}
