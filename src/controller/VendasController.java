package controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import modelo.Vendas;
import modelo.VendasEmProgresso;

import dao.ItensDAO;
import dao.VendasDAO;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class VendasController {
		private final VendasDAO dao;
		private final ItensDAO dao2;
		private final Result result;

		public VendasController(VendasDAO dao,Result result,
				VendasEmProgresso vendasEmProgresso, ItensDAO dao2) {
		this.dao = dao;
		this.dao2 = dao2;
		this.result = result;
		}
		
		//Lista todos
		@Get("/vendas")
		public List<Vendas> lista() {
		return dao.listaTudo();
		 }
		
		//Finalizar Venda
		@Get("/vendas/confirmar/{id}")
		public List<Vendas> finalizar(Long id) {
			
			//Include lista de Itens
			result.include("itensList",dao2.listaTudo());
			// Retirar da sessão
			VendasEmProgresso.setVendas(null);
			return dao.finalizar(id);
		}
		
		//Adicionar
		@Post("/vendas")
		public void adiciona(final Vendas vendas) {
			dao.salva(vendas);
			
			// Colocando o Vendas na sessão
			VendasEmProgresso.setVendas(vendas);
			
			//Redireciona para a lista de vendas de produtos
			result.redirectTo(ProdutosController.class).vendas();
		}
		
		//Novo - Gera a interface para cadastro
		@Get("/vendas/novo")
		public void formulario() {
		}
		
		//Edição
		@Get("/vendas/{id}")
		public Vendas edita(Long id) {
		return dao.carrega(id);
		}
		//Alteração - Gera a interface para alterar
		//Envia o ID para alterar
		
		@Put("/vendas/{vendas.id}")
		public void altera(final Vendas vendas) {
			dao.atualiza(vendas);
			//Redireciona para a listagem, após a inserção.
			result.redirectTo(this).finalizar(vendas.getId());
		}
		
		//Remove
		//Envia o ID para Remoção
		@Delete("/vendas/{id}")
		public void remove(Long id) {
		Vendas vendas = dao.carrega(id);
		dao.remove(vendas);
			//Redireciona para a listagem, após a inserção.
		result.redirectTo(this).lista();
		}

		//Busca por nome
		public List<Vendas> busca(String nome) {
			result.include("nome", nome);
			return dao.busca(nome);
			}
		
		//Busca Json para auto completar 
		@Get("/vendas/busca.json")
		public void buscaJson(String q) {
		result.use(json()).withoutRoot()
		.from(dao.busca(q))
		.exclude("id")
		.serialize();
		}
		
	
}
