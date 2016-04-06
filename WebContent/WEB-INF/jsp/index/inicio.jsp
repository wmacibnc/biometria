<div class="container">
	<div class="row">

		<div class="panel panel-default">

			<div class="panel-heading">
			
			 <span class="title-pager">${usuario.perfil.nome}</span>
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
				
				<div class="container" align="center">
				<div id="linha"></div>
					<div class="row">
						
						<c:set var="cont" value="0" />
						<c:forEach var="funcionalidades" items="${funcionalidadeList}">
						
						<c:if test="${total==1}">
						<div>
						<c:if test="${funcionalidades.tipo == 1}">
                                    <a href="<c:url value="/${funcionalidades.url}"/>">
                                        <button class="btn btn-default btn-inicial" style="width: 280px !important;">${funcionalidades.nome}</button>
                                    </a>
                                </c:if>

                                <c:if test="${funcionalidades.tipo == 2}">
                                    <button type="button" class="btn btn-default btn-inicial" style="width: 280px !important;"
                                        data-toggle="modal" data-target="#${funcionalidades.url}">${funcionalidades.nome}
                                    </button>
                                </c:if>
						</div>
						</c:if>
						
						<c:if test="${total>1}">
							<div class="form-group col-sm-6">
								<c:if test="${funcionalidades.tipo == 1}">
									<a href="<c:url value="/${funcionalidades.url}"/>">
										<button class="btn btn-default btn-inicial">${funcionalidades.nome}</button>
									</a>
								</c:if>

								<c:if test="${funcionalidades.tipo == 2}">
									<button type="button" class="btn btn-default btn-inicial"
										data-toggle="modal" data-target="#${funcionalidades.url}">${funcionalidades.nome}
									</button>
								</c:if>
								
								<div id="linha2"></div>
								<c:if test="${cont == 6}">
									<c:set var="cont" value="0" />
								</div>
								<div class="form-group">
								</c:if>
							</div>
							</c:if>
						</c:forEach>
						</div>
					</div>
				</div>

			</div>

<br /><br /><br /><br />			
<%@ include file="../pessoaMedicamento/listaMedicos.jsp"%>
<%@ include file="../tratamento/listaMedicos.jsp"%>
<%@ include file="../operacaoEstoque/listaMedicos.jsp"%>
<%@ include file="../itemEntrega/identifica.jsp"%>
<%@ include file="../itemAplicacao/identificaUsuarioAplicacao.jsp"%>