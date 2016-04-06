<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<c:if test="${!empty usuarioLogado && !empty usuarioLogado.usuario}">

	<div class="btn-group btn-header-usuario" style="float: right; font-size: 80%; position: relative; top: -6px;">
	
		<button 
			class="btn" style="background: transparent none repeat scroll 0% 0%; margin-right: -10px; border:none;" 
			data-toggle="dropdown">
				${usuarioLogado.usuario.pessoa.nome}&nbsp; 
					<span class="caret">
					</span>
		</button>
		
		<ul class="dropdown-menu user">
			<li>
				<a href="<c:url value="/telaInicial"/>">
					${usuarioLogado.usuario.perfil.nome}
				</a>
			</li>
			
			<!--<li><a href="#" onclick="requestFullScreen()">Tela cheia</a></li> -->
			
			<li 
				class="divider">
			</li>
			
			<li>
				<a href="<c:url value="/logout"/>"> 
					Sair
				</a>
			</li>
			
		</ul>
	</div>
</c:if>