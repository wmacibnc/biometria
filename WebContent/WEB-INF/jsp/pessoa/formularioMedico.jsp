<!-- Modal -->
<div id="novo" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
<div class="modal-content">
	<div class="modal-body">
		<div class="panel panel-default">
			<div class="panel-heading">Novo Médico</div>
			<div class="panel-body">


				<form class="form-horizontal" id="pessoaForm" action="<c:url value="/medicos"/>" method="POST">
				
					<!-- Select Basic -->
					<div class="form-group">
						<input type="hidden" name="pessoa.tipoPessoa.id" value="4" /> 
					</div>

					<%@ include file="camposPessoa.jsp"%>            


<table style="width:100%;text-align:center;">
<tr>
<td>

<span class="btn-espaco">
	<button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
</span>

<span class="btn-espaco"> 
	<button class="btn btn-default" type="reset">Limpar</button>
</span>

<span class="btn-espaco"> 
	<button class="btn btn-success" type="submit">Salvar</button>
</span>
</td>
</tr>
</table>
<div id="linha3"></div>
            
            
            </form>
            </div>
                </div>
            </div>
        </div>

    </div>
</div><!-- Modal -->