
<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Tipo de Documentos</div>
			<div class="panel-body">
				<form action="<c:url value="/tipoDocumento/busca"/>">
					<div class="input-group">
						<input id="buscatipoDocumento" name="nome" class="form-control"
							placeholder="Nome do Tipo de Documento"
							aria-describedby="basic-addon1" /> <span class="input-group-btn">
							<button class="btn btn-default" type="submit"
								alt="Clique aqui para pesquisar">Filtrar</button>
						</span>
					</div>
				</form>
				<c:if test="${nome != null}">
					<h4>
						Busca pelo nome <span class="label label-default"><b>"${nome }"</b></span>
					</h4>
				</c:if>

				<div class="displayTableFrame">
					<display:table class="table" id="tipoDocumento" export="false"
						name="${tipoDocumentoList}"
						requestURI="/listaTipoDocumento">
						<display:column property="nome" title="Nome" sortable="true" />
						<display:column title="A��o">
							<ul class="btn">
								<a href="<c:url value="/tipoDocumento/${tipoDocumento.id}"/>">
									<span class="btn btn-default btn-acao">Alterar</span>
								</a>
							</ul>
							<div class="btn">
								<form
									action="<c:url value="/tipoDocumento/${tipoDocumento.id}"/>"
									method="POST">
									<button class="btn btn-danger" name="_method" value="DELETE"
										alt="Clique aqui para excluir">Excluir</button>
								</form>
							</div>
						</display:column>
						<display:setProperty name="paging.banner.placement" value="bottom" />
					</display:table>
				</div>
			</div>
			<div class="panel-footer"></div>
		</div>
		<div class="checkbox" align="center">
			<a href="<c:url value="/telaInicial"/>">
				<button type="button" class="btn btn-danger btn-footer">Voltar</button>
			</a>
			<button type="button" class="btn btn-success" data-toggle="modal"
				data-target="#novo">Inserir Novo</button>
		</div>
	</div>
</div>


<%@ include file="formulario.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buscatipoDocumento").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : '<c:url value="tipoDocumento/busca.json" />',
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
		$("#buscatipoDocumento").puts("Busca por nome");
	});
</script>