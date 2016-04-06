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
				
				<form class="form-inline" id="pessoaForm" action="<c:url value="/filtroTratamentoAplicacao"/>" method="POST">
				<input type="hidden" name="paciente" value="${paciente.id}">
 				<p>Paciente: ${paciente.nome}</p>
				
				<c:forEach items="${paciente.listaPessoaTipoDocumento}" var="pessoaTipoDocumento" varStatus="cont">
 						<p>${pessoaTipoDocumento.tipoDocumento.nome} : ${pessoaTipoDocumento.numeroDocumento}</p> 				
 				</c:forEach>
				
				<p>
				<div class="form-group">
					<input type="radio" name="situacao" ${situacao eq 1 ? 'checked' : ''} value="1"> Em aberto<br>
  					<input type="radio" name="situacao" ${situacao eq 2 ? 'checked' : ''} value="2"> Finalizados<br>
  					<input type="radio" name="situacao" ${situacao eq 3 ? 'checked' : ''} value="3"> Todos
  				</div>
				</p>
				<input type="submit" value="Filtrar" class="btn btn-primary" />
				
				</form>
				
				<div class="displayTableFrame">
					<display:table class="table dataTable" id="tratamento" export="false" name="${tratamentoList}" size="resultSize" requestURI="/tratamentos/${paciente.id }">
						<display:column property="nome" title="Nome Tratamento" sortable="true" />
						<display:column property="tipoSituacaoTratamento.nome" title="Situação" sortable="true" format="{0,date,dd/MM/yyyy}" />
						<display:column property="medico.nome" title="Médico" sortable="true" />
						<display:column property="dataInicio" title="Data Inicio" sortable="true" format="{0,date,dd/MM/yyyy}" />
						
						<display:column title="Ação">
						<ul class="btn">
							<a href="<c:url value="/listaItemTratamentoA/${tratamento.id}"/>">
							<span class="btn btn-warning">Aplicar</span></a>
						</ul>
						</display:column>
						<display:setProperty name="paging.banner.placement" value="bottom" />
					</display:table>
				</div>
			</div>
			<div class="panel-footer"></div>
		</div>
	</div>
</div>

	</div>
<div id="footer">
<a href="<c:url value="/listaPaciente/3"/>">
				<button type="button" class="btn btn-danger btn-footer">Voltar</button>
			</a>
			</div>
