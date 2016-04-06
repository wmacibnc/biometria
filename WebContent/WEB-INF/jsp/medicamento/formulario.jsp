<!-- Modal -->
<div id="novo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-body">
				<div class="panel panel-default">
					<div class="panel-heading">Adicionar Medicamento</div>
					<div class="panel-body">
						<form id="medicamentoForm" action="<c:url value="/medicamento"/>" class="form-horizontal" method="POST">


					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="fabricante">Fabricante/Laboratório: </label>
						<div class="col-md-8">
							<input type="text" name="medicamento.fabricante" value="${medicamento.fabricante}" class="form-control input-md" aria-describedby="basic-addon1" /> 
						</div>
					</div>
					<!-- Text input-->
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="medicamento">Nome do Medicamento: </label>
						<div class="col-md-8">
							<input type="text" name="medicamento.nome" value="${medicamento.nome}" class="form-control input-md" aria-describedby="basic-addon1" /> 
						</div>
					</div>
					<!-- Text input-->
					
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="preco">Preço de custo: </label>
						<div class="col-md-8">
							<input type="text" name="precoCusto" value="${precoCusto}" class="form-control input-md" aria-describedby="basic-addon1" onKeyUp="maskIt(this,event,'###.###.###,##',true)" /> 
						</div>
					</div>
					<!-- Text input-->
					
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="preco">Preço de venda: </label>
						<div class="col-md-8">
							<input type="text" name="precoVenda" value="${precoVenda}" class="form-control input-md" aria-describedby="basic-addon1" onKeyUp="maskIt(this,event,'###.###.###,##',true)" /> 
						</div>
					</div>
					<!-- Text input-->
					
					
					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label" for="qtd">Quantidade Mínima: </label>
						<div class="col-md-8">
							<input type="number" id="medicamento.quantidadeMinima" value="${medicamento.quantidadeMinima}" name="medicamento.quantidadeMinima" class="form-control input-md" aria-describedby="basic-addon1" min="0" /> 
						</div>
					</div>
					<!-- Text input-->
</div>					
							
<table style="width:100%;text-align:center;">
	<tr>
		<td>
			<span class="btn-espaco">
				<button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
			</span>
			<span class="btn-espaco"> 
				<button class="btn btn-default" type="reset">Limpar</button>
			</span>
			<span class="btn-espaco"> 
				<button class="btn btn-success" type="submit">Salvar</button>
			</span>
		</td>
	</tr>
</table>
<div id="linha3"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<script type="text/javascript">
		$(function() {
			$(".btn-toggle").click(function(e) {
				e.preventDefault();
				el = $(this).data('element');
				$(el).show(1000);
			});
		});

		$('#medicamentoForm').validate({
			rules : {
				"medicamento.nome" : {
					required : true,
					minlength : 3
				}
			}
		});

		jQuery(function($) {
			$("#date").mask("99/99/9999");
			$("#phone").mask("(999) 999-9999? x99999");
			$("#tin").mask("99-99999999");
			$("#ssn").mask("999-99-9999");
			$("#cpf").mask("999.999.999-99");
		});
	</script>
	
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