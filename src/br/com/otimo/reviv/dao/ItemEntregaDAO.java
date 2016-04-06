package br.com.otimo.reviv.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.otimo.reviv.modelo.ItemEntrega;

@Component
public class ItemEntregaDAO extends GenericoDAO<Long, ItemEntrega> {

    public ItemEntregaDAO(Session session) {
        super(session);
    }

    public List<ItemEntrega> listarTodosParaAplicar(Long idTratamento, Long idPaciente) {

        StringBuilder consulta = new StringBuilder();

        consulta.append("select new ItemEntrega (it ,ia.quantidadeAplicada) ");
        consulta.append(" FROM ItemEntrega ia ");
        consulta.append(" right join ia.itemTratamento it ");
        consulta.append(" where it.tratamento.id = :idTratamento");
        consulta.append(" and it.tratamento.paciente.id = :idPaciente");
        consulta.append(" group by it,ia.quantidadeAplicada");

        return session.createQuery(consulta.toString()).setParameter("idTratamento", idTratamento)
                .setParameter("idPaciente", idPaciente).list();
    }

    public Long obterMaxSessao(Long idTratamento) {

        StringBuilder consulta = new StringBuilder();

        consulta.append("select max(ie.sessao) from ItemEntrega ie");
        consulta.append(" where ie.tratamento.id = :idTratamento");

        return (Long) session.createQuery(consulta.toString()).setParameter("idTratamento", idTratamento)
                .uniqueResult();
    }

}
