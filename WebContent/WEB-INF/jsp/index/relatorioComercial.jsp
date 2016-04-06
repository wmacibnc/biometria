<div class="container">
	<div class="row">

		<div class="panel panel-default">
            <div class="panel-heading">
            <span class="title-pager">Comercial - Relatórios</span>
            	<%@ include file="../headerUsuario.jsp"%>
            </div>
        </div>
        
				<div class="container" align="center">
				<div id="linha"></div>
					<div class="row">
						<div class="form-group col-sm-6">
							<div class="form-group">
							<button type="button" class="btn btn-default btn-inicial" data-toggle="modal" 
								data-target="#filtroEstoque">Estoque Disponível</button>
							</div>
							<div id="linha2"></div>
							<div class="form-group">
								<button type="button" class="btn btn-default btn-inicial" data-toggle="modal" 
								data-target="#filtroEntrada">Entradas no Estoque</button>
							</div>
						<div id="linha2"></div>
						</div>
						<div class="col-sm-6">
						<div class="form-group">
								<button type="button" class="btn btn-default btn-inicial" data-toggle="modal" 
								data-target="#filtroSaida">Saidas do estoque</button>
							</div>
							<div id="linha2"></div>
						</div>
					</div>
				</div>
			</div>
	</div>

<%@ include file="filtroEstoque.jsp"%>
<%@ include file="filtroEntrada.jsp"%>
<%@ include file="filtroSaida.jsp"%>
<div id="footer">
	<a href="<c:url value="/telaInicial"/>">
		<button type="button" class="btn btn-danger btn-footer">Voltar</button>
	</a>
</div>