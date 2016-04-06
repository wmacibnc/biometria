
<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Editar Fabricante</div>
			<div class="panel-body">
				<form name="funcionalidadeForm" id="funcionalidadeForm" action="<c:url value="/funcionalidade/${funcionalidade.id}"/>" method="POST">
					<div class="input-group col-md-2">
						<span class="input-group">Nome do Fabricante</span> <br /> <input id="funcionalidade.nome" value="${funcionalidade.nome}" name="funcionalidade.nome" class="form-control col-md-2"
							placeholder="Nome do Fabricante" aria-describedby="basic-addon1" /> <span class="input-group-btn" required> </span>
					</div>
					<span class="help-block">Informe o nome do funcionalidade</span> <label class="checkbox">

						<button class="btn btn-primary" type="submit" name="_method" value="PUT">Salvar</button>
						<button class="btn btn-danger" type="reset">Limpar</button>
				</form>
			</div>
			<div class="panel-footer" align="center">
				<a href="<c:url value="/listaFabricante"/>">
					<button type="button" class="btn btn-danger btn-footer">Voltar</button>
				</a>
			</div>
		</div>
	</div>
</div>