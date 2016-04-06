<form id="aplicarMedicamentoForm" action="<c:url value="/aplicarMedicamentoLista"/>" method="POST">
<div class="container">
	<div class="row">
	
	<div class="panel panel-default">
			<div class="panel-heading">
			<span>Aplicação de Medicamento</span>
			<%@ include file="../headerUsuario.jsp"%>			
			</div>
		</div>
			
				<p><label>Tratamento: </label> ${Tratamento.nome}</p>
				<p><label>Médico: </label> ${Tratamento.medico.nome}</p> 
				<p><label>Paciente: </label> ${Tratamento.paciente.nome}</p> 
				<p><label>Situação Tratamento: </label> ${Tratamento.tipoSituacaoTratamento.nome}</p>
				<c:if test="${mensagem != null}">
					<div class="alert alert-success">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${mensagem}
					</div>
				</c:if>

				<div class="displayTableFrame">
					<display:table class="table" id="itemTratamento" export="false" name="${itemTratamentoList}" size="resultSize" requestURI="/listaItemtratamento/${itemTratamento.tratamento.id}">
						<c:set var="total" value="0" />  
						<display:column property="medicamento.nome" title="Medicamento" sortable="true" />
						
						<display:column property="dataPrevista" title="Data Prevista" sortable="true" format="{0,date,dd/MM/yyyy}"/>
						<display:column property="quantidade" title="Qtd" sortable="true" />
						
						<display:column title="Entregas">
						<display:table name="${itemTratamento.listaItemEntrega}" uid="itemEntrega" class="simple"  style="text-align:center;"> 
						<display:column property="dataAplicacao" title=" Data "  format="{0,date,dd/MM/yyyy}"/>
						<display:column property="quantidadeAplicada" title=" Qtd " />
						</display:table>	
						</display:column>
					
						<display:column title="Aplicações">
						<display:table name="${itemTratamento.listaItemAplicacao}" uid="itemAplicacao" class="simple"  style="text-align:center;">
						${total = total + itemAplicacao.quantidadeAplicada} 
						<display:column property="dataAplicacao" title=" Data " sortable="true" format="{0,date,dd/MM/yyyy}"/>
						<display:column property="quantidadeAplicada" title=" Qtd Aplicada " sortable="true" />
						</display:table>	
						</display:column>
						
						
						<display:column title="Hoje">
							<jsp:useBean id="data" class="java.util.Date" />
							<fmt:formatDate value="${data}" />
						</display:column>
						
						<display:column title="Qtd. Restante">
						<c:set var="restantes" value="${itemTratamento.quantidade - total}" />
						${restantes}
						</display:column>
						
						<display:column title="Qtd aplicada">
							<input type="number" id="qtd${itemTratamento.id}" name="itemTratamento[${itemTratamento.id}].quantidadeAplicada" class="form-control" maxlength="3" min="1" max="${restantes}"/>
							<input type="hidden" name="itemTratamento[${itemTratamento.id}].id" value="${itemTratamento.id}">
							
									<script>
										var qtd${itemTratamento.id} = 'qtd${itemTratamento.id}';
										var searchTimeout${itemTratamento.id};
										document.getElementById(qtd${itemTratamento.id}).onkeypress = function() {
											if (searchTimeout${itemTratamento.id} != undefined)
												clearTimeout(searchTimeout${itemTratamento.id});
											searchTimeout${itemTratamento.id} = setTimeout(
													callServerScript${itemTratamento.id}, 250);
										};
										function callServerScript${itemTratamento.id}() {
											if (document.getElementById(qtd${itemTratamento.id}).value > document
													.getElementById(qtd${itemTratamento.id}).max) {
												alert("Valor inválido!");
											}
										}
									</script>
								</display:column>
						
						
						<display:setProperty name="paging.banner.placement" value="bottom" />
						
					</display:table>
				</div>
			</div>
			</div>
<div id="footer">
<a href="<c:url value="/listaPaciente/3"/>">
	<button type="button" class="btn btn-danger btn-footer">Voltar</button>
</a>
<button class="btn btn-primary btn-footer" type="submit" alt="Salvar">Salvar</button>	
</div>
			</form>