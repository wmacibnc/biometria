<div class="container">
    <div class="row">
    
    <div class="panel panel-default">
            <div class="panel-heading">
            <span class="title-pager">Lista de Funcion�rios</span>
            	<%@ include file="../headerUsuario.jsp"%>
            </div>
        </div>
           
			<c:if test="${mensagem != null}">
					<div class="alert alert-success">
						<button type="button" class="close" data-dismiss="alert">�</button>
						${mensagem}
					</div>
				</c:if>
				
				<c:if test="${error != null}">
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert">�</button>
                        ${error}
                    </div>
                    </c:if>
				
				
            <!-- 
                <form action="<c:url value="/pessoa/busca"/>">
                    <div class="input-group">
                        <input id="buscapessoa" name="nome" class="form-control" placeholder="Nome do Pessoa" aria-describedby="basic-addon1" /> <span class="input-group-btn">
                            <button class="btn btn-default btn-filtrar" type="submit" alt="Clique aqui para pesquisar">Filtrar</button>
                        </span>
                    </div>
                </form>
             -->
                <c:if test="${nome != null}">
                    <h4>
                        Busca pelo nome <span class="label label-default"><b>"${nome }"</b></span>
                    </h4>
                </c:if>
                
                <div class="displayTableFrame">
                <display:table class="table dataTable" id="pessoa" export="false" name="${pessoaList}" requestURI="/listaPessoa">
                    <display:column property="tipoPessoa.nome" title="Tipo" sortable="true" />
                    <display:column property="nome" title="Nome" sortable="true" />
                    <display:column title="CPF">
							<display:table name="${pessoa.listaPessoaTipoDocumento}" uid="numeroDocumento" class="simple"  style="text-align:center;">
								<display:column property="numeroDocumento" title=""/>
							</display:table>	
						</display:column>
                    
                    <display:column title="Telefone">
                    	<display:table name="${pessoa.listaPessoaContatoTelefonico}" uid="telefone" class="simple"  style="text-align:center;">
						<display:column property="numeroTelefone" title="" />
						</display:table>
						</display:column>
						
						<display:column title="E-mail">
						<display:table name="${pessoa.listaPessoaContatoEletronico}" uid="email" class="simple"  style="text-align:center;">
						<display:column property="email" title=""/>
						</display:table>
						</display:column>
						
                    
                    <display:column title="A��o">
                        <ul class="btn">
                            <a href="<c:url value="/pessoa/${pessoa.id}/4"/>"><span class="btn btn-default btn-acao">Alterar</span></a>
                        </ul>
                    </display:column>
                    <display:setProperty name="paging.banner.placement" value="bottom" />
                </display:table>
                </div>
            </div>
</div>

<div id="footer">
<c:if test="${usuarioLogado.usuario.perfil.id == 5}">
<a href="<c:url value="/telaInicial"/>">
	<button type="button" class="btn btn-danger btn-footer">Voltar</button>
</a>
</c:if>

<c:if test="${usuarioLogado.usuario.perfil.id == 4}">
<a href="<c:url value="/medicoAdm"/>">
	<button type="button" class="btn btn-danger btn-footer">Voltar</button>
</a>
</c:if>

<c:if test="${usuarioLogado.usuario.perfil.id != 4 && usuarioLogado.usuario.perfil.id != 5}">
<a href="<c:url value="/telaInicial"/>">
	<button type="button" class="btn btn-danger btn-footer">Voltar</button>
</a>
</c:if>
	<button type="button" class="btn btn-success btn-footer" data-toggle="modal" data-target="#novo">Novo Funcion�rio</button>
</div>

<%@ include file="formularioFuncionario.jsp"%>
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