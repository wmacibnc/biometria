<!-- Modal -->
<div id="novo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-heading">Adicionar Tipo de Documento</div>
					<div class="panel-body">
						<form id="tipoDocumentoForm" action="<c:url value="/tipoDocumento"/>" method="POST">

							<div class="input-group col-md-6">
								<span class="input-group">Nome do Tipo de Documento</span> <br /> 
								<input id="tipoDocumento.nome" name="tipoDocumento.nome" class="form-control col-md-2" placeholder="Nome do TipoDocumento" aria-describedby="basic-addon1" /> 
								<span class="input-group-btn" required> </span>
							</div>
							<span class="help-block">Informe o nome do tipo de documento</span> 
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
</div><!-- Modal -->

<script type="text/javascript">
	$('#tipoDocumentoForm').validate({
		rules : {
			"tipoDocumento.nome" : {
				required : true,
				minlength : 3
			}
		}
	});
</script>
