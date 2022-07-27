<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/WEB-INF/vistas/template_superiorPelicula.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$('.btn-borrar').on('click', function(event) {
				event.preventDefault();
				var hrefOriginal = $(this).attr('href');
				bootbox.confirm("Borramos la pelicula?", function(result){ 
					if(result) {
						window.location = hrefOriginal;
					}
				});
				
			});
		});	
</script>

<h1>Lista de Peliculas</h1>

<table class="table table-striped table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>ID Pelicula</th>
			<th>Imagen</th>
			<th>Titulo</th>
			<th>Fecha de Creacion</th>
			<th>Calificacion</th>
			<th>Nombre del Genero</th>
			<th>Acciones que se pueden realizar</th>
		</tr>
	</thead>
	<c:forEach items="${peliculas}" var="p">
		<tr class = "themed-grid-col">
			<td>${p.idPelicula}</td>
			<td>${p.imagen}</td>
			<td>${p.titulo}</td>
			<td>${p.fechaDeCreacion}</td>
			<td>${p.calificacion}</td>
			<td>${p.genero.nombre}</td>
			<td>
				<a href="/peliculas/${p.idPelicula}" class="btn btn-primary">Ver</a>&nbsp;
				<a href="/peliculas/${p.idPelicula}/editar" class="btn btn-success">Editar</a>&nbsp;
				<a href="/peliculas/${p.idPelicula}/borrar" class="btn btn-danger btn-borrar">Borrar</a>	
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>