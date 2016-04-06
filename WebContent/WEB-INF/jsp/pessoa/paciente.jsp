<div class="container">
	<div class="row">
		 <div class="panel panel-default">
            <div class="panel-heading">
            <span class="title-pager">Paciente</span>
            	<%@ include file="../headerUsuario.jsp"%>
            </div>
         </div>
           
			<c:if test="${mensagem != null}">
					<div class="alert alert-success">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${mensagem}
					</div>
				</c:if>
				
				<c:if test="${error != null}">
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        ${error}
                    </div>
                    </c:if>
				
				<div id="linha3"></div>
				<form action="<c:url value="/pessoa/buscaPaciente"/>">
					<div class="input-group">
						<input id="buscapessoa" name="nome" class="form-control"
							placeholder="Nome do paciente" aria-describedby="basic-addon1" />
						<span class="input-group-btn">
							<button class="btn btn-default btn-filtrar" type="submit"
								alt="Clique aqui para pesquisar">Filtrar</button>
						</span>
					</div>
				</form>
				
				<c:if test="${nome != null}">
					<h4>
						Busca pelo nome <span class="label label-default"><b>"${nome }"</b></span>
					</h4>
				</c:if>
				<div id="linha3"></div>
				<div class="displayTableFrame">
					<display:table class="table dataTable" id="pessoa" export="false"
						name="${pessoaList}" size="resultSize"
						requestURI="/listaPaciente">
						<display:column property="nome" title="Nome do Paciente" sortable="true" />
						
						<display:column title="CPF">
							<display:table name="${pessoa.listaPessoaTipoDocumento}" uid="numeroDocumento" class="simple"  style="text-align:center;">
								<display:column property="numeroDocumento" title=""/>
							</display:table>	
						</display:column>

						<display:column title="Ação">
						<c:if test="${proximaPagina == 1}">
							<ul class="btn">
								<a href="<c:url value="/pessoa/${pessoa.id}/2"/>">
								<span class="btn btn-default btn-acao">Alterar</span>
								</a>
							</ul>
							<div class="btn">
								<a href="<c:url value="/biometria/${pessoa.id}"/>">
									<button class="btn btn-default btn-acao"
										alt="Clique aqui cadastrar biometria">Biometria</button>
								</a>
							</div>
							<div class="btn">
								<a href="<c:url value="/tratamentos/${pessoa.id}/1"/>">
									<button class="btn btn-default btn-acao" alt="Clique aqui ver os tratametnos do paciente">Tratamentos</button>
								</a>
								</c:if>
								<c:if test="${proximaPagina == 2}">
								
								<form id="tratamentoForm" action="<c:url value="/listaTratamento"/>" method="POST">
										<input type="hidden" name="pessoa.id" value="${pessoa.id}" />
								<table>
									<tr width="100%">
										<td width="30%">
											<button class="btn btn-default btn-acao btn-tratamento" type="submit">Iniciar Tratamento</button>
										</td>
									</tr>
								</table>
								</form>
								</c:if>
								
								<c:if test="${proximaPagina == 3}">
								<form id="tratamentoForm" action="<c:url value="/listaAplicarMedicamento/1"/>" method="POST">
										<input type="hidden" name="pessoa.id" value="${pessoa.id}" />
									<button class="btn btn-default btn-acao" style="margin: 10px 0px !important;" type="submit">Aplicação</button>
								</form>
								</c:if>
								
								<c:if test="${proximaPagina == 4}">
								<form id="tratamentoForm" action="<c:url value="/listaEntregaMedicamento/1"/>" method="POST">
										<input type="hidden" name="pessoa.id" value="${pessoa.id}" />
									<button class="btn btn-default btn-acao" style="margin: 10px 0px !important;" type="submit">Entrega</button>
								</form>
								</c:if>
								
							</div>
						</display:column>
						<display:setProperty name="paging.banner.placement" value="bottom" />
					</display:table>
</div></div></div>
<br /><br /><br /><br /><br /><br />
<div id="footer">
			<a href="<c:url value="/telaInicial"/>">
				<button type="button" class="btn btn-danger btn-footer">Voltar</button>
			</a>
			<button type="button" class="btn btn-success btn-footer" data-toggle="modal"
				data-target="#novo">Novo Paciente</button>
		
</div>

<%@ include file="formularioPaciente.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buscapessoa").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : '<c:url value="pessoa/busca.json" />',
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
		$("#buscapessoa").puts("Busca por nome");
	});
</script>