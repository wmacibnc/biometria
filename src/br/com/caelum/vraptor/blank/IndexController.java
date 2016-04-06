
package br.com.caelum.vraptor.blank;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.otimo.reviv.controller.LoginController;
import br.com.otimo.reviv.dao.PerfilFuncionalidadeDAO;
import br.com.otimo.reviv.dao.PessoaDAO;
import br.com.otimo.reviv.interceptor.UsuarioLogado;
import br.com.otimo.reviv.modelo.Funcionalidade;

@Resource
public class IndexController {

	private final Result result;
	private final PessoaDAO pessoaDAO;
	private final PerfilFuncionalidadeDAO perfilFuncionalidadeDAO;
	private final UsuarioLogado usuarioLogado;

	public IndexController(Result result, PessoaDAO pessoaDAO, PerfilFuncionalidadeDAO perfilFuncionalidadeDAO,
			UsuarioLogado usuarioLogado) {
		super();
		this.result = result;
		this.pessoaDAO = pessoaDAO;
		this.perfilFuncionalidadeDAO = perfilFuncionalidadeDAO;
		this.usuarioLogado = usuarioLogado;
	}

	@Path("/")
	public void index() {
		result.redirectTo(LoginController.class).formulario();
	}

	@Path("/sair")
	public void sair() {

	}

	@Path("/comercial")
	public void comercial() {
		result.include("pessoTipoMedicamentoList", pessoaDAO.listarTodosMedicos());
	}

	@Path("/medicoAdm")
	public void medicoAdm() {
		result.include("medico", usuarioLogado.getUsuario().getPessoa());
	}

	@Path("/comercialAdm")
	public void comercialAdm() {
	}

	@Path("/relatorioComercial")
	public void relatorioComercial() {
		result.include("pessoaList", pessoaDAO.listarTodosMedicos());
	}

	@Path("/relatorioFinanceiro")
	public void relatorioFinanceiro() {
		result.include("pessoaList", pessoaDAO.listarTodosMedicos());
	}

	@Path("/relatorioMedico")
	public void relatorioMedico() {
		result.include("pessoaList", pessoaDAO.listarTodosMedicos());
	}

	@Get("/telaInicial")
	public List<Funcionalidade> inicio(Long idPerfil) {
		result.include("pessoTipoMedicamentoList", pessoaDAO.listarTodosMedicos());
		result.include("usuario", usuarioLogado.getUsuario());
		if (idPerfil == null) {
			idPerfil = usuarioLogado.getUsuario().getPerfil().getId();
		}
		List<Funcionalidade> listaFuncionalidade = perfilFuncionalidadeDAO.listarTodos(idPerfil);

		if (listaFuncionalidade != null) {
			result.include("total", listaFuncionalidade.size());
		}
		return listaFuncionalidade;
	}

}
