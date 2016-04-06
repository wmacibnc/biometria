package modelo;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component  
@SessionScoped  
public class VendasEmProgresso {
	private static Vendas svendas;

	public Vendas getVendas() {
		return svendas;
	}

	public static void setVendas(Vendas vendas) {
		svendas = vendas;
	}	
}