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
import dao.ProdutosDAO;
import modelo.Produtos;

@Resource
public class ProdutosController {
	private final ProdutosDAO dao;
	private final CategoriasDAO dao2;
	private final Result result;

	public ProdutosController(ProdutosDAO dao, Result result, CategoriasDAO dao2) {
		this.dao = dao;
		this.dao2 = dao2;
		this.result = result;
	}

	// Lista todos
	@Get("/produtos")
	public List<Produtos> lista() {
		return dao.listaTudo();
	}

	// Lista Venda
	@Get("/produtos/vendas")
	public List<Produtos> vendas() {
		return dao.listaTudo();
	}

	// Adicionar
	@Post("/produtos")
	public void adiciona(final Produtos produtos) {
		dao.salva(produtos);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Novo - Gera a interface para cadastro

	@Get("/produtos/novo")
	public void formulario() {
		result.include("categoriasList", dao2.listarTodos());
	}

	// Edição

	@Get("/produtos/{id}")
	public Produtos edita(Long id) {
		return dao.carrega(id);
	}

	// Alteração - Gera a interface para alterar
	// Envia o ID para alterar

	@Put("/produtos/{produtos.id}")
	public void altera(final Produtos produtos) {
		dao.atualiza(produtos);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Remove
	// Envia o ID para Remoção
	@Delete("/produtos/{id}")
	public void remove(Long id) {
		Produtos produtos = dao.carrega(id);
		dao.remove(produtos);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<Produtos> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca por nome
	public List<Produtos> busca2(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca Json para auto completar
	@Get("/produtos/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id").serialize();
	}

}
