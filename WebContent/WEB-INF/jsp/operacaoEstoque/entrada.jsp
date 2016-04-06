<form name="pessoaMedicamentoForm" class="form-inline" role="form" id="pessoaMedicamentoForm" action="<c:url value="/operacaoEstoqueEntrada"/>" method="POST">

	<div class="container">
		<div class="row">

	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="title-pager">Efetuar Entrada de Médicamento - Médico: ${medico.nome}</span>
            	<%@ include file="../headerUsuario.jsp"%>
        </div>
	</div>	
							<table class="table">
								<thead>
									<tr>
										<th class="sortable">Medicamento</th>
										<th class="sortable">Data de Validade</th>
										<th class="sortable">Nota Fiscal</th>
										<th class="sortable">Quantidade</th>
									</tr>
								</thead>
								
								<tbody>
								<c:forEach begin="1" end="5" varStatus="cont">
								<input type="hidden" name="pessoaMedicamento[${cont.index}].pessoa.id" value="${medico.id}" />
								
									<tr class="odd">
										<td width="50%">
										
										<select
												name="pessoaMedicamento[${cont.index}].medicamento.id"
												class="form-control"
												aria-describedby="basic-addon1"
												style="width: 93%;">
												<option value="">Selecione o medicamento</option>
													<c:forEach items="${medicamentoList}" var="medicamento">
														<option value="${medicamento.id}">${medicamento.nome}</option>
													</c:forEach>
											</select>
										</td>
										
										<td width="15%">
											<input
												id="calendario${cont.index}"
												name="pessoaMedicamento[${cont.index}].dataVencimento"
												class="form-control" style="width: 70%;"
												aria-describedby="basic-addon1" />
										</td>
										
										<td width="20%">
											<input
												id="notaFiscal"
												name="pessoaMedicamento[${cont.index}].notaFiscal"
												class="form-control col-md-12"
												aria-describedby="basic-addon1" />
										</td>
										
										<td width="15%">
											<input id="quantidadeEntrada"
												name="pessoaMedicamento[${cont.index}].quantidade"
												type="number" min="0" class="form-control" style="width: 48%;"
												aria-describedby="basic-addon1" />
										</td>
									</tr>
								</c:forEach>
								</tbody>
								
							</table>
	</div>
	</div>
	<div id="footer">
			<a href="<c:url value="/escolheTipoOperacaoEstoque"/>">
				<button type="button" class="btn btn-danger btn-footer">Voltar</button>
			</a>
			
		<button class="btn btn-primary btn-footer" onclick="return confirm('Confirma a entrada dos medicamentos no estoque?');" type="submit" name="_method" value="PUT">Salvar</button>
			
	</div>
</form>
<br /><br /><br /><br />
<script type="text/javascript">
	jQuery(function($) {
		$("#date1").mask("99/99/9999");
		$("#date2").mask("99/99/9999");
		$("#date3").mask("99/99/9999");
		$("#date4").mask("99/99/9999");
	});
</script>