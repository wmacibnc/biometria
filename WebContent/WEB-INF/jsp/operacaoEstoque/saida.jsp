<form name="pessoaMedicamentoForm" class="form-inline" role="form" id="pessoaMedicamentoForm" action="<c:url value="/operacaoEstoqueSaida"/>" method="POST">
<input type="hidden" name="bolAplicacao" value="false" />
	<div class="container">
		<div class="row">

	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="title-pager">Efetuar Saida Justificada - Médico: ${medico.nome}</span>
            	<%@ include file="../headerUsuario.jsp"%>
        </div>
	</div>	
							<table class="table">
								<thead>
									<tr>
										<th class="sortable">Medicamento</th>
										<th class="sortable">Justificativa</th>
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
											style="width: 100%;">
												<option value="">Selecione o medicamento</option>
													<c:forEach items="${medicamentoList}" var="medicamento">
														<option value="${medicamento.id}">${medicamento.nome}</option>
													</c:forEach>
											</select>
										</td>
										
										<td width="40%">
											<input
												id="justificativa" name="pessoaMedicamento[${cont.index}].justificativa"
												class="form-control col-md-12" style="width: 100%;"
												aria-describedby="basic-addon1" />
										</td>						
										
										<td width="10%">
											<input
												id="quantidadeEntrada" name="pessoaMedicamento[${cont.index}].quantidade"
												type="number" min="0" style="width: 100%;"
												class="form-control col-md-12"
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
			
		<button class="btn btn-primary btn-footer" type="submit" name="_method" value="PUT" onclick="return confirm('Confirma a saída justificada do medicamento?');">Salvar</button>
			
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