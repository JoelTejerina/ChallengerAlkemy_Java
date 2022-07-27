<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/WEB-INF/vistas/template_superiorPersonaje.jsp"></jsp:include>

	<script type="text/javascript">
		$(document).ready(function() {
			$('.btn-borrar').on('click', function(event) {
				event.preventDefault();
				var hrefOriginal = $(this).attr('href');
				bootbox.confirm("Borramos el producto?", function(result){ 
					if(result) {
						window.location = hrefOriginal;
					}
				});
				
			});
		});	
</script>

<h1>Lista de Personajes</h1>

<table class="table table-striped table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>ID Personaje</th>
			<th>Imagen</th>
			<th>Nombre</th>
			<th>Edad</th>
			<th>Peso kg</th>
			<th>Historia</th>
			<th>Titulo de Pelicula</th>
			<th>Acciones que se pueden realizar</th>
		</tr>
	</thead>
	<c:forEach items="${personajes}" var="p">
		<tr class="themed-grid">
			<td>${p.idPersonaje}</td>
			<td>${p.imagen}</td>
			<td>${p.nombre}</td>
			<td>${p.edad}</td>
			<td>${p.peso}</td>
			<td>${p.historia}</td>
			<td>${p.pelicula.titulo}</td>
			<td>
				<div class = "themed-grid-col">
					<a href="/personajes/${p.idPersonaje}" class="btn btn-primary">Ver</a>&nbsp;
					<a href="/personajes/${p.idPersonaje}/editar" class="btn btn-success">Editar</a>&nbsp;
					<a href="/personajes/${p.idPersonaje}/borrar" class="btn btn-danger btn-borrar">Borrar</a>	
				</div>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>