<div class="container">
	<div class="row">

				<div class="panel panel-default">

			<div class="panel-heading">
			
			 <span class="title-pager">Gerenciar Estoque</span>
            	<%@ include file="../headerUsuario.jsp"%>
            </div>
		</div>
				<div class="container" align="center">
				<div id="linha"></div>
					<div class="row">
					
						<div class="form-group col-sm-6">
							<a href="<c:url value="/operacaoEstoqueE"/>">
							<span class="btn btn-default btn-inicial"><br />Efetuar Entrada</span>
							</a>
							<div id="linha2"></div>
						</div>
						
						<div class="form-group col-sm-6">
							<a href="<c:url value="/operacaoEstoqueS"/>">
							<span class="btn btn-default btn-inicial"><p> </p>Efetuar Saida<p>Justificativa</p></span>
							</a>
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