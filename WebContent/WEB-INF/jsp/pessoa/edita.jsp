<div class="container">
    <div class="row">


<div class="panel panel-default">
            <div class="panel-heading">
            
            <c:if test="${proximaPagina == 1}">
			<span>Alterar Pessoa</span>
		</c:if>
		
		<c:if test="${proximaPagina == 2}">
			<span>Alterar Paciente</span>
		</c:if>
		
		<c:if test="${proximaPagina == 3}">
			<span>Alterar Médico</span>
		</c:if>
		
		<c:if test="${proximaPagina == 4}">
			<span>Alterar Funcionário</span>
		</c:if>
		
            	<%@ include file="../headerUsuario.jsp"%>
            </div>
         </div>
         
		<form name="pessoaForm" id="pessoaForm" class="form-horizontal" action="<c:url value="/alterarPessoa/${pessoa.id}"/>" method="POST">
            <div id="linha"></div>
			<input type="hidden" name="proximaPagina" value="${proximaPagina}" />
			<input type="hidden" name="pessoa.tipoPessoa.id" value="${pessoa.tipoPessoa.id}" />
			
			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nome">Nome</label>
				<div class="col-md-4">
					<input type="text" name="pessoa.nome" value="${pessoa.nome}" class="form-control input-md" aria-describedby="basic-addon1" /> 
				</div>
			</div>
			<!-- Text input-->

			<!-- Text input-->
			<div class="form-group">
				<label class="col-md-4 control-label" for="nome">Data de Nascimento</label>
				<div class="col-md-4">
					<input type="text" name="pessoa.dataNascimento" value="${dataNascimentoFormatada}" class="form-control input-md" id="calendario" aria-describedby="basic-addon1" />
				</div>
			</div>
			<!-- Text input-->

			<!-- Text input-->
			<c:forEach items="${pessoaTipoDocumentoList}" var="pessoaTipoDocumento" varStatus="cont">
				<div class="form-group">
					<label class="col-md-4 control-label" for="nometipodocumento">${pessoaTipoDocumento.tipoDocumento.nome}</label>
					<div class="col-md-4">
						<input type="hidden" name="pessoaTipoDocumento[${cont.index}].tipoDocumento.id" value="${pessoaTipoDocumento.tipoDocumento.id}" /> 
						<input type="text" readonly="readonly"
							name="pessoaTipoDocumento[${cont.index}].numeroDocumento" id="cpf" value="${pessoaTipoDocumento.numeroDocumento}" class="form-control input-md"
							aria-describedby="basic-addon1" maxlength="20" /> 
					</div>
				</div>
			</c:forEach>
			<!-- Text input-->

			<c:forEach items="${pessoaContatoTelefonicoList}" var="pessoaContatoTelefonico" varStatus="cont">
				<c:set var="contadorTelefone" value="${cont.index}" />
				<div id="telefone${cont.index}" class="form-group">

					<!-- Text input-->
					<label class="col-md-4 control-label" for="textinput">Telefone</label>
					<div class="col-md-4">
						<input type="text" id="tin${cont.index}" name="pessoaContatoTelefonico[${cont.index}].numeroTelefone" value="${pessoaContatoTelefonico.numeroTelefone}"
							class="form-control" aria-describedby="basic-addon1" />
					</div>
				</div>
			</c:forEach>



			<c:forEach begin="${contadorTelefone + 1}" end="10" varStatus="cont">
				<div id="telefone${cont.index}" style="display: none;">
					
					<!-- Text input-->
					<label class="col-md-4 control-label" for="textinput">Telefone</label>
					<div class="col-md-4">
						<input type="text" id="tin${cont.index}" name="pessoaContatoTelefonico[${cont.index}].numeroTelefone" ${pessoaContatoTelefonico[cont.index].numeroTelefone } placeholder="Número de telefone"
							class="form-control" aria-describedby="basic-addon1" /> <span class="help-block">Informe o número de telefone</span>
					</div>
					<!-- Button -->
					<div class="form-group">
						<label class="col-md-4 control-label" for=""></label>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary btn-toggle" data-element="#telefone${cont.index + 1}">Adicionar telefone</button>
						</div>
					</div>
				</div>
			</c:forEach>

			<c:forEach items="${pessoaContatoEletronicoList}" var="pessoaContatoEletronico" varStatus="cont">
				<c:set var="contadorEletronico" value="${cont.index}" />
				<div id="email${cont.index}" class="form-group">

					<!-- Text input-->
					<label class="col-md-4 control-label" for="textinput">E-mail</label>
					<div class="col-md-4">
						<input type="text" name="pessoaContatoEletronico[${cont.index}].email" value="${pessoaContatoEletronico.email}" class="form-control" aria-describedby="basic-addon1" /> 
					</div>
				</div>
			</c:forEach>

			<!-- Button -->
			<div class="form-group">
				<label class="col-md-4 control-label" for=""></label>
				<!-- 
				<div class="col-md-4">
					<button id="btn-sumir2" type="button" class="btn btn-primary btn-toggle" data-element="#email${contadorEletronico + 1}">Adicionar email</button>
				</div>
				 -->
			</div>

			<c:forEach begin="${contadorEletronico + 1}" end="10" varStatus="cont">
				<div id="email${cont.index}" style="display: none;">

					<!-- Text input-->
					<label class="col-md-4 control-label" for="textinput">E-mail</label>
					<div class="col-md-4">
						<input type="text" name="pessoaContatoEletronico[${cont.index}].email" ${pessoaContatoEletronico[cont.index].email } class="form-control" aria-describedby="basic-addon1" /> 
					</div>
					<!-- Button -->
					<div class="form-group">
						<label class="col-md-4 control-label" for=""></label>
						<div class="col-md-4">
							<button type="button" class="btn btn-primary btn-toggle" data-element="#email${cont.index + 1}">Adicionar email</button>
						</div>
					</div>
				</div>
			</c:forEach>
			
<table style="width:100%;text-align:center;">
<tr>
<td>
<span class="btn-espaco">
	<button type="button" class="btn btn-danger" onClick="history.go(-1)" >Voltar</button>
</span>

<span class="btn-espaco"> 
	<button class="btn btn-default" type="reset">Limpar</button>
</span>

<span class="btn-espaco"> 
	<button class="btn btn-success" type="submit" name="_method" value="PUT">Salvar</button>
</span>
</td>
</tr>

</table>

		</form>
	</div>
    </div>
    
    


<script type="text/javascript">
	$(function() {
		$(".btn-toggle").click(function(e) {
			e.preventDefault();
			el = $(this).data('element');
			$(el).show(1000);
		});
		
		$('#btn-sumir').click(function() {
	    $("#btn-sumir").css("display","none");
		});
		
		$('#btn-sumir2').click(function() {
		$("#btn-sumir2").css("display","none");
		});
		
	});

	$('#pessoaForm').validate({
		rules : {
			"pessoa.nome" : {
				required : true,
				minlength : 3
			},
			"pessoa.dataNascimento" : {
				required : true
			}
		}
	});

	jQuery(function($){
		   $("#date").mask("99/99/9999");
		   $("#phone").mask("(999) 999-9999? x99999");
		   $("#tin").mask("99-99999999");
		   $("#tin0").mask("99-99999999");
		   $("#tin1").mask("99-99999999");
		   $("#tin2").mask("99-99999999");
		   $("#tin3").mask("99-99999999");
		   $("#tin4").mask("99-99999999");
		   $("#tin5").mask("99-99999999");
		   $("#tin6").mask("99-99999999");
		   $("#tin7").mask("99-99999999");
		   $("#tin8").mask("99-99999999");
		   $("#tin9").mask("99-99999999");
		   $("#tin10").mask("99-99999999");
		   $("#ssn").mask("999-99-9999");
		   $("#cpf").mask("999.999.999-99");
		});
	
</script>