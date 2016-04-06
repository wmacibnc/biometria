<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">
			<span>Medicamentos</span>
			<%@ include file="../headerUsuario.jsp"%>			
			</div>
		</div>

			<form action="<c:url value="/medicamento/busca"/>">
				<div class="input-group">
					<input id="buscamedicamento" name="nome" class="form-control" placeholder="Nome do Medicamento" aria-describedby="basic-addon1" /> 
					<span class="input-group-btn">
						<button class="btn btn-default btn-filtrar" type="submit" alt="Clique aqui para pesquisar">Filtrar</button>
					</span>
				</div>
			</form>
			
			<c:if test="${nome != null}">
				<h4>
					Busca pelo nome <span class="label label-default"><b>"${nome }"</b></span>
				</h4>
			</c:if>
			
				<div class="displayTableFrame">
				<display:table class="table dataTable" id="medicamento" export="false" name="${medicamentoList}" requestURI="/listaMedicamento">
					<display:column property="fabricante" title="Fabricante/Laboratório" sortable="true" />
					<display:column property="nome" title="Nome" sortable="true" />
					<display:column property="precoCusto" title="Pr. de Custo" sortable="true"  format="{0, number,R$ #,##0.00}" />
					<display:column property="precoVenda" title="Pr. Venda" sortable="true" format="{0, number,R$ #,##0.00}" />
					<display:column property="quantidadeMinima" title="Qtd Min." sortable="true" />
					<display:column title="Ação">
						<ul class="btn">
							<a href="<c:url value="/medicamento/${medicamento.id}"/>">
							<span class="btn btn-default btn-acao">Alterar</span></a>
						</ul>
						<div class="btn">
							<form action="<c:url value="/medicamento/${medicamento.id}"/>" method="POST">
								<button class="btn btn-default btn-acao" onclick="return confirm('Deseja realmente excluir esse item?');" name="_method" value="DELETE" alt="Clique aqui para excluir">Excluir</button>
							</form>
						</div>
					</display:column>
					<display:setProperty name="paging.banner.placement" value="bottom" />
				</display:table>
				</div>
			</div>

		</div>




</div>
<div id="footer">
<a href="<c:url value="/telaInicial"/>">
					<button type="button" class="btn btn-danger btn-footer">Voltar</button>
				</a>
				<button type="button" class="btn btn-success btn-footer" data-toggle="modal" data-target="#novo">Inserir Novo</button>
</div>

<%@ include file="formulario.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buscamedicamento").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : '<c:url value="medicamento/busca.json" />',
					data : {
						q : request.term
					},
					success : function(data) {

						response($.map(data, function(item) {
							return {
								label : item.nome,
								value : item.nome
							};
						}));
					},
				});
			},
			minLength : 1,
		});
		$("#buscamedicamento").puts("Busca por nome");
	});
</script>