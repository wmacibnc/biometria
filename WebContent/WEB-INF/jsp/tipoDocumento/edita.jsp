
<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Editar Tipo de Documento</div>
			<div class="panel-body">
				<form name="tipoDocumentoForm" id="tipoDocumentoForm" action="<c:url value="/tipoDocumento/${tipoDocumento.id}"/>" method="POST">
					<div class="input-group col-md-2">
						<span class="input-group">Nome do Tipo de Documento</span> <br /> <input id="tipoDocumento.nome" value="${tipoDocumento.nome}" name="tipoDocumento.nome" class="form-control col-md-2"
							placeholder="Nome do TipoDocumento" aria-describedby="basic-addon1" /> <span class="input-group-btn" required> </span>
					</div>
					<span class="help-block">Informe o nome do tipo de documento</span> <label class="checkbox">

						<button class="btn btn-primary" type="submit" name="_method" value="PUT">Salvar</button>
						<button class="btn btn-danger" type="reset">Limpar</button>
				</form>
			</div>
			<div class="panel-footer" align="center">
				<a href="<c:url value="/listaTipoDocumento"/>">
					<button type="button" class="btn btn-danger btn-footer">Voltar</button>
				</a>
			</div>
		</div>
	</div>
</div>