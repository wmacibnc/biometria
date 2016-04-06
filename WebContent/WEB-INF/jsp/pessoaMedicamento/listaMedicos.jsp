<!-- Modal -->
<div id="admMedico" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-heading">Escolha o médico que deseja administrar</div>
					<div class="panel-body">
						<form id="escolherMedicoForm" action="<c:url value="/definirMedico"/>"
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
									<span class="help-block">Selecione o Médico que deseja administrar</span>
								</div>
							</div>
							<!-- Select Basic -->
							<button class="btn btn-primary" type="submit">Selecionar</button>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal">Fechar</button>
			</div>

		</div>
	</div>
	</div>
	<!-- Modal -->