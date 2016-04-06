	<div class="container">
		<div class="row">

			<div class="panel panel-default">
				<div class="panel-heading">
					<span class="title-pager">Tratamento</span>
					<%@ include file="../headerUsuario.jsp"%>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<p>
				<b>Médico:</b> ${medico.nome}
			</p>
			<p>
				<b>Paciente:</b> ${paciente.nome}
			</p>
			
		<form action="<c:url value="/finalizarEntregaMedicamentoPaciente"/>" method="POST">
		
			<table class="table">
				<thead>
					<tr>
					<th class="sortable">Sessão</th>
					<th class="sortable">Tratamento</th>
						<th class="sortable">Medicamento</th>
						<th class="sortable">Quantidade de Entrega</th>
						<th class="sortable">Quantidade Total</th>
					</tr>
				</thead>

				<tbody>
					  <c:forEach var="tratamento" items="${tratamentoList}" varStatus="cont">
					  
					  <input type="hidden" name="itemEntregas[${tratamento.id}].tratamento.id" value="${tratamento.id}" />
					  
					  <c:if test="${tratamento.totalEntregue < tratamento.quantidadeTotal}">
						<tr class="odd">
						      <td>
						      	<fmt:formatNumber value="${tratamento.totalEntregue + 1}"/>
						      	<input type="hidden" name="itemEntregas[${tratamento.id}].sessao" value="${tratamento.totalEntregue + 1}" />
						      </td>
						      
								<td>
						     		${tratamento.nome}
						      	</td>
						      
							<td>
								${tratamento.medicamento.nome}
							</td>
							
							<td>
								<input type="number" class="form-control input-md" name="itemEntregas[${tratamento.id}].quantidadeEntrege" max="${tratamento.quantidadeTotal - tratamento.totalEntregue}" style="width: 50%" />
							</td>
							
							<td>
								${tratamento.quantidadeTotal}
							</td>
						
						</tr>
						</c:if>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	<br /><br /><br /><br /><br /><br /><br />
	
	
<div id="footer">
	<a href="<c:url value="/telaInicial"/>">
		<button type="button" class="btn btn-danger btn-footer">Voltar</button>
	</a>
	
		<button type="submit" class="btn btn-success btn-footer" name="_method" value="POST" onclick="return confirm('Confirma a entrega dos medicamentos?');">Entregar</button>
</div>

</form>

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