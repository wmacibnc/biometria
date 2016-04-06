<div class="container">
	<div class="row">
	<div class="panel panel-default">
        <div class="panel-heading">
        <span>Editar Medicamento</span>
        	<%@ include file="../headerUsuario.jsp"%>
     	</div>
     </div>
     
      <form name="medicamentoForm" id="medicamentoForm" class="form-horizontal" action="<c:url value="/medicamento/${medicamento.id}"/>" method="POST">
<div id="linha3"></div>
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="fabricante">Fabricante/Laboratório: </label>
						<div class="col-md-6">
							<input type="text" name="medicamento.fabricante" value="${medicamento.fabricante}" class="form-control input-md" aria-describedby="basic-addon1" /> 
						</div>
					</div>
					<!-- Text input-->
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="medicamento">Nome do Medicamento: </label>
						<div class="col-md-6">
							<input type="text" name="medicamento.nome" value="${medicamento.nome}" class="form-control input-md" aria-describedby="basic-addon1" /> 
						</div>
					</div>
					<!-- Text input-->
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="preco">Preço de custo: </label>
						<div class="col-md-6">
							<input type="text" name="precoCusto" value="${medicamento.precoCusto}" class="form-control input-md" aria-describedby="basic-addon1" onKeyUp="maskIt(this,event,'###.###.###,##',true)" /> 
						</div>
					</div>
					<!-- Text input-->
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="preco">Preço de venda: </label>
						<div class="col-md-6">
							<input type="text" name="precoVenda" value="${medicamento.precoVenda}" class="form-control input-md" aria-describedby="basic-addon1" onKeyUp="maskIt(this,event,'###.###.###,##',true)" /> 
						</div>
					</div>
					<!-- Text input-->
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="qtd">Quantidade Mínima: </label>
						<div class="col-md-6">
							<input type="number" id="medicamento.quantidadeMinima" value="${medicamento.quantidadeMinima}" name="medicamento.quantidadeMinima" class="form-control input-md" aria-describedby="basic-addon1" min="0" /> 
						</div>
					</div>
					<!-- Text input-->
					
	<br />				
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
function maskIt(w,e,m,r,a){
// Cancela se o evento for Backspace
if (!e) var e = window.event
if (e.keyCode) code = e.keyCode;
else if (e.which) code = e.which;
// Variáveis da função
var txt  = (!r) ? w.value.replace(/[^\d]+/gi,'') : w.value.replace(/[^\d]+/gi,'').reverse();
var mask = (!r) ? m : m.reverse();
var pre  = (a ) ? a.pre : "";
var pos  = (a ) ? a.pos : "";
var ret  = "";
if(code == 9 || code == 8 || txt.length == mask.replace(/[^#]+/g,'').length) return false;
// Loop na máscara para aplicar os caracteres
for(var x=0,y=0, z=mask.length;x<z && y<txt.length;){
if(mask.charAt(x)!='#'){
ret += mask.charAt(x); x++; } 
else {
ret += txt.charAt(y); y++; x++; } }
// Retorno da função
ret = (!r) ? ret : ret.reverse()	
w.value = pre+ret+pos; }
// Novo método para o objeto 'String'
String.prototype.reverse = function(){
return this.split('').reverse().join(''); };
</script> 