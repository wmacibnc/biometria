
<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Editar Perfil</div>
			<div class="panel-body">
				<form name="perfilForm" id="perfilForm" action="<c:url value="/perfil/${perfil.id}"/>" method="POST">
					<div class="input-group col-md-2">
						<span class="input-group">Nome do Perfil</span> <br /> <input id="perfil.nome" value="${perfil.nome}" name="perfil.nome" class="form-control col-md-2"
							placeholder="Nome do Perfil" aria-describedby="basic-addon1" /> <span class="input-group-btn" required> </span>
					</div>
					<span class="help-block">Informe o nome do perfil</span> <label class="checkbox">

						<button class="btn btn-primary" type="submit" name="_method" value="PUT">Salvar</button>
						<button class="btn btn-danger" type="reset">Limpar</button>
				</form>
			</div>
			<div class="panel-footer" align="center">
			</div>
		</div>
		<div align="center">
		<a href="<c:url value="/listaPerfil"/>">
					<button type="button" class="btn btn-danger btn-footer">Voltar</button>
				</a>
				</div>
	</div>
</div>