package br.com.otimo.reviv.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.NonUniqueResultException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.blank.IndexController;
import br.com.otimo.reviv.dao.ItemEntregaDAO;
import br.com.otimo.reviv.dao.TratamentoDAO;
import br.com.otimo.reviv.dao.UsuarioDAO;
import br.com.otimo.reviv.interceptor.UsuarioLogado;
import br.com.otimo.reviv.modelo.ItemEntrega;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.TipoPessoa;
import br.com.otimo.reviv.modelo.TipoSituacaoTratamento;
import br.com.otimo.reviv.modelo.Tratamento;
import br.com.otimo.reviv.modelo.Usuario;
import br.com.otimo.reviv.sessao.PacienteSessao;
import br.com.otimo.reviv.sessao.PessoaAplicacaoSessao;
import br.com.otimo.reviv.sessao.TratamentoSessao;

@Resource
public class ItemAplicacaoController {

    private final ItemEntregaDAO itemEntregaDAO;
    private final TratamentoDAO tratamentoDAO;
    private final UsuarioDAO usuarioDAO;
    private final TratamentoSessao tratamentoSessao;
    private final PacienteSessao pacienteSessao;
    private final PessoaAplicacaoSessao pessoaAplicacaoSessao;
    private final EmailController emailController;
    private final UsuarioLogado usuarioLogado;

    private final Result result;

    public ItemAplicacaoController(ItemEntregaDAO itemEntregaDAO, TratamentoDAO tratamentoDAO, UsuarioDAO usuarioDAO,
            TratamentoSessao tratamentoSessao, PacienteSessao pacienteSessao,
            PessoaAplicacaoSessao pessoaAplicacaoSessao, EmailController emailController, UsuarioLogado usuarioLogado,
            Result result) {
        super();
        this.itemEntregaDAO = itemEntregaDAO;
        this.tratamentoDAO = tratamentoDAO;
        this.usuarioDAO = usuarioDAO;
        this.tratamentoSessao = tratamentoSessao;
        this.pacienteSessao = pacienteSessao;
        this.emailController = emailController;
        this.pessoaAplicacaoSessao = pessoaAplicacaoSessao;
        this.usuarioLogado = usuarioLogado;
        this.result = result;
    }

    // Lista todos
    @Post("/listaAplicarMedicamento/{situacao}")
    public List<Tratamento> lista(final Pessoa pessoa, Long situacao) {
        pacienteSessao.setPaciente(pessoa);
        result.include("paciente", pacienteSessao.getPaciente());
        result.include("situacao", situacao);
        return tratamentoDAO.listarTodosTratamentos(pessoa, situacao);
    }

    // Lista todos
    @Get("/listaItemTratamentoA/{id}")
    public List<Tratamento> listaItem(final Long id) {

        tratamentoSessao.setTratamento(tratamentoDAO.obterPorId(id));
        Tratamento tratamento = tratamentoSessao.getTratamento();
        Pessoa paciente = pacienteSessao.getPaciente();

        result.include("Tratamento", tratamento);
        result.include("Paciente", paciente);

        return null;// itemTratamentoDAO.listarItemTratamentoPorTratamento(tratamento.getId());
    }

    @Post("/identificaAplicacao")
    public void identificaUsuarioAplicacao(Usuario usuario, Long idTratamento) {
        Usuario autenticado = null;

        try {
            autenticado = usuarioDAO.obterUsuarioPorSenha(usuario);
        } catch (NonUniqueResultException e) {
        }

        if (autenticado != null) {
            if (autenticado.getPessoa() != null && autenticado.getPessoa().getId() != null) {
                if (autenticado.getPessoa().getTipoPessoa().getId() == TipoPessoa.PACIENTE) {
                    // Redireciona para a aplicação de paciente
                    result.redirectTo(this).listaAplicacaoPaciente();
                    pessoaAplicacaoSessao.setPessoaAplicacao(autenticado.getPessoa());
                    pacienteSessao.setPaciente(autenticado.getPessoa());
                } else {
                    // Redireciona para a tela inicial - informando que somente
                    // paciente pode retirar o medicamento
                    result.include("error", "Somente o paciente pode efetuar essa operação.");
                    result.redirectTo(IndexController.class).inicio(TipoPessoa.ENFERMEIRA);
                }
            } else {
                result.include("error", "Não foi possivel validar as informações");
                result.redirectTo(IndexController.class).inicio(TipoPessoa.ENFERMEIRA);
            }
        } else {
            result.include("error", "Login e/ou senha invalidos.");
            result.redirectTo(IndexController.class).inicio(TipoPessoa.ENFERMEIRA);
        }

    }

    @Get("/listaAplicacaoPaciente")
    public void listaAplicacaoPaciente() {
        result.include("paciente", pacienteSessao.getPaciente());
        result.include("tratamentoList", tratamentoDAO.listarTodosTratamentosParaAplicacao(pacienteSessao.getPaciente(),
                TipoSituacaoTratamento.EM_ANDAMENTO));
    }

    @Post("/finalizarAplicacaoMedicamento")
    public List<Tratamento> finalizarAplicacaoMedicamento(List<ItemEntrega> itemAplicacaos) {
        List<Tratamento> listaTratamento = new ArrayList<Tratamento>();

        for (ItemEntrega itemAplicacao : itemAplicacaos) {

            if (itemAplicacao.getSim() != null && itemAplicacao.getSim()) {

                ItemEntrega itemEntrega = itemEntregaDAO.obterPorId(itemAplicacao.getId());
                itemEntrega.setBolAplicacao(Boolean.TRUE);
                itemEntrega.setEnfermeira(usuarioLogado.getUsuario().getPessoa());
                itemEntrega.setQuantidadeAplicada((short) 1);
                itemEntrega.setDataAplicacao(new Date());
                itemEntregaDAO.atualizar(itemEntrega);
                listaTratamento.add(tratamentoDAO.obterPorId(itemAplicacao.getTratamento().getId()));
            }

            enviarEmail(listaTratamento);

        }
        result.include("paciente", pacienteSessao.getPaciente());
        result.include("mensagem", "Medicamentos aplicados com sucesso!");
        return listaTratamento;
    }

    private void enviarEmail(List<Tratamento> listaTratamento) {
        try {

            StringBuilder mensagem = new StringBuilder();
            String destinatario = null;
            for (Tratamento tratamento : listaTratamento) {

                destinatario = tratamento.getPaciente().getListaPessoaContatoEletronico().get(0).getEmail();

                mensagem.append("<h4> Aplicação de Medicamento </h4>");
                mensagem.append("<p> Tratamento: " + tratamento.getNome() + "</p>");
                mensagem.append("<p> Posologia: " + tratamento.getPosologia().getNome() + "</p>");
                mensagem.append("<p> Total de Tratamentos: " + tratamento.getQuantidadeTotal() + "</p>");
                mensagem.append("<p> Médico: " + tratamento.getMedico().getNome() + "</p>");
                mensagem.append("<p> Médicamento: " + tratamento.getMedicamento().getNome() + "</p>");
                mensagem.append("<br />");
                for (ItemEntrega item : tratamento.getListaItemEntrega()) {

                    mensagem.append("<table>");
                    mensagem.append("<tr>");
                    mensagem.append("<td>Sessão</td>");
                    mensagem.append("<td>Data/Hora</td>");
                    mensagem.append("<td>Enfermeira</td>");
                    mensagem.append("</tr>");

                    mensagem.append("<tr>");
                    mensagem.append("<td>" + item.getSessao() + "</td>");

                    if (item.getDataAplicacao() != null && item.getEnfermeira() != null) {
                        mensagem.append("<td>" + item.getDataAplicacao() + "</td>");
                        mensagem.append("<td>" + item.getEnfermeira().getNome() + "</td>");
                    }
                    mensagem.append("</tr>");

                    mensagem.append("</table>");
                    mensagem.append("<br />");
                }
            }

            emailController.enviarEmail(destinatario, "Clínica Reviv - Aplicação de Medicamento", mensagem.toString());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
