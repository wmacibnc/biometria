<div class="container">
	<div class="row" align="center">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span>Biometria</span>
			</div>
		</div>

		<iframe
			src="http://52.36.222.170:8080/JavaAppStart/gravar.jsp?param=${idUsuario}"
			style="border: 0;"> </iframe>
<br />
		<div class="form-group col-sm-6">
			<button class="btn btn-default btn-inicial"
				onclick="javascript:window.history.go(-1)">Voltar</button>
		</div>
		<div class="form-group col-sm-6">
			<button class="btn btn-default btn-inicial" onclick="myFunction()">Tentar novamente</button>
		</div>

		<script>
			function myFunction() {
				location.reload();
			}
		</script>

	</div>
</div>
