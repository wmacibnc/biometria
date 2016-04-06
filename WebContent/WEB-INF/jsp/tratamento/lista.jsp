	<div class="container">
		<div class="row">

			<div class="panel panel-default">
				<div class="panel-heading">
					<span class="title-pager">Novo tratamento</span>
					<%@ include file="../headerUsuario.jsp"%>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<p style="margin: 12px 0 4px !important;">
				<b>Médico:</b> ${medico.nome}
			</p>
			<p style="margin: 12px 0 4px !important;">
				<b>Paciente:</b> ${paciente.nome}
			</p>
			
				<form action="<c:url value="/listaTratamento"/>" method="POST">
				<div class="input-group col-md-12">
					<table>
						<tr>
							<td>
								<b class="control-label" aria-describedby="basic-addon1" for="selectbasic">Quantidade de tratamentos:&nbsp; &nbsp;</b>
							</td>
							
							<td>
								<select style="width: 6em; text-align: center !important;  align: center !important;"
									name="qtd" onchange="this.form.submit()"
									class="form-control"
									aria-describedby="basic-addon1">
									
									<c:forEach begin="1" end="99" varStatus="qtd2">
										<option value="${qtd2.index}" ${qtd2.index eq qtd ? 'selected' : ''}>${qtd2.index}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
				</div>
				</form>

		<form action="<c:url value="/tratamento"/>" method="POST">
			<table class="table">
				<thead>
					<tr>
					<th class="sortable">Nº</th>
					<th class="sortable">Tratamento</th>
						<th class="sortable">Medicamento</th>
						<th class="sortable">Qtd. Estoque</th>
						<th class="sortable">Posologia</th>
						<th class="sortable">Qtd. Total</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach begin="1" end="${qtd}" varStatus="cont">
						<tr class="odd">
						
						      <td> ${cont.index} </td>
						      
						      <td>
						          <input type="text"
                                    name="tratamentos[${cont.index}].nome"
                                    value="${tratamento.nome}" class="form-control"
                                    aria-describedby="basic-addon1" />
						      </td>
						      
							<td>
							     <select
									name="tratamentos[${cont.index}].medicamento.id"
									class="form-control" aria-describedby="basic-addon1"
									id="medicamentoSelect${cont.index}"
									style="width: 100%;">
										<option value="">Selecione o medicamento</option>
										<c:forEach items="${medicamentoList}" var="medicamento">
											<option value="${medicamento.id}">${medicamento.nome}</option>
										</c:forEach>
							     </select>
							<script type="text/javascript">
							$(document).ready(function(){
								   $('#medicamentoSelect${cont.index}').on('change', function(){

								      var self = $(this);
								      var selecionado = self.val(); // pega o ID do estado
								      $.ajax({
								    	  
								         url:'<c:url value="buscaTotalEstoqueJson.json" />',
								         data:{id:selecionado}, // Passa a variável ao server. O nome do parâmetro tem que ser estado, pois foi o nome colocado aqui.
								         dataType:'json', // O Server vai retornar um JSON
								         success:function(data){
								            document.getElementById('qtdEstoque${cont.index}').value = data[0];
								         },
								         error:function(){
								            alert('Erro ao obter dados do estoque');
								         }
								      });
								   });
								});
							</script>
							</td>
							

							<td>
							 	<input type="text" id="qtdEstoque${cont.index}" class="form-control" aria-describedby="basic-addon1" size="3" readonly="readonly">
							</td>
							<td>

                                  <select
	                                    name="tratamentos[${cont.index}].posologia.id"
	                                    class="form-control" aria-describedby="basic-addon1"
	                                    style="width: 100%;">
	                                        <option value="">Selecione a posologia</option>
	                                        <c:forEach items="${posologiaList}" var="posologia">
	                                            <option value="${posologia.id}">${posologia.nome}</option>
	                                        </c:forEach>
                                 </select>
                            </td>
							<td>
								<input type="number"
									name="tratamentos[${cont.index}].quantidadeTotal"
									value="${tratamento.quantidadeTotal}" class="form-control"
									style="width: 70%;" aria-describedby="basic-addon1" />
							</td>
						</tr>
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
	
		<button type="submit" class="btn btn-success btn-footer" name="_method" value="POST" onclick="return confirm('Confirmar salvar o tratamento?');">Salvar</button>
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