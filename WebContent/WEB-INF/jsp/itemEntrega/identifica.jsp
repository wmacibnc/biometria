<!-- Modal -->
<div id="identifica" class="modal fade" role="dialog">
				<div class="panel panel-default">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Responsável por receber o medicamento</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">
					<div style="display: none" id="login-alert" class="alert alert-danger col-sm-12"></div>
					<form id="medicamentoForm" action="<c:url value="/identificaReceber"/>" method="POST" class="form-horizontal" role="form">
					
					<input type="hidden" name="idTratamento" value="${idTratamento}" />
					<!-- 
					<c:if test="${error != null}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert">×</button>
								${error}
						</div>
					</c:if>
					-->
					
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-user"></i>
							</span>
								<input id="numeroCPF" type="text" class="form-control" name="usuario.login" placeholder="Informe o seu CPF" maxlength="20" required onblur="TestaCPF(this)">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon">
								<i class="glyphicon glyphicon-lock"></i>
							</span> 
							
							<input id="login-password" type="password" class="form-control" name="usuario.senha" placeholder="Informe sua Senha">
						</div>

						<div style="margin-top: 10px" class="form-group">
						
							<!-- Button -->
							<div class="col-sm-12 controls" align="center">
								<button class="btn btn-success btn-footer" type="submit">Logar</button>
							</div>
						</div>
					</form>
				</div>
				</div>
			</div>

		</div>
	</div>
	<!-- Modal -->
	
<script type="text/javascript">
jQuery(function($){
    $("#numeroCPF").mask("999.999.999-99");
 });

function TestaCPF(strCPF){
 if(!CPFValido(strCPF.value.toString().replace(/[^\d]+/g,''))){
        alert("CPF inválido");
        document.getElementById('numeroCPF').value = "";
 }
}

function CPFValido(strCPF) {
 var Soma;
 var Resto;
 Soma = 0;
 if (strCPF == "00000000000")
     return false;
 for (i = 1; i <= 9; i++)
     Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
 Resto = (Soma * 10) % 11;
 if ((Resto == 10) || (Resto == 11))
     Resto = 0;
 if (Resto != parseInt(strCPF.substring(9, 10)))
     return false;
 Soma = 0;
 for (i = 1; i <= 10; i++)
     Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
 Resto = (Soma * 10) % 11;
 if ((Resto == 10) || (Resto == 11))
     Resto = 0;
 if (Resto != parseInt(strCPF.substring(10, 11)))
     return false;
 return true;
}
</script>