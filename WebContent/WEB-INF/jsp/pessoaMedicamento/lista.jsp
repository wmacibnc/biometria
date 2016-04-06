
<div class="container">
	<div class="row">
	
	<div class="panel panel-default">
            <div class="panel-heading">
            <span class="title-pager">Lista de Medicamentos - Médico: ${medico.nome}</span>
            	<%@ include file="../headerUsuario.jsp"%>
            </div>
    </div>
        
		
				<form action="<c:url value="/pessoaMedicamento/busca"/>">
					<div class="input-group">
						<input id="buscapessoaMedicamento" name="nome" class="form-control" placeholder="Nome do PessoaMedicamento" aria-describedby="basic-addon1" /> <span class="input-group-btn">
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
				<display:table class="table dataTable" id="estoque" export="false" name="${estoqueList}" requestURI="/listaPessoaMedicamento">
					<display:column property="pessoaMedicamento.medicamento.fabricante" title="Fabricante/Laboratório" sortable="true" />
					<display:column property="pessoaMedicamento.medicamento.nome" title="Nome" sortable="true" />    
					<display:column property="quantidadeDisponivel" title="Qtd Disp." sortable="true" />
					<display:column property="pessoaMedicamento.dataVencimento" title="Data Vencimento" sortable="true" format="{0,date,dd/MM/yyyy}" />
					<!-- 
					<display:column title="Ação">
						<div class="btn">
							<form action="<c:url value="/pessoaMedicamento/${estoque.pessoaMedicamento.id}"/>" method="POST">
								<button class="btn btn-danger" name="_method" value="DELETE" alt="Clique aqui para excluir">Excluir</button>
							</form>
						</div>
					</display:column>
					 -->
					<display:setProperty name="paging.banner.placement" value="bottom" />
				</display:table>
				</div>
			</div>
	</div>
<div id="footer">
<a href="<c:url value="/telaInicial"/>">
					<button type="button" class="btn btn-danger btn-footer">Voltar</button>
				</a>
				<!--
				<button type="button" class="btn btn-success" data-toggle="modal" data-target="#novo">Inserir Novo</button> 
				 -->
</div>

<%@ include file="formulario.jsp"%>
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