<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/WEB-INF/vistas/template_superiorGenero.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#form-generos').validate();
		});
	</script>

<h1>Formulario de Generos</h1>

<form:form method="post" action="/generos/guardar" modelAttribute="generoForm" id="form-generos">

	<div class="form-group">
			<label>Id Genero</label>
			<form:input path="idGenero" readonly="true" cssClass="form-control"/>
		</div>

		<div class="form-group">
			<label>Imagen</label>
			<form:input path="imagen" cssClass="form-control" />
			<form:errors path="imagen" cssClass="error"/>
		</div>
		
		<div class="form-group">
			<label>Nombre</label>
			<form:input path="nombre" cssClass="form-control required"/>
			<form:errors path="nombre" cssClass="error"/>
		</div>
		
		<button type="submit" class="btn btn-primary">Enviar datos</button>
	
</form:form>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>