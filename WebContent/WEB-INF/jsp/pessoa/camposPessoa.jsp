					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="nome">Nome</label>
						<div class="col-md-4">
							<input type="text" name="pessoa.nome" class="form-control input-md" aria-describedby="basic-addon1" required="required"/> 
						</div>
					</div>
					<!-- Text input-->
					
						<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="nome">Data de Nascimento</label>
						<div class="col-md-4">
							<input type="text" name="pessoa.dataNascimento" class="form-control input-md" id="calendario" aria-describedby="basic-addon1" required="required" />
						</div>
					</div>
					<!-- Text input-->
					
<!-- 
					<div class="form-group">
						<label class="col-md-4 control-label" for="documento">Documentos</label>
					</div>
 -->

					<!-- Text input-->
						<c:forEach items="${tipoDocumentoList}" var="tipoDocumento" varStatus="cont">
					<div class="form-group">
							<label class="col-md-4 control-label" for="nometipodocumento">${tipoDocumento.nome}</label>
							<div class="col-md-4">
								<input type="hidden" name="pessoaTipoDocumento[${cont.index}].tipoDocumento.id" value="${tipoDocumento.id}" /> 
								<input type="text" name="pessoaTipoDocumento[${cont.index}].numeroDocumento" onblur="TestaCPF(this)" id="numeroCPF" class="form-control input-md" aria-describedby="basic-addon1" maxlength="20" required="required"/> 
							</div>
					</div>
						</c:forEach>
					
					
					<!-- Text input-->
					<!-- 
					<div class="form-group">
						<label class="col-md-4 control-label" for="cpf">Contato Telefonico</label>
					</div>
					 -->

					<!-- Text input-->
					<label class="col-md-4 control-label" for="textinput">Telefone</label>
					<div class="col-md-4">
						<input type="text" id="tin" name="pessoaContatoTelefonico[0].numeroTelefone" class="form-control" aria-describedby="basic-addon1" required="required"/>
					</div>

			<!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for=""></label>
                <!-- 
                <div class="col-md-4">
                    <button type="button" class="btn btn-primary btn-toggle" data-element="#telefone1">Adicionar telefone</button>
                </div>
                 -->
            </div>

            <c:forEach begin="1" end="10" varStatus="cont">

                    <div id="telefone${cont.index}" style="display: none;">

                    <!-- Text input-->
                    <label class="col-md-4 control-label" for="textinput">Telefone</label>
                    <div class="col-md-4">
                        <input type="text" id="tin${cont.index}" name="pessoaContatoTelefonico[${cont.index}].numeroTelefone" class="form-control" aria-describedby="basic-addon1" required="required"/>
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
            
            <!--
                    <div class="form-group">
                          <label class="col-md-4 control-label" for="cpf">Contato Eletrônico</label>
                    </div>
             -->

                    <!-- Text input-->
                    <label class="col-md-4 control-label" for="textinput">E-mail</label>
                    <div class="col-md-4">
                        <input type="text" name="pessoaContatoEletronico[0].email" class="form-control" aria-describedby="basic-addon1" required="required"/>
                    </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for=""></label>
                <!-- 
                <div class="col-md-4">
                    <button type="button" class="btn btn-primary btn-toggle" data-element="#email1">Adicionar e-mail</button>
                </div>
                 -->
            </div>
            
            <div class="form-group">
                        <label class="col-md-4 control-label" for="cpf">Autenticação</label>
                    </div>

                    <!-- Text input-->
                    <label class="col-md-4 control-label" for="textinput">Senha</label>
                    <div class="col-md-4">
                        <input type="password" id="senha1" maxlength="4" onkeypress='return SomenteNumero(event)' name="pessoaContatoEletronico[0].senha" class="form-control" aria-describedby="basic-addon1" required="required"/>
                    </div>
                    
                      <!-- Text input-->
                    <label class="control-label" style="margin-top: -33px; margin-left: 32px;" for="textinput">Confirmar Senha</label>
                    <div class="col-md-4">
                        <input onblur="validarSenha()" maxlength="4" onkeypress='return SomenteNumero(event)' type="password" id="senha2" name="pessoaContatoEletronico[0].confirmaSenha" class="form-control" aria-describedby="basic-addon1" required="required"/>
                    </div>
                    
                    <!-- Button -->
                    <!-- 
                <div class="form-group">
                    <label class="col-md-4 control-label" for=""></label>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-primary btn-toggle" data-element="#email1">Adicionar e-mail</button>
                        </div>
                </div>
                    -->
            

            <c:forEach begin="1" end="10" varStatus="cont">

                    <div id="email${cont.index}" style="display: none;">

                    <!-- Text input-->
                    <label class="col-md-4 control-label" for="textinput">E-mail</label>
                    <div class="col-md-4">
                        <input type="text" name="pessoaContatoEletronico[${cont.index}].email" placeholder="E-mail" class="form-control" aria-describedby="basic-addon1" />
                        <span class="help-block">Informe o e-mail</span>
                    </div>


                    <!-- Button -->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for=""></label>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-primary btn-toggle" data-element="#email${cont.index + 1}">Adicionar e-mail</button>
                        </div>
                    </div>
                </div>
                
            </c:forEach>
            
            <div id="linha4"></div>
<script type="text/javascript">
    $(function() {
        $(".btn-toggle").click(function(e) {
            e.preventDefault();
            el = $(this).data('element');
            $(el).show(1000);
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
            },
            "pessoaTipoDocumento[0].numeroDocumento" : {
                required : true
            },
            "pessoaContatoTelefonico[0].numeroTelefone" : {
                required : true
            },
            "pessoaContatoEletronico[0].email" : {
                required : true
            },
            "pessoaContatoEletronico[0].senha" : {
            	required : true,
                minlength : 4
            },
            "pessoaContatoEletronico[0].confirmaSenha" : {
            	required : true,
                minlength : 4
            }
        }
    });

    jQuery(function($){
           $("#date").mask("99/99/9999");
           $("#phone").mask("(999) 999-9999? x99999");
           $("#tin").mask("99-999999999");
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

   function validarSenha(){
       
       senha1 = document.getElementById('senha1').value;
       senha2 = document.getElementById('senha2').value;
    
       if (senha1 == senha2){
       }else{
           alert("As senhas informadas não conferem. Por favor! Digite novamente a senha e a confirmação de senha.")
           document.getElementById('senha1').value = "";
           document.getElementById('senha2').value = "";
       }
   }

   function SomenteNumero(e){
	    var tecla=(window.event)?event.keyCode:e.which;   
	    if((tecla>47 && tecla<58)) return true;
	    else{
	        if (tecla==8 || tecla==0) return true;
	    else  return false;
	    }
	}
</script>