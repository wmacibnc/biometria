<!-- Modal -->
<div id="novo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-heading">Adicionar Pessoa</div>
					<div class="panel-body">
						<form class="form-horizontal" id="pessoaForm"
							action="<c:url value="/pessoa"/>" method="POST">

							<!-- Select Basic -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="selectbasic">Tipo pessoa</label>
								<div class="col-md-4">
									<select name="pessoa.tipoPessoa.id" class="form-control" aria-describedby="basic-addon1">
										<c:forEach items="${tipoPessoaList}" var="tipoPessoa">
											<option value="${tipoPessoa.id}">${tipoPessoa.nome}</option>
										</c:forEach>
									</select> <span class="help-block">Selecione o tipo de pessoa</span>
								</div>
							</div>

							<%@ include file="camposPessoa.jsp"%>


							<button class="btn btn-primary" type="submit">Salvar</button>
							<button class="btn btn-danger" type="reset">Limpar</button>
							<div id="linha3"></div>
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
