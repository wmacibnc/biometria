
<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Editar OperacaoEstoque</div>
			<div class="panel-body">
				<form name="operacaoEstoqueForm" id="operacaoEstoqueForm" action="<c:url value="/operacaoEstoque/${operacaoEstoque.id}"/>" method="POST">
					<div class="input-group col-md-2">
						<span class="input-group">Nome do OperacaoEstoque</span> <br /> <input id="operacaoEstoque.nome" value="${operacaoEstoque.nome}" name="operacaoEstoque.nome" class="form-control col-md-2"
							placeholder="Nome do OperacaoEstoque" aria-describedby="basic-addon1" /> <span class="input-group-btn" required> </span>
					</div>
					<span class="help-block">Informe o nome do operacaoEstoque</span> <label class="checkbox">

						<button class="btn btn-primary" type="submit" name="_method" value="PUT">Salvar</button>
						<button class="btn btn-danger" type="reset">Limpar</button>
				</form>
			</div>
			<div class="panel-footer" align="center">
				<a href="<c:url value="/listaOperacaoEstoque"/>">
					<button type="button" class="btn btn-danger btn-footer">Voltar</button>
				</a>
			</div>
		</div>
	</div>
</div>