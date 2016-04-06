package br.com.otimo.reviv.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.Iterator;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.otimo.reviv.dao.EstoqueDAO;
import br.com.otimo.reviv.dao.MedicamentoDAO;
import br.com.otimo.reviv.dao.OperacaoEstoqueDAO;
import br.com.otimo.reviv.dao.PessoaDAO;
import br.com.otimo.reviv.interceptor.UsuarioLogado;
import br.com.otimo.reviv.modelo.Estoque;
import br.com.otimo.reviv.modelo.OperacaoEstoque;
import br.com.otimo.reviv.modelo.Pessoa;
import br.com.otimo.reviv.modelo.PessoaMedicamento;
import br.com.otimo.reviv.modelo.TipoOperacao;
import br.com.otimo.reviv.sessao.MedicoSessao;

@Resource
public class OperacaoEstoqueController {
    private final OperacaoEstoqueDAO dao;
    private final PessoaDAO pessoaDAO;
    private final MedicoSessao medicoSessao;
    private final EstoqueDAO estoqueDAO;
    private final UsuarioLogado usuarioLogado;
    private final MedicamentoDAO medicamentoDAO;
    private final Result result;

    public OperacaoEstoqueController(OperacaoEstoqueDAO dao, PessoaDAO pessoaDAO, MedicoSessao medicoSessao,
            EstoqueDAO estoqueDAO, UsuarioLogado usuarioLogado, MedicamentoDAO medicamentoDAO, Result result) {
        this.dao = dao;
        this.pessoaDAO = pessoaDAO;
        this.medicoSessao = medicoSessao;
        this.estoqueDAO = estoqueDAO;
        this.usuarioLogado = usuarioLogado;
        this.medicamentoDAO = medicamentoDAO;
        this.result = result;
    }

    // Lista todos
    @Get("/listaOperacaoEstoque")
    public List<Estoque> lista() {
        result.include("medico", medicoSessao.getMedico());
        return estoqueDAO.listarTodos(medicoSessao.getMedico().getId());
    }

    // Entrada
    @Get("/operacaoEstoqueE")
    public void entrada() {
        result.include("medico", medicoSessao.getMedico());
        result.include("medicamentoList", medicamentoDAO.listarTodos());
    }

    // Saida
    @Get("/operacaoEstoqueS")
    public void saida() {
        result.include("medico", medicoSessao.getMedico());
        result.include("medicamentoList", medicamentoDAO.listarTodos());
    }

    // Altera��o - Gera a interface para alterar
    // Envia o ID para alterar
    @Put("/operacaoEstoqueSaida")
    public void alteraSaida(final List<PessoaMedicamento> pessoaMedicamento, Boolean bolAplicacao) {

        Iterator<PessoaMedicamento> it = pessoaMedicamento.iterator();
        while (it.hasNext()) {
            PessoaMedicamento pessoaMedicamento2 = it.next();

            if (pessoaMedicamento2 != null && pessoaMedicamento2.getMedicamento() != null
                    && pessoaMedicamento2.getMedicamento().getId() != null && pessoaMedicamento2.getPessoa() != null
                    && pessoaMedicamento2.getPessoa().getId() != null) {

                Estoque estoque = estoqueDAO.obterEstoquePorMedicoMedicamento(pessoaMedicamento2.getPessoa().getId(),
                        pessoaMedicamento2.getMedicamento().getId());

                estoque = atualizarEstoque(estoque, pessoaMedicamento2.getQuantidade());
                salvarOperacaoEstoqueSaida(estoque, pessoaMedicamento2.getQuantidade(),
                        pessoaMedicamento2.getJustificativa());

            }
        }

        if (!bolAplicacao)
            // Redireciona para a listagem, ap�s a inser��o.
            result.redirectTo(this).lista();
    }

    @Put("/operacaoEstoqueEntrada")
    public void alteraEntrada(final List<PessoaMedicamento> pessoaMedicamento) {
        Iterator<PessoaMedicamento> it = pessoaMedicamento.iterator();
        while (it.hasNext()) {
            PessoaMedicamento pessoaMedicamento2 = it.next();

            if (pessoaMedicamento2 != null && pessoaMedicamento2.getMedicamento() != null
                    && pessoaMedicamento2.getMedicamento().getId() != null && pessoaMedicamento2.getPessoa() != null
                    && pessoaMedicamento2.getPessoa().getId() != null) {

                Estoque estoque = estoqueDAO.obterEstoquePorMedicoMedicamento(pessoaMedicamento2.getPessoa().getId(),
                        pessoaMedicamento2.getMedicamento().getId());

                estoque = atualizarEstoqueE(estoque, pessoaMedicamento2.getQuantidade());
                salvarOperacaoEstoqueEntrada(estoque, pessoaMedicamento2.getQuantidade(),
                        pessoaMedicamento2.getNotaFiscal());

            }
        }

        // Redireciona para a listagem, ap�s a inser��o.
        result.redirectTo(this).lista();
    }

    public void salvarOperacaoEstoqueSaida(Estoque estoque, final Short quantidadeSaida, String justificativa) {
        OperacaoEstoque operacaoEstoque = new OperacaoEstoque();
        operacaoEstoque.setEstoque(estoque);
        operacaoEstoque.setQuantidadeDisponivelAnterior(estoque.getQuantidadeDisponivel());
        operacaoEstoque.setQuantidadeDisponivelPosterior((short) (estoque.getQuantidadeDisponivel() - quantidadeSaida));
        operacaoEstoque.setJustificativa(justificativa);
        operacaoEstoque.setTipoOperacao(new TipoOperacao(TipoOperacao.SAIDA));
        if (usuarioLogado != null && usuarioLogado.getUsuario() != null
                && usuarioLogado.getUsuario().getPessoa() != null
                && usuarioLogado.getUsuario().getPessoa().getId() != null) {
            operacaoEstoque.setPessoa(usuarioLogado.getUsuario().getPessoa());
        }
        dao.novo(operacaoEstoque);
    }

    private void salvarOperacaoEstoqueEntrada(Estoque estoque, final Short quantidadeEntrada, String notaFiscal) {
        OperacaoEstoque operacaoEstoque = new OperacaoEstoque();
        operacaoEstoque.setEstoque(estoque);
        operacaoEstoque.setQuantidadeDisponivelAnterior(estoque.getQuantidadeDisponivel());
        operacaoEstoque
                .setQuantidadeDisponivelPosterior((short) (estoque.getQuantidadeDisponivel() + quantidadeEntrada));
        operacaoEstoque.setTipoOperacao(new TipoOperacao(TipoOperacao.ENTRADA));
        operacaoEstoque.setJustificativa("Entrada de Médicamento - NF: " + notaFiscal);

        if (usuarioLogado != null && usuarioLogado.getUsuario() != null
                && usuarioLogado.getUsuario().getPessoa() != null
                && usuarioLogado.getUsuario().getPessoa().getId() != null) {
            operacaoEstoque.setPessoa(usuarioLogado.getUsuario().getPessoa());
        }

        dao.novo(operacaoEstoque);
    }

    public Estoque atualizarEstoque(Estoque estoque, final Short quantidadeSaida) {
        estoque = estoqueDAO.obterPorId(estoque.getId());
        estoque.setQuantidadeDisponivel((short) (estoque.getQuantidadeDisponivel() - quantidadeSaida));
        estoqueDAO.atualizar(estoque);
        return estoque;
    }

    private Estoque atualizarEstoqueE(Estoque estoque, final Short quantidadeSaida) {
        if (estoque.getQuantidadeDisponivel() == null) {
            estoque.setQuantidadeDisponivel((short) 0);
        }
        estoque.setQuantidadeDisponivel((short) (estoque.getQuantidadeDisponivel() + quantidadeSaida));
        estoqueDAO.atualizar(estoque);
        return estoque;
    }

    // Remove
    // Envia o ID para Remo��o
    @Delete("/operacaoEstoque/{id}")
    public void remove(Long id) {
        OperacaoEstoque operacaoEstoques = dao.obterPorId(id);
        dao.excluir(operacaoEstoques);
        // Redireciona para a listagem, ap�s a inser��o.
        result.redirectTo(this).lista();
    }

    // Busca por nome
    public List<OperacaoEstoque> busca(String nome) {
        result.include("nome", nome);
        return dao.busca(nome);
    }

    // Busca por nome vendas
    public List<OperacaoEstoque> busca2(String nome) {
        result.include("nome", nome);
        return dao.busca(nome);
    }

    // Busca Json para auto completar
    @Get("/operacaoEstoque/busca.json")
    public void buscaJson(String q) {
        result.use(json()).withoutRoot().from(dao.busca(q)).exclude("id").serialize();
    }

    @Post("/admEstoqueMedico")
    public void escolherMedico(Pessoa pessoa) {
        if (pessoa != null && pessoa.getId() != null) {
            medicoSessao.setMedico(pessoaDAO.obterPorId(pessoa.getId()));
            result.redirectTo(this).estoque();
        } else {
            return;
        }
    }

    // Entrada
    @Get("/escolheTipoOperacaoEstoque")
    public void estoque() {
    }

}
