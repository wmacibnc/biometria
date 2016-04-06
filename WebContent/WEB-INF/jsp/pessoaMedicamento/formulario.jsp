<!-- Modal -->
<div id="novo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-heading">Adicionar Dados Medicamento -
						Médico: ${medico.nome}</div>
					<div class="panel-body">
						<form id="pessoaMedicamentoForm"
							action="<c:url value="/pessoaMedicamento"/>" method="POST">

							<input type="hidden" name="pessoaMedicamento.pessoa.id" value="${medico.id}" />
							<!-- Select Basic -->
							<div class="form-group">
								<label class="col-md-6 control-label" for="selectbasic">Medicamento</label>
									<select name="pessoaMedicamento.medicamento.id" class="form-control"
										placeholder="Nome do Medicamento"
										aria-describedby="basic-addon1">
										<c:forEach items="${medicamentoList}" var="medicamento">
											<option value="${medicamento.id}">
											${medicamento.nome} - 
											${medicamento.fabricante}
											</option>
										</c:forEach>
									</select> <span class="help-block">Selecione o medicamento</span>
							</div>
                            
                            <!-- input -->
                            <div class="input-group col-md-6">
                                <span class="input-group">Quantidade Disponível</span> <br />
                                <input id="number" type="number"
                                    name="quantidadeDisponivel"
                                    class="form-control col-md-2"
                                    placeholder="Quantidade Disponível"
                                    aria-describedby="basic-addon1" /> <span
                                    class="input-group-btn" required> </span>
                            </div>
                            <span class="help-block">Informe a quantidade disponível do medicamento</span>
                            <!-- input -->
                                                        
                            <!-- input -->
                            <div class="input-group col-md-6">
                                <span class="input-group">Data de Vencimento</span> <br />
                                <input id="date" 
                                    name="pessoaMedicamento.dataVencimento"
                                    class="form-control col-md-2"
                                    placeholder="Data de vencimento"
                                    aria-describedby="basic-addon1" /> <span
                                    class="input-group-btn" required> </span>
                            </div>
                            <span class="help-block">Informe a data de vencimento do medicamento</span>
                            <!-- input -->
							
							<div class="checkbox">
								<button class="btn btn-primary" type="submit">Salvar</button>
								<button class="btn btn-danger" type="reset">Limpar</button>
							</div>
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

<script type="text/javascript">
	$("#date").mask("99/99/9999");
</script>
