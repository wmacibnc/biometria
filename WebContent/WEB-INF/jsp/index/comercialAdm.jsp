
<div class="container">
	<div class="row">

		<div class="panel panel-default">

			<div class="panel-heading">
			
			 <span class="title-pager">Comercial</span>
            	<%@ include file="../headerUsuario.jsp"%>
            </div>
		</div>
			

				<div class="container" align="center">
					<div id="linha"></div>
					<div class="row">
						<div class="form-group col-sm-6">

							
							<div class="form-group">
								<a href="<c:url value="/listaMedicamento"/>">
									<button class="btn btn-default btn-inicial">Medicamento</button>
								</a>
								<div id="linha2"></div>
							</div>
						</div>

						<div class="col-sm-6">
							<div class="form-group">
								<a href="<c:url value="/listaMedico"/>">
									<button class="btn btn-default btn-inicial">Médico</button>
								</a>
								<div id="linha2"></div>
							</div>
						
						<div class="form-group">
								<a href="<c:url value="/listaFuncionario"/>">
									<button class="btn btn-default btn-inicial">Funcionário</button>
								</a>
								<div id="linha2"></div>
							</div>
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
