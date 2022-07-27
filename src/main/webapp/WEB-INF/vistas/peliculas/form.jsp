<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/WEB-INF/vistas/template_superiorPelicula.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#select-generos').select2();
			$('#form-peliculas').validate();
		});
	</script>

<h1>Formulario de Peliculas</h1>

<form:form method="post" action="/peliculas/guardar" modelAttribute="peliculaForm" id="form-peliculas">

	<div class="form-group">
			<label>Id Pelicula</label>
			<form:input path="idPelicula" readonly="true" cssClass="form-control"/>
		</div>

		<div class="form-group">
			<label>Imagen</label>
			<form:input path="imagen" cssClass="form-control" />
			<form:errors path="imagen" cssClass="error"/>
		</div>
		
		<div class="form-group">
			<label>Titulo</label>
			<form:input path="titulo" cssClass="form-control required"/>
			<form:errors path="titulo" cssClass="error"/>
		</div>

		<div class="form-group">
			<label>Fecha De Creacion</label>
			<input type="date" name="fechaDeCreacion" class="form-control"/>
			<form:errors path="fechaDeCreacion" cssClass="error"/>
			
		</div>
		
		<div class="form-group">
			<label>Calificacion</label>
			<form:input path="calificacion" cssClass="form-control required"/>
			<form:errors path="calificacion" cssClass="error"/>
		</div>

		<div class="form-group" >
			<label >Genero nombre</label>
			<form:select path="generoIdGenero" items="${generos}" itemLabel="nombre" itemValue="idGenero" cssClass="form-control required" id="select-generos"/>
		</div>
		
		<button type="submit" class="btn btn-primary">Enviar datos</button>
	
</form:form>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>