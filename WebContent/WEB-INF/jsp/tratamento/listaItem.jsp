
<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Medicamentos - Médico:
				${medico.nome}</div>
			<div class="panel-body">


				<p>Médico: ${medico.nome}</p>
				<p>Paciente: ${paciente.nome}</p>

				<form action="<c:url value="/tratamento/busca"/>">
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

				<display:table class="table" id="estoque" export="false" name="${estoqueList}" requestURI="/listaMedicamento">
					<c:set var="cont" value="0" />
					<display:column property="pessoaMedicamento.medicamento.fabricante" title="Fabricante/Laboratório" sortable="true" />
					<display:column property="pessoaMedicamento.medicamento.nome" title="Nome" sortable="true" />
					
					<display:column>
						<div class="btn">
							<form action="<c:url value="/itemTratamento"/>" method="POST">
								<div class="form-group col-md-12">
									
									
								<table>
								
								<tr>
								<td width="15%"><label>Qtd</label></td>
								<td width="30%"><label>Data Aplicação</label></td>
								<td width="50%"><label>Selecionar</label></td>
								</tr>
								
								<tr>
								<td width="15%">
								</td>
								<td width="30%">
									
											
								</td>
								
								<td width="45%"><button class="btn btn-warning"  alt="Clique aqui para selecionar o medicamento"> Selecionar</button></td>
								</tr>
								</table>
								</div>

</form>
						</div>

					</display:column>
					<display:setProperty name="paging.banner.placement" value="bottom" />
				</display:table>
				
				<div class="checkbox" align="center">
				<a href="<c:url value="/listaItem"/>">
					<button type="button" class="btn btn-primary">Listar todos os medicamentos</button>
				</a>
				</div>

			</div>
			<div class="panel-footer"></div>

		</div>
	</div>
</div>

</div>
<div id="footer">
<a href="<c:url value="/listaTratamento"/>">
					<button type="button" class="btn btn-danger btn-footer">Voltar</button>
				</a>
</div>
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