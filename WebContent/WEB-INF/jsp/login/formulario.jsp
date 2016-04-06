<div class="container">
	<div class="row">
	
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Sistema Reviv</div>
					<div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="#"></a>
					</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">
					<div style="display: none" id="login-alert" class="alert alert-danger col-sm-12"></div>
					<form id="medicamentoForm" action="<c:url value="/login/autentica"/>" method="POST" class="form-horizontal" role="form">
					
					<c:if test="${error != null}">
					<div class="alert alert-danger">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${error}
					</div>
				    </c:if>
					
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
				<div class="panel-footer" align="right" style="font-size:12px;color:#666;"> Desenvolvido por Ótimo Tecnologia </div>
			</div>
		</div>

		</div>
	</div>
	
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


$(document).ready(function() {
    $("#MostrarEsconderMensagem").click(MostrarEsconderMensagem);
    });
function MostrarEsconderMensagem(){
    $("#Mensagem").toggle();
}
</script>
<button type="button" id="MostrarEsconderMensagem">+/-</button>

<div id="Mensagem" style="display:none">
<div class="container">
	<div class="row">

<div class="panel panel-default">
					<div class="panel-heading">Lista de perfis de teste</div>
					<div class="panel-body">
	<table class="table">
				<tr>
					<td><b>Perfil</b></td>
					<td><b>Login</b></td>
					<td><b>Senha</b></td>
				</tr>

				<tr>
					<td>Enfermeira</td>
					<td>111.111.111-11</td>
					<td>1234</td>
				</tr>

				<tr>
					<td>Financeiro</td>
					<td>222.222.222-22</td>
					<td>1234</td>
				</tr>

				<tr>
					<td>Médico</td>
					<td>333.333.333-33</td>
					<td>1234</td>
				</tr>

				<tr>
					<td>Comercial</td>
					<td>444.444.444-44</td>
					<td>1234</td>
				</tr>

				<tr>
					<td>Farmacéutico</td>
					<td>555.555.555-55</td>
					<td>1234</td>
				</tr>
				
			</table>
		</div>
	</div>
</div>
</div>
</div>

<iframe
			src="http://52.36.222.170:8080/JavaAppStart/gravar.jsp?param=${idUsuario}"
			style="border: 0;"> </iframe>