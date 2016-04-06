<!-- Modal -->
<div id="admEstoqueMedico" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-heading">Gerenciar Estoque</div>
					<div class="panel-body">
						<form id="escolherMedicoForm" action="<c:url value="/admEstoqueMedico"/>"
							method="POST">

							<!-- Select Basic -->
							<div class="input-group col-md-12">
								<label class="control-label" for="selectbasic">Médico</label>
								<div class="">
									<select name="pessoa.id" class="form-control"
										placeholder="Nome do Médico"
										aria-describedby="basic-addon1">
										<c:forEach items="${pessoTipoMedicamentoList}" var="pessoa">
											<option value="${pessoa.id}">${pessoa.nome}</option>
										</c:forEach>
									</select> 
								</div>
							</div>
							<table style="width:100%;text-align:center; margin-top: 17px;">
								<tr>
									<td>
									<span class="btn-espaco">
										<button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
									</span>
									
									<span class="btn-espaco">
										<button class="btn btn-success" type="submit">Selecionar</button>
									</span> 
									</td>
									
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- Modal -->