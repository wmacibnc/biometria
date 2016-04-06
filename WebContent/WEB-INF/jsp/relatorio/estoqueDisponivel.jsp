<div class="container">
	<div class="row">
	
	<div class="panel panel-default">
			<div class="panel-heading">
			<span>Relatório Estoque Disponível</span>
			<%@ include file="../headerUsuario.jsp"%>			
			</div>
		</div>
				<display:table class="table" id="estoque" export="true" name="${estoqueList}" requestURI="/estoqueDisponivel" pagesize="4" size="resultSize">
					<display:column property="pessoaMedicamento.pessoa.nome" title="Médico" sortable="true" />
					<display:column property="pessoaMedicamento.medicamento.nome" title="Medicamento" sortable="true" />
					
					<display:column title="Qtd Disp.">
					<c:if test="${estoque.quantidadeDisponivel < estoque.pessoaMedicamento.medicamento.quantidadeMinima }">
						<span style="color:red">
							${estoque.quantidadeDisponivel}
						</span>
					</c:if>
					<c:if test="${estoque.quantidadeDisponivel > estoque.pessoaMedicamento.medicamento.quantidadeMinima }">
						<span>
							${estoque.quantidadeDisponivel}
						</span>
					</c:if>										
					</display:column>
					
					<display:column property="pessoaMedicamento.medicamento.precoCusto" title="Valor Custo" sortable="true" format="{0, number,R$ #,##0.00}"/>
					<display:setProperty name="paging.banner.placement" value="bottom" />
				</display:table>
				</div>
			</div>
	<div id="footer">
		<a href="<c:url value="/telaInicial"/>">
			<button type="button" class="btn btn-danger btn-footer">Voltar</button>
		</a>
	</div>