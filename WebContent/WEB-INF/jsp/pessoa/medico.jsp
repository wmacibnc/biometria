<div class="container">
    <div class="row">
   
   		<div class="panel panel-default">
            <div class="panel-heading">
            <span class="title-pager">Lista de Médicos</span>
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
				
                <div class="displayTableFrame">
                <display:table class="table dataTable" id="pessoa" export="false" name="${pessoaList}" requestURI="/listaMedico">
                    <display:column property="nome" title="Nome" sortable="true" />
                   <display:column title="CPF">
							<display:table name="${pessoa.listaPessoaTipoDocumento}" uid="numeroDocumento" class="simple"  style="text-align:center;">
								<display:column property="numeroDocumento" title=""/>
							</display:table>	
						</display:column>
                    
                    	<display:column title="Telefone" >
                    	<display:table name="${pessoa.listaPessoaContatoTelefonico}" uid="telefone" class="simple"  style="text-align:center;">
						<display:column property="numeroTelefone" title="" />
						</display:table>
						</display:column>
						
						<display:column title="E-mail">
						<display:table name="${pessoa.listaPessoaContatoEletronico}" uid="email" class="simple"  style="text-align:center;">
						<display:column property="email" title=""/>
						</display:table>
						</display:column>
						
						
                    <display:column title="Ação">
                        <ul class="btn">
                            <a href="<c:url value="/pessoa/${pessoa.id}/3"/>"><span class="btn btn-default btn-acao">Alterar</span></a>
                        </ul>
                    </display:column>
                    <display:setProperty name="paging.banner.placement" value="bottom" />
                </display:table>
                </div>
            </div>
    </div>
<div id="footer">
<a href="<c:url value="/telaInicial"/>">
	<button type="button" class="btn btn-danger btn-footer">Voltar</button>
</a>

<button type="button" class="btn btn-success btn-footer" data-toggle="modal" data-target="#novo">Novo Médico</button>
</div>

<%@ include file="formularioMedico.jsp"%>
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