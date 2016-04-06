
<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Editar Dados Medicamento - Médico:
				${medico.nome}</div>
			<div class="panel-body">
				<form name="pessoaMedicamentoForm" id="pessoaMedicamentoForm"
					action="<c:url value="/pessoaMedicamento/${pessoaMedicamento.id}"/>"
					method="POST">

					<!-- Select Basic -->
					<div class="form-group">
						<label class="col-md-6 control-label" for="selectbasic">Medicamento</label>
						<select name="pessoaMedicamento.medicamento.id"
							class="form-control" placeholder="Nome do Medicamento"
							aria-describedby="basic-addon1">
							<c:forEach items="${medicamentoList}" var="medicamento">
								<option
									${pessoaMedicamento.medicamento.id eq medicamento.id ? 'selected' : ''}
									value="${medicamento.id}">${medicamento.nome}-
									${medicamento.fabricante}</option>
							</c:forEach>
						</select> <span class="help-block">Selecione o medicamento</span>
					</div>

					<!-- input -->
					<div class="input-group col-md-6">
						<span class="input-group">Data de Vencimento</span> <br /> <input
							id="date" name="pessoaMedicamento.dataVencimento"
							value="${pessoaMedicamento.dataVencimento}"
							class="form-control col-md-2" placeholder="Data de vencimento"
							aria-describedby="basic-addon1" /> <span class="input-group-btn"
							required> </span>
					</div>
					<span class="help-block">Informe a data de vencimento do
						medicamento</span>
					<!-- input -->

					<button class="btn btn-primary" type="submit" name="_method"
						value="PUT">Salvar</button>
					<button class="btn btn-danger" type="reset">Limpar</button>
				</form>
			</div>

			<div class="panel-footer"></div>
		</div>
		<div align="center">
			<a href="<c:url value="/listaPessoaMedicamento"/>">
				<button type="button" class="btn btn-danger btn-footer">Voltar</button>
			</a>
		</div>
	</div>

	<script type="text/javascript">
    
    $("#date").mask("99/99/9999");
    $("#moeda").maskMoney();
    $("#moeda2").maskMoney();
    $("#moeda3").maskMoney();

    $("#moeda2").blur(function(){
        var porcentagem = document.getElementById("moeda2").value;
        var custo = document.getElementById("moeda").value;
        var valor1 = parseFloat(porcentagem);
        var valor2 = parseFloat(custo);
        var valor3 = (valor1/100) * valor2;
        var valor4 = valor3 + valor2;
        document.getElementById("moeda3").value = valor4*100;
    });

</script>