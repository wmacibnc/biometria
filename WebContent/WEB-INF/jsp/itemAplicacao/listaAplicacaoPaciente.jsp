<style>

.centraliza {
  position: absolute;
  top: 50%;
  left: 50%;
}

.on-off {
  display: inline-block;
  position: relative;
  overflow: hidden;
  padding: 0px 12px;
  border-radius: 30px;
}
.on-off span a {
  content: "";
  position: absolute;
  top: 0;
  right: 50%;
  z-index: 2;
  width: 50%;
  height: 100%;
  border: 1px solid rgba(0, 0, 0, 0.3);
  border-radius: 50%;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  -moz-box-sizing: border-box;
  -ms-box-sizing: border-box;
  -o-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  transition: right 250ms ease-out;
  cursor: pointer;
  background-color: #ffffff;
  *zoom: 1;
  filter: progid:DXImageTransform.Microsoft.gradient(gradientType=0, startColorstr='#FFFFFFFF', endColorstr='#FFE0E0E0');
  background-image: url('data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4gPHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGRlZnM+PGxpbmVhckdyYWRpZW50IGlkPSJncmFkIiBncmFkaWVudFVuaXRzPSJvYmplY3RCb3VuZGluZ0JveCIgeDE9IjAuNSIgeTE9IjAuMCIgeDI9IjAuNSIgeTI9IjEuMCI+PHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZmZmZiIvPjxzdG9wIG9mZnNldD0iMTAwJSIgc3RvcC1jb2xvcj0iI2UwZTBlMCIvPjwvbGluZWFyR3JhZGllbnQ+PC9kZWZzPjxyZWN0IHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIGZpbGw9InVybCgjZ3JhZCkiIC8+PC9zdmc+IA==');
  background-size: 100%;
  background-image: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #ffffff), color-stop(100%, #e0e0e0));
  background-image: -moz-linear-gradient(top, #ffffff 0%, #e0e0e0 100%);
  background-image: -webkit-linear-gradient(top, #ffffff 0%, #e0e0e0 100%);
  background-image: linear-gradient(to bottom, #ffffff 0%, #e0e0e0 100%);
}
.on-off span a:after {
  content: "";
  position: absolute;
  width: 60%;
  height: 60%;
  top: 20%;
  left: 20%;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.35) inset;
  background: white;
  border-radius: 50%;
  -moz-box-sizing: border-box;
  -ms-box-sizing: border-box;
  -o-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
.on-off span:before, .on-off span:after {
  content: "";
  position: absolute;
  top: 0;
  left: 0%;
  z-index: 1;
  width: 100%;
  height: 100%;
  transition: left 250ms ease-out;
  font-family: sans-serif;
  font-size: 10px;
  text-align: right;
  padding-top: 6px;
  padding-right: 7px;
  color: rgba(255, 255, 255, 0.6);
  cursor: pointer;
  -moz-box-sizing: border-box;
  -ms-box-sizing: border-box;
  -o-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  background-color: #9e9e9e;
  *zoom: 1;
  filter: progid:DXImageTransform.Microsoft.gradient(gradientType=0, startColorstr='#FF9E9E9E', endColorstr='#FFBFBFBF');
  background-image: url('data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4gPHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGRlZnM+PGxpbmVhckdyYWRpZW50IGlkPSJncmFkIiBncmFkaWVudFVuaXRzPSJvYmplY3RCb3VuZGluZ0JveCIgeDE9IjAuNSIgeTE9IjAuMCIgeDI9IjAuNSIgeTI9IjEuMCI+PHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzllOWU5ZSIvPjxzdG9wIG9mZnNldD0iMTAwJSIgc3RvcC1jb2xvcj0iI2JmYmZiZiIvPjwvbGluZWFyR3JhZGllbnQ+PC9kZWZzPjxyZWN0IHg9IjAiIHk9IjAiIHdpZHRoPSIxMDAlIiBoZWlnaHQ9IjEwMCUiIGZpbGw9InVybCgjZ3JhZCkiIC8+PC9zdmc+IA==');
  background-size: 100%;
  background-image: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #9e9e9e), color-stop(100%, #bfbfbf));
  background-image: -moz-linear-gradient(top, #9e9e9e 0%, #bfbfbf 100%);
  background-image: -webkit-linear-gradient(top, #9e9e9e 0%, #bfbfbf 100%);
  background-image: linear-gradient(to bottom, #9e9e9e 0%, #bfbfbf 100%);
}
.on-off span:after {
  content: "";
  left: -100%;
  background-color: #9ee62a;
  *zoom: 1;
  filter: progid:DXImageTransform.Microsoft.gradient(gradientType=0, startColorstr='#FF9EE62A', endColorstr='#FF9FE509');
  background-image: url('data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz4gPHN2ZyB2ZXJzaW9uPSIxLjEiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGRlZnM+PGxpbmVhckdyYWRpZW50IGlkPSJncmFkIiBncmFkaWVudFVuaXRzPSJvYmplY3RCb3VuZGluZ0JveCIgeDE9IjAuNSIgeTE9IjAuMCIgeDI9IjAuNSIgeTI9IjEuMCI+PHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iIzllZTYyYSIvPjxzdG9wIG9mZnNldD0iNTAlIiBzdG9wLWNvbG9yPSIjOTdlZDE3Ii8+PHN0b3Agb2Zmc2V0PSI1MiUiIHN0b3AtY29sb3I9IiM3ZWU1MTAiLz48c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiM5ZmU1MDkiLz48L2xpbmVhckdyYWRpZW50PjwvZGVmcz48cmVjdCB4PSIwIiB5PSIwIiB3aWR0aD0iMTAwJSIgaGVpZ2h0PSIxMDAlIiBmaWxsPSJ1cmwoI2dyYWQpIiAvPjwvc3ZnPiA=');
  background-size: 100%;
  background-image: -webkit-gradient(linear, 50% 0%, 50% 100%, color-stop(0%, #9ee62a), color-stop(50%, #97ed17), color-stop(52%, #7ee510), color-stop(100%, #9fe509));
  background-image: -moz-linear-gradient(top, #9ee62a 0%, #97ed17 50%, #7ee510 52%, #9fe509 100%);
  background-image: -webkit-linear-gradient(top, #9ee62a 0%, #97ed17 50%, #7ee510 52%, #9fe509 100%);
  background-image: linear-gradient(to bottom, #9ee62a 0%, #97ed17 50%, #7ee510 52%, #9fe509 100%);
}
.on-off input {
  position: relative;
  z-index: 999;
  visibility: hidden;
}
.on-off input:checked + span:before {
  left: 100%;
}
.on-off input:checked + span:after {
  left: 0%;
}

input:checked + span a {
  right: 0%;
}
</style>

	<div class="container">
		<div class="row">

			<div class="panel panel-default">
				<div class="panel-heading">
					<span class="title-pager">Tratamento</span>
					<%@ include file="../headerUsuario.jsp"%>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			
			<table width="100%" style="margin-top: 20px;">
					<tr>
						<td> 
							<b>Paciente:</b> 
							${paciente.nome} 
						</td>
						
						<td align="right">
						 <c:forEach items="${paciente.listaPessoaTipoDocumento}" var="pessoaTipoDocumento" varStatus="cont">
 							<b>${pessoaTipoDocumento.tipoDocumento.nome} :</b> ${pessoaTipoDocumento.numeroDocumento} 				
 						</c:forEach>
						</td>
						
					</tr>
				</table>
				
			
		<c:forEach var="tratamento" items="${tratamentoList}" varStatus="cont">
			<br />
				<table width="100%"  class="table table-bordered">
					<tr>
						<td colspan="3" style="background-color: #f9f9f9;">
							<b>Médico: </b> ${tratamento.medico.nome}						
						</td>
					</tr>
					<tr>
						<td width="33%"> <b>Tratamento: </b> ${tratamento.nome} </td>
						<td width="34%" align="center"> <b>Medicamento: </b> ${tratamento.medicamento.nome}  </td>
						<td width="33%" align="right"> <b>Total de Sessões: </b> ${tratamento.quantidadeTotal} </td>
					</tr>
				</table>
		
			<table class="table">
				<thead>
					<tr>
					<th class="sortable">Sessão</th>
					
						<th class="sortable">Entregue para</th>
						<th class="sortable">Data Entrega</th>
						<th class="sortable">Aplicação</th>
					</tr>
				</thead>

				<tbody>
				
		<form action="<c:url value="/finalizarAplicacaoMedicamento"/>" method="POST">		
				<c:forEach var="itemEntrega" items="${tratamento.listaItemEntrega}" varStatus="cont2">
		
					<c:if test="${!itemEntrega.bolAplicacao}">
					<input type="hidden" name="itemAplicacaos[${itemEntrega.id}].tratamento.id" value="${tratamento.id}" />
					<input type="hidden" name="itemAplicacaos[${itemEntrega.id}].quantidadeAplicada" value="1" />
					<input type="hidden" name="itemAplicacaos[${itemEntrega.id}].id" value="${itemEntrega.id}"/>
					
						<tr class="odd">
						      <td>
						      	<fmt:formatNumber value="${itemEntrega.sessao}"/>
						      </td>
						      
								<td>
						     		${itemEntrega.pessoaRecebedora.tipoPessoa.nome} - ${itemEntrega.pessoaRecebedora.nome}
						      	</td>
						      	
						      	<td>
						     		<fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${itemEntrega.dataEntrega}" />
						      	</td>
						      	
							<td>
								<label class="on-off">
    								<input type="radio" name="itemAplicacaos[${itemEntrega.id}].sim"/>
    								<span><a></a></span>
  								</label>
							</td>
						</tr>
					</c:if>
					</c:forEach>
				</tbody>

			</table>
			</c:forEach>
		</div>
	</div>
	<br /><br /><br /><br /><br /><br /><br />
	
	
<div id="footer">
	<a href="<c:url value="/telaInicial"/>">
		<button type="button" class="btn btn-danger btn-footer">Voltar</button>
	</a>
	
		<button type="submit" class="btn btn-success btn-footer" name="_method" value="POST" onclick="return confirm('Confirma aplicação dos medicamentos?');">Aplicar</button>
</div>

</form>

<script type="text/javascript">
	$(document).ready(function() {
		$("#buscamedicamento").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : '<c:url value="medicamento/busca.json" />',
					data : {
						q : request.term
					},
					success : function(data) {

						response($.map(data, function(item) {
							return {
								label : item.nome,
								value : item.nome
							};
						}));
					},
				});
			},
			minLength : 1,
		});
		$("#buscamedicamento").puts("Busca por nome");
	});
</script>