package br.com.otimo.reviv.interceptor;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.otimo.reviv.modelo.Usuario;

@Component
@SessionScoped
public class UsuarioLogado {
	private Usuario usuarioLogado;

	public void efetuaLogin(Usuario usuario) {
		this.usuarioLogado = usuario;
	}

	public Usuario getUsuario() {
		return this.usuarioLogado;
	}

	public void logout() {
		this.usuarioLogado = null;
	}
}