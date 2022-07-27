<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/WEB-INF/vistas/template_superiorGenero.jsp"></jsp:include>

<h1>Lista de Generos</h1>

<table class="table table-striped table-bordered">
	<thead class="thead-dark">
		<tr>
			<th>ID Genero</th>
			<th>Imagen</th>
			<th>Nombre</th>
			<th>Acciones que se pueden realizar</th>
		</tr>
	</thead>
	<c:forEach items="${generos}" var="p">
		<tr class = "themed-grid-col">
			<td>${p.idGenero}</td>
			<td>${p.imagen}</td>
			<td>${p.nombre}</td>
			<td>
				<div>
					<a href="/generos/${p.idGenero}" class="btn btn-primary">Ver</a>&nbsp;
					<a href="/generos/${p.idGenero}/editar" class="btn btn-success">Editar</a>&nbsp;	
				</div>
			</td>
		</tr>
	</c:forEach>
</table>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>