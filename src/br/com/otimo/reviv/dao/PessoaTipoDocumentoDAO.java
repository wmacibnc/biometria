package br.com.otimo.reviv.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.PessoaTipoDocumento;

@Component
public class PessoaTipoDocumentoDAO extends GenericoDAO<Long, PessoaTipoDocumento> {

	public PessoaTipoDocumentoDAO(Session session) {
		super(session);
	}

	public List<PessoaTipoDocumento> listarDocumentoPessoa(Long idPessoa) {
		return session.createQuery("From PessoaTipoDocumento ptc where ptc.pessoa.id = :idPessoa ").setParameter("idPessoa", idPessoa).list();
	}

	public void limparListaDocumento(Long idPessoa) {
		session.createQuery("Delete From PessoaTipoDocumento ptc where ptc.pessoa.id = :idPessoa ").setParameter("idPessoa", idPessoa).executeUpdate();
	}

}
