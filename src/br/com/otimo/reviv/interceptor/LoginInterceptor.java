package br.com.otimo.reviv.interceptor;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceClass;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.otimo.reviv.controller.LoginController;

@Intercepts
public class LoginInterceptor implements Interceptor {

	private UsuarioLogado usuarioLogado;
	private Result result;
	private final HttpServletRequest request;

	public LoginInterceptor(UsuarioLogado usuarioLogado, Result result, HttpServletRequest request) {
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.request = request;
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {

		if (usuarioLogado.getUsuario() != null) {
			result.include("usuarioLogado", usuarioLogado);
			stack.next(method, instance);
			System.out.println("URI" + request.getRequestURI());
			if (request.getRequestURI().contains("Ini")) {
				System.out.println("ok");
			}
			System.out.println("URL" + request.getRequestURL());
		} else {
			result.redirectTo(LoginController.class).formulario();
		}
	}

	public boolean accepts(ResourceMethod method) {
		ResourceClass resource = method.getResource();
		return !resource.getType().isAssignableFrom(LoginController.class);
	}
}
