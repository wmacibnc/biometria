<div class="container">
	<div class="row">
		<div class="panel panel-default">
			<div class="panel-heading">Comercial - Tela Inicial</div>
			<div class="panel-body">
			<c:if test="${mensagem != null}">
					<div class="alert alert-success">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${mensagem}
					</div>
				</c:if>
				
				<div class="container">
					<div class="row">
						<div class="form-group col-sm-6">
							<div class="form-group">
								<a href="<c:url value="/listaPaciente/1"/>">
									<button class="btn btn-primary btn-inicial">Pacientes</button>
								</a>
							</div>

							<div class="form-group">
								<a href="<c:url value="/comercialAdm"/>">
									<button class="btn btn-danger btn-inicial">Administrar</button>
								</a>
							</div>

							<div class="form-group">
								<button type="button" class="btn btn-success btn-inicial" data-toggle="modal" 
								data-target="#admMedico">Administrar Médico</button>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<button type="button" class="btn btn-primary btn-inicial" data-toggle="modal" 
								data-target="#novoTratamento">Novo Tratamento</button>
							</div>
							<div class="form-group">
								<button type="button" class="btn btn-danger btn-inicial" data-toggle="modal" 
								data-target="#admEstoqueMedico">Gerenciar Estoque</button>
							</div>
							<div class="form-group">
								<a href="#">
									<button class="btn  btn-success btn-inicial">Gerar
										relatório</button>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel-footer"></div>

		</div>
		

	</div>
</div>
</div>
<div id="footer">
			<a href="<c:url value="/"/>">
				<button type="button" class="btn btn-default">Tela Inicial</button>
			</a>
</div>

<%@ include file="../pessoaMedicamento/listaMedicos.jsp"%>
<%@ include file="../tratamento/listaMedicos.jsp"%>
<%@ include file="../operacaoEstoque/listaMedicos.jsp"%>