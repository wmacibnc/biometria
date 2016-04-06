<div class="container">
	<div class="row">
	
	<div class="panel panel-default">
			<div class="panel-heading">
			<span>Relatório Entradas no Estoque</span>
			<%@ include file="../headerUsuario.jsp"%>			
			</div>
		</div>
		
				<display:table class="table" id="operacaoEstoque" export="true" name="${operacaoEstoqueList}" requestURI="/entradaEstoque" pagesize="4" size="resultSize">
					<display:column property="estoque.pessoaMedicamento.pessoa.nome" title="Médico" sortable="true" />
					<display:column property="dataHoraLog" title="Data Entrada" sortable="true" format="{0,date,dd/MM/yyyy}"/>
					<display:column property="estoque.pessoaMedicamento.pessoa.nome" title="Farmacêutico" sortable="true" />
					<display:column property="estoque.pessoaMedicamento.medicamento.fabricante" title="Fabricante/Laboratório" sortable="true" />
					<display:column property="estoque.pessoaMedicamento.medicamento.nome" title="Medicamento" sortable="true" />
					<display:column property="numeroNotaFiscal" title="Número NF" sortable="true" />
					<display:column property="estoque.pessoaMedicamento.dataVencimento" title="Data. Validade" sortable="true" format="{0,date,dd/MM/yyyy}"/>
					<display:column property="quantidadeDisponivelAnterior" title="Qtd. Anterior" sortable="true" />
	
					<display:column title="Qtd." sortable="true">
						${operacaoEstoque.quantidadeDisponivelPosterior - operacaoEstoque.quantidadeDisponivelAnterior}
					</display:column>
					
					<display:column property="quantidadeDisponivelPosterior" title="Qtd. Posterior" sortable="true" />
					<display:column property="estoque.pessoaMedicamento.medicamento.precoCusto" title="Valor Custo" sortable="true" format="{0, number,R$ #,##0.00}"/>					
					<display:setProperty name="paging.banner.placement" value="bottom" />
				</display:table>
				</div>
			</div>
	<div id="footer">
		<a href="<c:url value="/telaInicial"/>">
			<button type="button" class="btn btn-danger btn-footer">Voltar</button>
		</a>
	</div>