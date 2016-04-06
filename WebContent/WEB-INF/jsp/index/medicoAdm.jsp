
<div class="container">
	<div class="row">

		<div class="panel panel-default">
            <div class="panel-heading">
            <span class="title-pager">Médico: ${medico.nome} - Administração</span>
            	<%@ include file="../headerUsuario.jsp"%>
            </div>
        </div>
        
				<div class="container" align="center">
				<div id="linha"></div>
					<div class="row">
						<div class="form-group col-sm-6">

							<div class="form-group">
								<a href="<c:url value="/listaMedico"/>">
									<button class="btn btn-default btn-inicial">Médico</button>
								</a>
							</div>
							<div id="linha2"></div>
							
							<div class="form-group">
								<form id="escolherMedicoForm" action="<c:url value="/definirMedico"/>" method="POST">
									<input type="hidden" name="pessoa.id" value="${medico.id}" />
									<button class="btn btn-default btn-inicial" type="submit">Medicamento</button>
								</form>
							</div>
							<div id="linha2"></div>
							
						</div>

						<div class="col-sm-6">
						
						<div class="form-group">
								<a href="<c:url value="/listaFuncionario"/>">
									<button class="btn btn-default btn-inicial">Funcionário</button>
								</a>
							</div>
							<div id="linha2"></div>
						</div>
					</div>
				</div>
			</div>
	</div>
<div id="footer">
	<a href="<c:url value="/telaInicial"/>">
		<button type="button" class="btn btn-danger btn-footer">Voltar</button>
	</a>
</div>
