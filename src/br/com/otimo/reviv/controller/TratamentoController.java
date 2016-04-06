package br.com.otimo.reviv.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.blank.IndexController;
import br.com.otimo.reviv.dao.EstoqueDAO;
import br.com.otimo.reviv.dao.MedicamentoDAO;
import br.com.otimo.reviv.dao.PessoaDAO;
import br.com.otimo.reviv.dao.PosologiaDAO;
import br.com.otimo.reviv.dao.TratamentoDAO;
import br.com.otimo.reviv.modelo.Estoque;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.TipoSituacaoTratamento;
import br.com.otimo.reviv.modelo.Tratamento;
import br.com.otimo.reviv.sessao.MedicoSessao;
import br.com.otimo.reviv.sessao.PacienteSessao;
import br.com.otimo.reviv.sessao.TratamentoSessao;

@Resource
public class TratamentoController {
    private final TratamentoDAO dao;
    private final PessoaDAO pessoaDAO;
    private final MedicoSessao medicoSessao;
    private final PacienteSessao pacienteSessao;

    private final MedicamentoDAO medicamentoDAO;
    private final PosologiaDAO posologiaDAO;
    private final EstoqueDAO estoqueDAO;
    private final TratamentoSessao tratamentoSessao;
    private final EmailController emailController;
    private final Result result;

    public TratamentoController(TratamentoDAO dao, PessoaDAO pessoaDAO, MedicoSessao medicoSessao,
            PacienteSessao pacienteSessao, MedicamentoDAO medicamentoDAO, PosologiaDAO posologiaDAO,
            EstoqueDAO estoqueDAO, TratamentoSessao tratamentoSessao, EmailController emailController, Result result) {
        this.dao = dao;
        this.pessoaDAO = pessoaDAO;

        this.medicoSessao = medicoSessao;
        this.pacienteSessao = pacienteSessao;
        this.medicamentoDAO = medicamentoDAO;
        this.posologiaDAO = posologiaDAO;
        this.estoqueDAO = estoqueDAO;
        this.tratamentoSessao = tratamentoSessao;
        this.emailController = emailController;
        this.result = result;
    }

    // Lista todos
    @Post("/listaTratamento")
    public void lista(Pessoa pessoa, Long qtd) {
        if (pessoa != null && pessoa.getId() != null) {
            pacienteSessao.setPaciente(pessoaDAO.obterPorId(pessoa.getId()));
        }
        result.include("paciente", pacienteSessao.getPaciente());
        result.include("medico", medicoSessao.getMedico());
        result.include("tratamento", tratamentoSessao.getTratamento());
        result.include("medicamentoList", medicamentoDAO.listarTodos());
        result.include("posologiaList", posologiaDAO.listarTodos());
        result.include("estoque", estoqueDAO.listarTodos(medicoSessao.getMedico().getId()));
        result.include("qtd", qtd);
    }

    // Lista todos
    @Get("/tratamentoSessao/{id}")
    public List<Tratamento> tratamentoSessao(Long id) {
        tratamentoSessao.setTratamento(dao.obterPorId(id));
        result.include("paciente", tratamentoSessao.getTratamento().getPaciente());
        result.include("medico", tratamentoSessao.getTratamento().getMedico());
        result.include("tratamento", tratamentoSessao.getTratamento());
        return null;// itemTratamentoDAO.listarItemTratamentoPorTratamento(tratamentoSessao.getTratamento().getId());
    }

    // Adicionar
    @Post("/tratamento")
    public void adiciona(Pessoa pessoa, List<Tratamento> tratamentos) {

        List<Tratamento> listaTratamento = new ArrayList<Tratamento>();

        for (Tratamento tratamento : tratamentos) {
            if (tratamento != null && tratamento.getMedicamento() != null
                    && tratamento.getMedicamento().getId() != null) {

                tratamento.setMedicamento(medicamentoDAO.obterPorId(tratamento.getMedicamento().getId()));
                tratamento.setQuantidadeTotal(tratamento.getQuantidadeTotal());
                tratamento.setPosologia(tratamento.getPosologia());
                tratamento.setPaciente(pacienteSessao.getPaciente());
                tratamento.setMedico(medicoSessao.getMedico());
                tratamento.setTipoSituacaoTratamento(new TipoSituacaoTratamento(TipoSituacaoTratamento.EM_ANDAMENTO));
                tratamento.setDataInicio(new Date());
                tratamento.setValorTotal(tratamento.getMedicamento().getPrecoVenda()
                        .multiply(new BigDecimal(tratamento.getQuantidadeTotal())));
                tratamento.setValorTotalCusto(tratamento.getMedicamento().getPrecoCusto()
                        .multiply(new BigDecimal(tratamento.getQuantidadeTotal())));

                dao.novo(tratamento);
                listaTratamento.add(tratamento);
            }

        }

        enviarEmail(listaTratamento);

        result.include("mensagem", "Tratamento(s) adicionado(s) com sucesso.");
        result.redirectTo(IndexController.class).inicio(null);
    }

    private void enviarEmail(List<Tratamento> listaTratamento) {
        try {

            StringBuilder mensagem = new StringBuilder();
            String destinatario = null;
            for (Tratamento tratamento : listaTratamento) {

                destinatario = pessoaDAO.obterPorId(tratamento.getPaciente().getId()).getListaPessoaContatoEletronico()
                        .get(0).getEmail();

                mensagem.append("<h4> Tratamentos </h4>");
                mensagem.append("<p> Tratamento: " + tratamento.getNome() + "</p>");
                mensagem.append("<p> Posologia: " + tratamento.getPosologia().getNome() + "</p>");
                mensagem.append("<p> Total de Tratamentos: " + tratamento.getQuantidadeTotal() + "</p>");
                mensagem.append("<p> Médico: " + tratamento.getMedico().getNome() + "</p>");
                mensagem.append("<p> Médicamento: " + tratamento.getMedicamento().getNome() + "</p>");
                mensagem.append("<br />");
            }

            emailController.enviarEmail(destinatario, "Clínica Reviv - Tratamentos", mensagem.toString());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Altera��o - Gera a interface para alterar
    // Envia o ID para alterar
    @Get("/finalizarTratatamento/{tratamento.id}")
    public void finalizarTratamento(Tratamento tratamento) {
        tratamento = dao.obterPorId(tratamento.getId());

        BigDecimal valorTotalCusto = new BigDecimal("0.0");
        BigDecimal valorTotal = new BigDecimal("0.0");

        Pessoa pessoa = pacienteSessao.getPaciente();
        Boolean entrou = false;
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("<h4>Dados do Tratamento<h4>");

        // for (ItemTratamento itemTratamento :
        // itemTratamentoDAO.listarItemTratamentoPorTratamento(tratamento.getId()))
        // {
        // entrou = true;
        // valorTotal = valorTotal.add(itemTratamento.getValorVenda());
        // valorTotalCusto =
        // valorTotalCusto.add(itemTratamento.getValorCusto());
        //
        // mensagem.append("<p><b>Medicamento: </b>" +
        // itemTratamento.getMedicamento().getNome() + "</p>");
        // mensagem.append("<p><b>Quantidade: </b>" +
        // itemTratamento.getQuantidade() + "</p>");
        // mensagem.append("<p><b>Data Prevista: </b>" +
        // itemTratamento.getDataPrevista() + "</p>");
        // mensagem.append("<br />");
        //
        // }

        try {
            if (entrou) {
                emailController.enviarEmail(
                        pessoa.getListaPessoaContatoEletronico().toString().replace("[", "").replace("]", ""),
                        "Clinica Reviv - Dados Tratamento", mensagem.toString());
            } else {
                result.include("info", "Não foi selecionado nenhum item");
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        tratamento.setValorTotal(valorTotal);
        tratamento.setValorTotalCusto(valorTotalCusto);

        dao.atualizar(tratamento);
        result.include("mensagem", "Novo tratamento adicionado com sucesso.");
        result.redirectTo(IndexController.class).inicio(null);
    }

    // Remove
    // Envia o ID para Remo��o
    @Delete("/tratamento/{id}")
    public void remove(Long id) {
        Tratamento tratamento = dao.obterPorId(id);
        dao.excluir(tratamento);
        // Redireciona para a listagem, ap�s a inser��o.
        result.include("mensagem", "Novo tratamento iniciado com sucesso.");
        result.redirectTo(IndexController.class).comercial();
    }

    @Post("escolherMedico")
    public void escolherMedico(Pessoa pessoa) {
        if (pessoa != null && pessoa.getId() != null) {
            medicoSessao.setMedico(pessoaDAO.obterPorId(pessoa.getId()));
            result.redirectTo(PessoaController.class).paciente(2);
        } else {
            return;
        }
    }

    // Busca por nome
    public List<Estoque> busca(String nome) {
        result.include("tratamento", tratamentoSessao.getTratamento());
        result.include("nome", nome);
        result.include("medico", medicoSessao.getMedico());
        result.include("paciente", pacienteSessao.getPaciente());
        return estoqueDAO.busca(nome, medicoSessao.getMedico().getId());
    }

    // Busca medicamentos
    @Get("/listaItem")
    public List<Estoque> listaItem() {
        result.include("tratamento", tratamentoSessao.getTratamento());
        result.include("medico", medicoSessao.getMedico());
        result.include("paciente", pacienteSessao.getPaciente());
        return estoqueDAO.listarTodos(medicoSessao.getMedico().getId());
    }

    // Busca Json para auto completar
    @Get("/tratamento/busca.json")
    public void buscaJson(String q) {
        result.use(json()).withoutRoot().from(estoqueDAO.busca(q, medicoSessao.getMedico().getId())).exclude("id")
                .serialize();
    }

    // Busca Json para auto completar
    @Get("/buscaTotalEstoqueJson.json")
    public void buscaTotalEstoqueJson(String id) {
        result.use(json()).withoutRoot().from(dao.buscaEstoqueJson(id, medicoSessao.getMedico().getId())).serialize();
    }

}
