<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/WEB-INF/vistas/template_superiorPersonaje.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#select-peliculas').select2();
			$('#form-personaje').validate();
		});
	</script>

<h1>Formulario de Personajes</h1>

<form:form method="post" action="/personajes/guardar" modelAttribute="personajeForm" id="form-personaje" enctype="multipart/form-data">

	<div class="form-group">
			<label>Id Personaje</label>
			<form:input path="idPersonaje" readonly="true" cssClass="form-control"/>
		</div>


		<div class="form-group">
			<label>Foto personaje</label>
			<input type="file" name="imagen" class="form-control">
		</div>
		
		<div class="form-group">
			<label>Nombre</label>
			<form:input path="nombre" cssClass="form-control required"/>
			<form:errors path="nombre" cssClass="error"/>
		</div>

		<div class="form-group">
			<label>Edad</label>
			<form:input path="edad" cssClass="form-control required"/>
			<form:errors path="edad" cssClass="error"/>
		</div>
		
		<div class="form-group">
			<label>Peso kg</label>
			<form:input path="peso" cssClass="form-control required"/>
			<form:errors path="peso" cssClass="error"/>
		</div>
		
		<div class="form-group">
			<label>Historia</label>
			<form:input path="historia" cssClass="form-control required"/>
			<form:errors path="historia" cssClass="error"/>
		</div>

		<div class="form-group" >
			<label >Pelicula titulo</label>
			<form:select path="peliculaIdPelicula" items="${peliculas}" itemLabel="titulo" itemValue="idPelicula" cssClass="form-control required" id="select-peliculas"/>
		</div>
		
		<button type="submit" class="btn btn-primary">Enviar datos</button>
	
</form:form>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>