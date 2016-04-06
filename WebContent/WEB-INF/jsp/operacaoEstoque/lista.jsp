
<div class="container">
	<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="title-pager">Lista de Medicamentos - Médico: ${medico.nome} </span>
            	<%@ include file="../headerUsuario.jsp"%>
        </div>
	</div>
	<!-- 
	<form action="<c:url value="/pessoaMedicamento/busca"/>">
		<div class="input-group">
			<input id="buscapessoaMedicamento" name="nome" class="form-control" placeholder="Nome do PessoaMedicamento" aria-describedby="basic-addon1" />
				<button class="btn btn-default btn-filtrar" type="submit" alt="Clique aqui para pesquisar">Filtrar</button>
		</div>
				</form>
	 -->
				<c:if test="${nome != null}">
					<h4>
						Busca pelo nome <span class="label label-default"><b>"${nome }"</b></span>
					</h4>
				</c:if>
				
				<div class="displayTableFrame">
				<display:table class="table dataTable" id="estoque" export="false" name="${estoqueList}" requestURI="/listaPessoaMedicamento">
					<display:column property="pessoaMedicamento.medicamento.fabricante" title="Fabricante/Laboratório" sortable="true" />
					<display:column property="pessoaMedicamento.medicamento.nome" title="Nome" sortable="true" />
					<display:column property="pessoaMedicamento.medicamento.quantidadeMinima" title="Qtd Min." sortable="true" />    
					<display:column property="quantidadeDisponivel" title="Qtd Disp." sortable="true" />
					<display:setProperty name="paging.banner.placement" value="bottom" />
				</display:table>
                </div>
            </div>
</div>
<div id="footer">
	<a href="<c:url value="/escolheTipoOperacaoEstoque"/>">				
		<button type="button" class="btn btn-danger btn-footer">Voltar</button>
	</a>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buscapessoaMedicamento").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : '<c:url value="pessoaMedicamento/busca.json" />',
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
		$("#buscapessoaMedicamento").puts("Busca por nome");
	});
</script>