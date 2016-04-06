package controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import modelo.Clientes;

import dao.ClientesDAO;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ClientesController {
	private final ClientesDAO dao;
	private final Result result;

	public ClientesController(ClientesDAO dao, Result result) {
		this.dao = dao;
		this.result = result;
	}

	// Lista todos
	@Get("/clientes")
	public List<Clientes> lista() {
		return dao.listaTudo();
	}

	// Lista todos vendas
	@Get("/clientes/vendas")
	public List<Clientes> lista2() {
		return dao.listaTudo();
	}

	// Adicionar
	@Post("/clientes")
	public void adiciona(final Clientes clientes) {
		dao.salva(clientes);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Novo - Gera a interface para cadastro
	@Get("/clientes/novo")
	public void formulario() {
	}

	// Edição
	@Get("/clientes/{id}")
	public Clientes edita(Long id) {
		return dao.carrega(id);
	}

	// Alteração - Gera a interface para alterar
	// Envia o ID para alterar
	@Put("/clientes/{clientes.id}")
	public void altera(final Clientes clientes) {
		dao.atualiza(clientes);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Remove
	// Envia o ID para Remoção
	@Delete("/clientes/{id}")
	public void remove(Long id) {
		Clientes clientes = dao.carrega(id);
		dao.remove(clientes);
		// Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<Clientes> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca por nome vendas
	public List<Clientes> busca2(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca Json para auto completar
	@Get("/clientes/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id")
				.serialize();
	}

}
