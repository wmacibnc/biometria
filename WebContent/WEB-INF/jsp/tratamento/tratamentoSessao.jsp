<div class="container">
	<div class="row">
	
	<div class="panel panel-default">
           <div class="panel-heading">
           <span>Detalhes do Tratamento</span>
           	<%@ include file="../headerUsuario.jsp"%>
           </div>
        </div>
        
				<p><label>Tratamento: </label> ${tratamento.nome}</p>
				<p><label>Médico: </label> ${tratamento.medico.nome}</p> 
				<p><label>Paciente: </label> ${tratamento.paciente.nome}</p> 
				<p><label>Situação Tratamento: </label> ${tratamento.tipoSituacaoTratamento.nome}</p>
				
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
						<display:column property="dataAplicacao" title=" Data " format="{0,date,dd/MM/yyyy}"/>
						<display:column property="quantidadeAplicada" title=" Qtd Aplicada "/>
						</display:table>	
						</display:column>
						
						<display:setProperty name="paging.banner.placement" value="bottom" />
					</display:table>
				</div>
			</div>
	</div>
<div id="footer">
<a href="javascript:window.history.go(-1)">
				<button type="button" class="btn btn-danger btn-footer">Voltar</button>
			</a>
</div>