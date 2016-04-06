<div class="container">
	<div class="row">
	
		<div class="panel panel-default">
           <div class="panel-heading">
           <span>Tratamentos</span>
           	<%@ include file="../headerUsuario.jsp"%>
           </div>
        </div>

			<c:if test="${mensagem != null}">
					<div class="alert alert-success">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${mensagem}
					</div>
				</c:if>
				
				<form class="form-inline" id="pessoaForm" action="<c:url value="/filtroTratamento"/>" method="POST">
				<input type="hidden" name="paciente" value="${paciente.id}">
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
				
				</form>
				
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
		
		<c:if test="${tratamento.totalEntregue > 0}">
			<table  class="table" style="margin-top: -1em !important;">
				<thead>
					<tr>
					<th class="sortable">Sessão</th>
					
						<th class="sortable">Data Entrega</th>
						<th class="sortable">Entregue para</th>
					
						<th class="sortable">Data Aplicação</th>
						<th class="sortable">Aplicado por</th>
						
					</tr>
				</thead>

				<tbody>
				
				<c:forEach var="itemEntrega" items="${tratamento.listaItemEntrega}" varStatus="cont2">
		
					<input type="hidden" name="itemAplicacaos[${itemEntrega.id}].tratamento.id" value="${tratamento.id}" />
					<input type="hidden" name="itemAplicacaos[${itemEntrega.id}].quantidadeAplicada" value="1" />
					<input type="hidden" name="itemAplicacaos[${itemEntrega.id}].id" value="${itemEntrega.id}"/>
					
						<tr class="odd">
						      <td>
						      	<fmt:formatNumber value="${itemEntrega.sessao}"/>
						      </td>
						      
						      	<td>
						     		<fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${itemEntrega.dataEntrega}" />
						      	</td>
						      	
								<td>
						     		${itemEntrega.pessoaRecebedora.tipoPessoa.nome} - ${itemEntrega.pessoaRecebedora.nome}  
						      	</td>
						      	
						      	<td>
						     		<fmt:formatDate type="both" dateStyle="short" timeStyle="short"  value="${itemEntrega.dataAplicacao}" />
						      	</td>
						      	
						      	<td>
						     		${itemEntrega.enfermeira.nome}
						      	</td>
						      							      														
						</tr>
					</c:forEach>
				</tbody>

			</table>
			</c:if>
			
			<c:if test="${tratamento.totalEntregue == 0}">
				<p><b>Nenhum medicamento entregue/aplicado para este tratamento.</b></p>
			</c:if>
			
			</c:forEach>
			</div>
		</div>
		<br /><br /><br /><br /><br />
		
		<div id="footer">
		<a href="<c:url value="/listaPaciente/1"/>">
			<button type="button" class="btn btn-danger btn-footer">Voltar</button>
		</a>
</div>