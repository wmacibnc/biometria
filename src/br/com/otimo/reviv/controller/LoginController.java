package br.com.otimo.reviv.controller;

import org.hibernate.NonUniqueResultException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.blank.IndexController;
import br.com.otimo.reviv.dao.FuncionalidadeDAO;
import br.com.otimo.reviv.dao.PessoaDAO;
import br.com.otimo.reviv.dao.UsuarioDAO;
import br.com.otimo.reviv.interceptor.UsuarioLogado;
import br.com.otimo.reviv.modelo.TipoPessoa;
import br.com.otimo.reviv.modelo.Usuario;

@Resource
public class LoginController {

	private final FuncionalidadeDAO funcionalidadeDAO;
	private final UsuarioDAO usuarioDAO;
	private final UsuarioLogado usuarioLogado;
	private final Result result;
	private final PessoaDAO pessoaDAO;

	public LoginController(FuncionalidadeDAO funcionalidadeDAO, UsuarioDAO usuarioDAO, UsuarioLogado usuarioLogado,
			Result result, PessoaDAO pessoaDAO) {
		super();
		this.funcionalidadeDAO = funcionalidadeDAO;
		this.usuarioDAO = usuarioDAO;
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.pessoaDAO = pessoaDAO;
	}

	public void autentica(Usuario usuario) {
		Usuario autenticado = null;

		try {
			autenticado = usuarioDAO.obterUsuarioPorSenha(usuario);
		} catch (NonUniqueResultException e) {
		}

		if (autenticado != null) {
			if (autenticado.getPessoa() != null && autenticado.getPessoa().getTipoPessoa() != null
					&& autenticado.getPessoa().getTipoPessoa().getId() != null
					&& autenticado.getPessoa().getTipoPessoa().getId() == TipoPessoa.PACIENTE) {
				result.include("error",
						"Perfil de Paciente não tem permissão para acessar as funcionalidades do sistema.");
				usuarioLogado.logout();
				result.redirectTo(LoginController.class).formulario();
				return;
			}
			usuarioLogado.efetuaLogin(autenticado);
			result.redirectTo(IndexController.class).inicio(usuarioLogado.getUsuario().getPerfil().getId());
			return;
		} else {
			result.include("error", "Login e senha invalidos.");
		}
		result.redirectTo(LoginController.class).formulario();
	}

	@Get("/autenticaBiometria/{hashCode}")
	public void autenticaBiometria(String hashCode) {
		Usuario usuario = null;

		try {
			usuario = usuarioDAO.obterUsuarioPorHashCode(hashCode);
			usuarioDAO.excluirHashCode(hashCode);
			if (usuario == null) {
				usuario = usuarioVazio();
			}

		} catch (Exception e) {
			usuario = usuarioVazio();
		}

		autentica(usuario);
	}

	private Usuario usuarioVazio() {
		Usuario usuario;
		usuario = new Usuario();
		usuario.setLogin("xpto");
		usuario.setSenha("xpto");
		return usuario;
	}

	public void formulario() {
		usuarioLogado.logout();
	}

	@Path("/logout")
	public void logout() {
		usuarioLogado.logout();
		result.redirectTo(this).formulario();
	}

	@Post("/adicionarBiometria")
	public void adicionarBiometria(String idUsuario, String hashCode) {
		pessoaDAO.inserirBiometria(Long.parseLong(idUsuario), hashCode);
	}
}
