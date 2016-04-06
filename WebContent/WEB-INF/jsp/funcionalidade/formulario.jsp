<!-- Modal -->
<div id="novo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-heading">Adicionar Funcionalidade</div>
					<div class="panel-body">
						<form id="funcionalidadeForm" action="<c:url value="/funcionalidade"/>" method="POST">

							<div class="input-group col-md-6">
								<span class="input-group">Nome do Funcionalidade</span> <br /> 
								<input id="funcionalidade.nome" name="funcionalidade.nome" class="form-control col-md-2" placeholder="Nome do Funcionalidade" aria-describedby="basic-addon1" /> 
								<span class="input-group-btn" required> </span>
							</div>
							<span class="help-block">Informe o nome do funcionalidade</span> 
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
	$('#funcionalidadeForm').validate({
		rules : {
			"funcionalidade.nome" : {
				required : true,
				minlength : 3
			}
		}
	});
</script>
