<!-- Modal -->
<div id="novo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-heading">Novo Funcionário</div>
					<div class="panel-body">
					
						<form class="form-horizontal" id="pessoaForm" action="<c:url value="/funcionarios"/>" method="POST">

							<!-- Select Basic -->
							<div class="form-group">
								<label class="col-md-4 control-label" for="selectbasic">Tipo funcionário</label>
								
								<div class="col-md-4">
									<select name="pessoa.tipoPessoa.id" class="form-control"
										aria-describedby="basic-addon1">
										<c:forEach items="${tipoPessoaList}" var="tipoPessoa">
											<option value="${tipoPessoa.id}">${tipoPessoa.nome}</option>
										</c:forEach>
									</select> 
								</div>
							</div>

							<%@ include file="camposPessoa.jsp"%>

<table style="width:100%;text-align:center;">
	<tr>
		<td>
			<span class="btn-espaco">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
			</span>

			<span class="btn-espaco"> 
				<button class="btn btn-default" type="reset">Limpar</button>
			</span>

			<span class="btn-espaco"> 
				<button class="btn btn-success" type="submit">Salvar</button>
			</span>
		</td>
	</tr>
</table>
<div id="linha3"></div>

</form>
</div>
</div>
<!-- Modal -->