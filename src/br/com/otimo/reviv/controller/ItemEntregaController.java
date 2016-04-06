package br.com.otimo.reviv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.NonUniqueResultException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.blank.IndexController;
import br.com.otimo.reviv.dao.EstoqueDAO;
import br.com.otimo.reviv.dao.ItemEntregaDAO;
import br.com.otimo.reviv.dao.PessoaDAO;
import br.com.otimo.reviv.dao.TratamentoDAO;
import br.com.otimo.reviv.dao.UsuarioDAO;
import br.com.otimo.reviv.interceptor.UsuarioLogado;
import br.com.otimo.reviv.modelo.Estoque;
import br.com.otimo.reviv.modelo.ItemEntrega;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.TipoPessoa;
import br.com.otimo.reviv.modelo.TipoSituacaoTratamento;
import br.com.otimo.reviv.modelo.Tratamento;
import br.com.otimo.reviv.modelo.Usuario;
import br.com.otimo.reviv.sessao.MedicoSessao;
import br.com.otimo.reviv.sessao.PacienteSessao;
import br.com.otimo.reviv.sessao.PessoaEntregaSessao;

@Resource
public class ItemEntregaController {

	private final ItemEntregaDAO dao;
	private final TratamentoDAO tratamentoDAO;
	private final PacienteSessao pacienteSessao;
	private final PessoaEntregaSessao pessoaEntregaSessao;
	private final EmailController emailController;
	private final PessoaDAO pacienteDAO;
	private final UsuarioLogado usuarioLogado;
	private final UsuarioDAO usuarioDAO;
	private final Result result;
	private final OperacaoEstoqueController operacaoEstoqueController;
	private final EstoqueDAO estoqueDAO;
	private final MedicoSessao medicoSessao;

	public ItemEntregaController(ItemEntregaDAO dao, TratamentoDAO tratamentoDAO, EstoqueDAO estoqueDAO,
			PacienteSessao pacienteSessao, PessoaEntregaSessao pessoaEntregaSessao, EmailController emailController,
			PessoaDAO pacienteDAO, UsuarioLogado usuarioLogado, UsuarioDAO usuarioDAO, Result result,
			OperacaoEstoqueController operacaoEstoqueController, MedicoSessao medicoSessao) {

		this.dao = dao;
		this.tratamentoDAO = tratamentoDAO;
		this.pacienteSessao = pacienteSessao;
		this.medicoSessao = medicoSessao;
		this.pessoaEntregaSessao = pessoaEntregaSessao;
		this.emailController = emailController;
		this.pacienteDAO = pacienteDAO;
		this.usuarioLogado = usuarioLogado;
		this.usuarioDAO = usuarioDAO;
		this.result = result;
		this.operacaoEstoqueController = operacaoEstoqueController;
		this.estoqueDAO = estoqueDAO;
	}

	// Lista todos
	@Post("/listaEntregaMedicamento/{situacao}")
	public List<Tratamento> lista(final Pessoa pessoa, Long situacao) {
		pacienteSessao.setPaciente(pacienteDAO.obterPorId(pessoa.getId()));
		result.include("paciente", pacienteSessao.getPaciente());
		result.include("medico", medicoSessao.getMedico());
		return tratamentoDAO.listarTodosTratamentos(pessoa, situacao);
	}

	@Get("/identifica/{id}")
	public void identifica(final Long id) {
		result.include("idTratamento", id);
	}

	@Post("/filtroTratamentoEntrega")
	public void filtroTratamento(Long paciente, Long situacao) {
		Pessoa pacientePersistido = new Pessoa();
		pacientePersistido.setId(paciente);
		lista(pacientePersistido, situacao);
	}

	@Post("/identificaReceber")
	public void identificaUsuario(Usuario usuario, Long idTratamento) {
		Usuario autenticado = null;

		try {
			autenticado = usuarioDAO.obterUsuarioPorSenha(usuario);
		} catch (NonUniqueResultException e) {
		}

		if (autenticado != null) {
			if (autenticado.getPessoa() != null && autenticado.getPessoa().getId() != null) {
				if (autenticado.getPessoa().getTipoPessoa().getId() == TipoPessoa.PACIENTE) {
					// Redireciona para a entrega de paciente
					result.redirectTo(this).listaEntregaPaciente();
					pessoaEntregaSessao.setPessoaEntrega(autenticado.getPessoa());
					pacienteSessao.setPaciente(autenticado.getPessoa());
				} else if (autenticado.getPessoa().getTipoPessoa().getId() == TipoPessoa.ENFERMEIRA) {
					// Redireciona para a entrega de enfermeira
					result.redirectTo(this).listaEntregaEnfermeira();
					pessoaEntregaSessao.setPessoaEntrega(autenticado.getPessoa());
				} else {
					// Redireciona para a tela inicial - informando que somente
					// paciente ou enfermeira podem retirar o medicamento
					result.include("error", "Somente o paciente ou uma enfermeira podem efetuar essa operação.");
					result.redirectTo(IndexController.class).inicio(TipoPessoa.FARMACEUTICO);
				}
			} else {
				result.include("error", "Não foi possivel validar as informações");
				result.redirectTo(IndexController.class).inicio(TipoPessoa.FARMACEUTICO);
			}
		} else {
			result.include("error", "Login e/ou senha invalidos.");
			result.redirectTo(IndexController.class).inicio(TipoPessoa.FARMACEUTICO);
		}

	}

	@Get("/listaEntregaEnfermeira")
	public void listaEntregaEnfermeira() {
		result.include("pessoaList", pacienteDAO.listarTodosPacientes());
	}

	@Get("/listaTratamentoEntregaEnfermeira")
	public void listaTratamentoEntregaEnfermeira() {
		result.include("paciente", pacienteSessao.getPaciente());
		result.include("enfermeira", pessoaEntregaSessao.getPessoaEntrega());

		result.include("tratamentoList", tratamentoDAO.listarTodosTratamentos(pacienteSessao.getPaciente(),
				TipoSituacaoTratamento.EM_ANDAMENTO));
	}

	@Post("/definirPacienteEntrega")
	public void selecionarPacienteEntrega(Long id) {
		pacienteSessao.setPaciente(pacienteDAO.obterPorId(id));
		result.include("paciente", pacienteSessao.getPaciente());
		result.redirectTo(this).listaTratamentoEntregaEnfermeira();
	}

	@Post("/finalizarEntregaMedicamento")
	public List<Tratamento> finalizarEntregaMedicamento(List<ItemEntrega> itemEntregas) {
		List<Tratamento> listaTratamento = new ArrayList<Tratamento>();
		for (ItemEntrega itemEntrega : itemEntregas) {

			if (itemEntrega.getSim() != null && itemEntrega.getSim()) {
				finalizarEntrega(listaTratamento, itemEntrega);
			}
		}

		enviarEmail(listaTratamento);
		result.include("paciente", pacienteSessao.getPaciente());
		result.include("mensagem", "Medicamentos entregues com sucesso!");
		return listaTratamento;
	}

	@Post("/finalizarEntregaMedicamentoPaciente")
	public List<Tratamento> finalizarEntregaMedicamentoPaciente(List<ItemEntrega> itemEntregas) {
		List<Tratamento> listaTratamento = new ArrayList<Tratamento>();

		for (ItemEntrega itemEntrega : itemEntregas) {

			if (itemEntrega.getQuantidadeEntrege() != null) {
				finalizarEntrega(listaTratamento, itemEntrega);
			}

		}

		enviarEmail(listaTratamento);
		result.include("paciente", pacienteSessao.getPaciente());
		result.include("mensagem", "Medicamentos entregues com sucesso!");
		return listaTratamento;
	}

	private void finalizarEntrega(List<Tratamento> listaTratamento, ItemEntrega itemEntrega) {
		final int entregas = itemEntrega.getQuantidadeEntrege();
		Long sessao = dao.obterMaxSessao(itemEntrega.getTratamento().getId());

		if (sessao == null) {
			sessao = 0L;
		}
		Tratamento tratamento = tratamentoDAO.obterPorId(itemEntrega.getTratamento().getId());
		for (int i = 0; i < entregas; i++) {

			ItemEntrega itemEntrega2 = new ItemEntrega();
			itemEntrega2.setDataEntrega(new Date());
			itemEntrega2.setFarmaceutico(usuarioLogado.getUsuario().getPessoa());
			itemEntrega2.setPessoaRecebedora(pessoaEntregaSessao.getPessoaEntrega());
			itemEntrega2.setId(null);
			itemEntrega2.setQuantidadeEntrege((short) 1);
			itemEntrega2.setSessao(++sessao);
			itemEntrega2.setTratamento(itemEntrega.getTratamento());
			dao.novo(itemEntrega2);
			efetuarSaidaEstoqueMedicamento(tratamento);
		}

		listaTratamento.add(tratamento);
	}

	private void efetuarSaidaEstoqueMedicamento(Tratamento tratamento) {
		Estoque estoque = estoqueDAO.obterEstoquePorMedicoMedicamento(tratamento.getMedico().getId(),
				tratamento.getMedicamento().getId());
		operacaoEstoqueController.atualizarEstoque(estoque, (short) 1);
		operacaoEstoqueController.salvarOperacaoEstoqueSaida(estoque, (short) 1,
				"Entrega de Medicamento para " + pessoaEntregaSessao.getPessoaEntrega().getTipoPessoa().getNome());
	}

	private void enviarEmail(List<Tratamento> listaTratamento) {
		try {

			StringBuilder mensagem = new StringBuilder();
			String destinatario = null;
			for (Tratamento tratamento : listaTratamento) {

				destinatario = tratamento.getPaciente().getListaPessoaContatoEletronico().get(0).getEmail();

				mensagem.append("<h4> Entrega de Medicamento </h4>");
				mensagem.append("<p> Tratamento: " + tratamento.getNome() + "</p>");
				mensagem.append("<p> Posologia: " + tratamento.getPosologia().getNome() + "</p>");
				mensagem.append("<p> Total de Tratamentos: " + tratamento.getQuantidadeTotal() + "</p>");
				mensagem.append("<p> Médico: " + tratamento.getMedico().getNome() + "</p>");
				mensagem.append("<p> Médicamento: " + tratamento.getMedicamento().getNome() + "</p>");
				mensagem.append("<br />");
				for (ItemEntrega item : tratamento.getListaItemEntrega()) {

					mensagem.append("<table>");
					mensagem.append("<tr>");
					mensagem.append("<td>Sessão</td>");
					mensagem.append("<td>Data/Hora</td>");
					mensagem.append("<td>Entregue para</td>");
					mensagem.append("</tr>");

					mensagem.append("<tr>");
					mensagem.append("<td>" + item.getSessao() + "</td>");
					mensagem.append("<td>" + item.getDataEntrega() + "</td>");
					mensagem.append("<td>" + item.getPessoaRecebedora().getNome() + " - "
							+ item.getPessoaRecebedora().getTipoPessoa().getNome() + "</td>");
					mensagem.append("</tr>");

					mensagem.append("</table>");

					mensagem.append("<br />");
				}
			}

			emailController.enviarEmail(destinatario, "Clínica Reviv - Entrega de Medicamento", mensagem.toString());

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Get("/listaEntregaPaciente")
	public void listaEntregaPaciente() {
		result.include("paciente", pacienteSessao.getPaciente());
		result.include("medico", medicoSessao.getMedico());
		result.include("tratamentoList", tratamentoDAO.listarTodosTratamentos(pacienteSessao.getPaciente(),
				TipoSituacaoTratamento.EM_ANDAMENTO));
	}
}
