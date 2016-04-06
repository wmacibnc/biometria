package br.com.otimo.reviv.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.otimo.reviv.dao.EstoqueDAO;
import br.com.otimo.reviv.dao.MedicamentoDAO;
import br.com.otimo.reviv.dao.PessoaContatoEletronicoDAO;
import br.com.otimo.reviv.dao.PessoaContatoTelefonicoDAO;
import br.com.otimo.reviv.dao.PessoaDAO;
import br.com.otimo.reviv.dao.PessoaMedicamentoDAO;
import br.com.otimo.reviv.dao.PessoaTipoDocumentoDAO;
import br.com.otimo.reviv.dao.TipoDocumentoDAO;
import br.com.otimo.reviv.dao.TipoPessoaDAO;
import br.com.otimo.reviv.dao.TratamentoDAO;
import br.com.otimo.reviv.dao.UsuarioDAO;
import br.com.otimo.reviv.modelo.Estoque;
import br.com.otimo.reviv.modelo.Medicamento;
import br.com.otimo.reviv.modelo.Perfil;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.PessoaContatoEletronico;
import br.com.otimo.reviv.modelo.PessoaContatoTelefonico;
import br.com.otimo.reviv.modelo.PessoaMedicamento;
import br.com.otimo.reviv.modelo.PessoaTipoDocumento;
import br.com.otimo.reviv.modelo.TipoPessoa;
import br.com.otimo.reviv.modelo.Tratamento;
import br.com.otimo.reviv.modelo.Usuario;

@Resource
public class PessoaController {
	private final PessoaDAO dao;
	private final TipoPessoaDAO tipoPessoaDAO;
	private final TipoDocumentoDAO tipoDocumentoDAO;
	private final PessoaContatoEletronicoDAO pessoaContatoEletronicoDAO;
	private final PessoaContatoTelefonicoDAO pessoaContatoTelefonicoDAO;
	private final PessoaTipoDocumentoDAO pessoaTipoDocumentoDAO;
	private final PessoaMedicamentoDAO pessoaMedicamentoDAO;
	private final TratamentoDAO tratamentoDAO;
	private final MedicamentoDAO medicamentoDAO;
	private final EstoqueDAO estoqueDAO;
	private final UsuarioDAO usuarioDAO;
	private final Result result;
	private final Validator validator;

	private final Integer PAGINAADM = 1;
	private final Integer PAGINATRATAMENTO = 2;
	private final Integer PAGINAAPLICAR = 3;

	public PessoaController(PessoaDAO dao, TipoPessoaDAO tipoPessoaDAO, TipoDocumentoDAO tipoDocumentoDAO,
			PessoaContatoEletronicoDAO pessoaContatoEletronicoDAO,
			PessoaContatoTelefonicoDAO pessoaContatoTelefonicoDAO, PessoaTipoDocumentoDAO pessoaTipoDocumentoDAO,
			PessoaMedicamentoDAO pessoaMedicamentoDAO, TratamentoDAO tratamentoDAO, MedicamentoDAO medicamentoDAO,
			EstoqueDAO estoqueDAO, UsuarioDAO usuarioDAO, Result result, Validator validator) {
		super();
		this.dao = dao;
		this.tipoPessoaDAO = tipoPessoaDAO;
		this.tipoDocumentoDAO = tipoDocumentoDAO;
		this.pessoaContatoEletronicoDAO = pessoaContatoEletronicoDAO;
		this.pessoaContatoTelefonicoDAO = pessoaContatoTelefonicoDAO;
		this.pessoaTipoDocumentoDAO = pessoaTipoDocumentoDAO;
		this.pessoaMedicamentoDAO = pessoaMedicamentoDAO;
		this.tratamentoDAO = tratamentoDAO;
		this.medicamentoDAO = medicamentoDAO;
		this.estoqueDAO = estoqueDAO;
		this.usuarioDAO = usuarioDAO;
		this.result = result;
		this.validator = validator;
	}

	// Lista todos
	@Get("/listaPessoa")
	public List<Pessoa> lista() {
		result.include("tipoPessoaList", tipoPessoaDAO.listarTodos());
		result.include("tipoDocumentoList", tipoDocumentoDAO.listarTodos());
		return dao.listarTodos();
	}

	// Lista todos os pacientes
	@Get("/listaPaciente/{proximaPagina}")
	public List<Pessoa> paciente(Integer proximaPagina) {
		result.include("tipoDocumentoList", tipoDocumentoDAO.listarTodos());
		result.include("pessoTipoMedicamentoList", dao.listarTodosMedicos());
		result.include("proximaPagina", proximaPagina);
		return dao.listarTodosPacientes();
	}

	// Lista todos os tratamento
	@Get("/tratamentos/{id}/{situacao}")
	public List<Tratamento> tratamento(Long id, Long situacao) {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(id);
		result.include("paciente", dao.obterPorId(id));
		result.include("situacao", situacao);
		return tratamentoDAO.listarTodosTratamentos(pessoa, situacao);
	}

	// Lista todos os funcionários
	@Get("/listaFuncionario")
	public List<Pessoa> funcionario() {
		result.include("tipoPessoaList", tipoPessoaDAO.listarTipoPessoaFuncionario());
		result.include("tipoDocumentoList", tipoDocumentoDAO.listarTodos());
		return dao.listarTodosFuncionarios();
	}

	// Lista todos os médicos
	@Get("/listaMedico")
	public List<Pessoa> medico() {
		result.include("tipoPessoaList", tipoPessoaDAO.listarTodos());
		result.include("tipoDocumentoList", tipoDocumentoDAO.listarTodos());
		return dao.listarTodosMedicos();
	}

	// Adicionar
	@Post("/pessoa")
	public void adiciona(final Pessoa pessoa, List<PessoaTipoDocumento> pessoaTipoDocumento,
			List<PessoaContatoTelefonico> pessoaContatoTelefonico,
			List<PessoaContatoEletronico> pessoaContatoEletronico) {
		String retorno = salvarDadosPessoa(pessoa, pessoaTipoDocumento, pessoaContatoTelefonico,
				pessoaContatoEletronico);

		if (retorno != null) {
			result.include("error", retorno);
		} else {
			adicionarMedicamentosAoMedico(pessoa);
			result.include("mensagem", pessoa.getTipoPessoa().getNome() + " cadastrado com sucesso.");
		}

		// Redireciona para a listagem, ap�s a inser��o.
		result.redirectTo(this).lista();
	}

	@Post("/filtroTratamento")
	public void filtroTratamento(Long paciente, Long situacao) {
		tratamento(paciente, situacao);
	}

	private void adicionarMedicamentosAoMedico(final Pessoa pessoa) {
		try {
			if (pessoa.getTipoPessoa().getId() == TipoPessoa.MEDICO) {
				for (Medicamento medicamento : medicamentoDAO.listarTodos()) {
					PessoaMedicamento pessoaMedicamento = preencherPessoaMedicamento(pessoa, medicamento);
					adicionadoEstoque(pessoaMedicamento);
				}
			}
		} catch (Exception e) {
			System.err.println("Erro ao criar o estoque do médico");
		}
	}

	private PessoaMedicamento preencherPessoaMedicamento(final Pessoa pessoa, Medicamento medicamento) {
		PessoaMedicamento pessoaMedicamento = new PessoaMedicamento();
		pessoaMedicamento.setMedicamento(medicamento);
		pessoaMedicamento.setPessoa(pessoa);
		pessoaMedicamento.setDataVencimento(null);
		pessoaMedicamentoDAO.novo(pessoaMedicamento);
		return pessoaMedicamento;
	}

	private void adicionadoEstoque(PessoaMedicamento pessoaMedicamento) {
		Estoque estoque = new Estoque();
		estoque.setPessoaMedicamento(pessoaMedicamento);
		estoque.setQuantidadeDisponivel((short) 0);
		estoqueDAO.novo(estoque);
	}

	// Adicionar Funcionários
	@Post("/funcionarios")
	public void adicionaFuncionario(final Pessoa pessoa, List<PessoaTipoDocumento> pessoaTipoDocumento,
			List<PessoaContatoTelefonico> pessoaContatoTelefonico,
			List<PessoaContatoEletronico> pessoaContatoEletronico) {

		String retorno = salvarDadosPessoa(pessoa, pessoaTipoDocumento, pessoaContatoTelefonico,
				pessoaContatoEletronico);

		if (retorno != null) {
			result.include("error", retorno);
		} else {
			result.include("mensagem", "Funcionário cadastrado com sucesso.");
		}

		// Redireciona para a listagem, apos a inserir
		result.redirectTo(this).funcionario();
	}

	// Adicionar Paciente
	@Post("/paciente")
	public void adicionaPaciente(final Pessoa pessoa, List<PessoaTipoDocumento> pessoaTipoDocumento,
			List<PessoaContatoTelefonico> pessoaContatoTelefonico,
			List<PessoaContatoEletronico> pessoaContatoEletronico, Integer proximaPagina) {

		String retorno = salvarDadosPessoa(pessoa, pessoaTipoDocumento, pessoaContatoTelefonico,
				pessoaContatoEletronico);

		if (retorno != null) {
			result.include("error", retorno);
		} else {
			result.include("mensagem", "Paciente cadastrado com sucesso.");
		}

		if (proximaPagina == PAGINAADM) {
			result.redirectTo(this).paciente(proximaPagina);
		} else if (proximaPagina == PAGINATRATAMENTO) {
			result.redirectTo(this).paciente(2);
		} else if (proximaPagina == PAGINAAPLICAR) {
			result.redirectTo(this).paciente(proximaPagina);
		} else {
			System.err.println("ERRO no redirecionamento da página");
		}
	}

	// Adicionar Medico
	@Post("/medicos")
	public void adicionaMedico(final Pessoa pessoa, List<PessoaTipoDocumento> pessoaTipoDocumento,
			List<PessoaContatoTelefonico> pessoaContatoTelefonico,
			List<PessoaContatoEletronico> pessoaContatoEletronico) {

		String retorno = salvarDadosPessoa(pessoa, pessoaTipoDocumento, pessoaContatoTelefonico,
				pessoaContatoEletronico);

		if (retorno != null) {
			result.include("error", retorno);
		} else {
			adicionarMedicamentosAoMedico(pessoa);
			result.include("mensagem", "Médico cadastrado com sucesso.");
		}

		// Redireciona para a listagem, ap�s a inser��o.
		result.redirectTo(this).medico();
	}

	private String salvarDadosPessoa(final Pessoa pessoa, List<PessoaTipoDocumento> pessoaTipoDocumento,
			List<PessoaContatoTelefonico> pessoaContatoTelefonico,
			List<PessoaContatoEletronico> pessoaContatoEletronico) {

		List<Pessoa> lista = dao.buscaPessoaPorCPF(pessoaTipoDocumento.get(0).getNumeroDocumento());
		if (lista != null && !lista.isEmpty()) {
			return "O CPF informado já existe na base de dados";
		}
		dao.novo(pessoa);

		inserirListaTipoDocumento(pessoa, pessoaTipoDocumento);
		inserirListaContatoTelefonico(pessoa, pessoaContatoTelefonico);
		inserirListaContatoEletronico(pessoa, pessoaContatoEletronico);
		return null;
	}

	// Novo - Gera a interface para cadastro
	@Get("/pessoa/novo")
	public void formulario() {
	}

	// Novo - Gera a interface para cadastro de paciente
	@Get("/pessoa/novoPaciente")
	public void formularioPaciente() {
	}

	// Edi��o
	@Get("/pessoa/{id}/{proximaPagina}")
	public Pessoa edita(Long id, Long proximaPagina) {
		result.include("proximaPagina", proximaPagina);
		result.include("tipoPessoaList", tipoPessoaDAO.listarTodos());
		result.include("pessoaTipoDocumentoList", pessoaTipoDocumentoDAO.listarDocumentoPessoa(id));
		result.include("pessoaContatoTelefonicoList", pessoaContatoTelefonicoDAO.listarPessoaContatoTelefonico(id));
		result.include("pessoaContatoEletronicoList", pessoaContatoEletronicoDAO.listarPessoaContatoTelefonico(id));
		Pessoa pessoa = dao.obterPorId(id);
		try {
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
			result.include("dataNascimentoFormatada", fmt.format(pessoa.getDataNascimento()));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return pessoa;
	}

	// Altera��o - Gera a interface para alterar
	// Envia o ID para alterar
	@Put("/alterarPessoa/{pessoa.id}")
	public void altera(final Pessoa pessoa, List<PessoaTipoDocumento> pessoaTipoDocumento,
			List<PessoaContatoTelefonico> pessoaContatoTelefonico, Integer proximaPagina) {
		dao.atualizar(pessoa);

		pessoaTipoDocumentoDAO.limparListaDocumento(pessoa.getId());
		inserirListaTipoDocumento(pessoa, pessoaTipoDocumento);

		pessoaContatoTelefonicoDAO.limparListaTelefonico(pessoa.getId());
		inserirListaContatoTelefonico(pessoa, pessoaContatoTelefonico);

		// Redireciona para a listagem, ap�s a inser��o.
		if (proximaPagina == 1)
			result.redirectTo(this).lista();

		if (proximaPagina == 2)
			result.redirectTo(this).paciente(1);

		if (proximaPagina == 3)
			result.redirectTo(this).medico();

		if (proximaPagina == 4)
			result.redirectTo(this).funcionario();
	}

	private void inserirListaTipoDocumento(final Pessoa pessoa, List<PessoaTipoDocumento> pessoaTipoDocumento) {
		if (pessoaTipoDocumento == null) {
			return;
		}
		for (PessoaTipoDocumento pessoaDocumento : pessoaTipoDocumento) {
			pessoaDocumento.setPessoa(pessoa);
			pessoaTipoDocumentoDAO.novo(pessoaDocumento);
			pessoa.setListaPessoaTipoDocumento(pessoaTipoDocumento);
		}
	}

	private void inserirListaContatoTelefonico(final Pessoa pessoa,
			List<PessoaContatoTelefonico> pessoaContatoTelefonico) {
		if (pessoaContatoTelefonico == null) {
			return;
		}
		for (PessoaContatoTelefonico contatoTelefonico : pessoaContatoTelefonico) {
			if (contatoTelefonico != null && contatoTelefonico.getNumeroTelefone() != null) {
				contatoTelefonico.setPessoa(pessoa);
				pessoaContatoTelefonicoDAO.novo(contatoTelefonico);
			}
		}
	}

	private void inserirListaContatoEletronico(final Pessoa pessoa,
			List<PessoaContatoEletronico> pessoaContatoEletronico) {
		for (PessoaContatoEletronico contatoEletronico : pessoaContatoEletronico) {
			if (contatoEletronico != null && contatoEletronico.getEmail() != null) {
				contatoEletronico.setPessoa(pessoa);
				pessoaContatoEletronicoDAO.novo(contatoEletronico);

				// Criando usuário de pessoa
				criarUsuario(pessoa, contatoEletronico);
			}
		}
	}

	private void criarUsuario(final Pessoa pessoa, PessoaContatoEletronico contatoEletronico) {
		Usuario usuario = new Usuario();
		usuario.setLogin(pessoa.getListaPessoaTipoDocumento().get(0).getNumeroDocumento());
		usuario.setSenha(contatoEletronico.getSenha());
		usuario.setPerfil(new Perfil(pessoa.getTipoPessoa().getId()));
		usuario.setPessoa(pessoa);
		usuarioDAO.novo(usuario);
	}

	// Remove
	// Envia o ID para Remo��o
	@Delete("/pessoa/{id}")
	public void remove(Long id) {
		Pessoa pessoa = dao.obterPorId(id);

		pessoaTipoDocumentoDAO.limparListaDocumento(pessoa.getId());
		pessoaContatoTelefonicoDAO.limparListaTelefonico(pessoa.getId());
		pessoaContatoEletronicoDAO.limparListaEletronico(pessoa.getId());

		usuarioDAO.excluir(usuarioDAO.obterUsuarioPorPessoa(id));

		dao.excluir(pessoa);
		// Redireciona para a listagem, ap�s a inser��o.
		result.redirectTo(this).lista();
	}

	// Busca por nome
	public List<Pessoa> busca(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca por nome vendas
	public List<Pessoa> busca2(String nome) {
		result.include("nome", nome);
		return dao.busca(nome);
	}

	// Busca por nome vendas
	public List<Pessoa> buscaPaciente(String nome) {
		result.include("nome", nome);
		return dao.buscaPaciente(nome);
	}

	// Busca Json para auto completar
	@Get("/pessoa/busca.json")
	public void buscaJson(String q) {
		result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id").serialize();
	}

	@Get("/biometria/{id}")
	public void biometria(Long id) {
		result.include("idUsuario", usuarioDAO.obterUsuarioPorPessoa(id).getId());
	}

}
