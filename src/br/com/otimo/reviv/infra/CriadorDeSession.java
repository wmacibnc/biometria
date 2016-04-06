package br.com.otimo.reviv.infra;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;
import org.hibernate.SessionFactory;
@Component
public class CriadorDeSession implements ComponentFactory<Session>{
	
	private SessionFactory factory;
	private Session session;
	
	public CriadorDeSession(SessionFactory factory){
		this.factory = factory;
	}
	@PostConstruct
	public void abre(){
		this.session = factory.openSession();
	}
	public Session getInstance(){
			return this.session;
	}
	@PreDestroy
	public void fecha(){
		this.session.close();
	}
	}


