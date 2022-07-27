<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/WEB-INF/vistas/template_superiorPelicula.jsp"></jsp:include>

<div class="card">
  <h5 class="card-header">Pelicula id: ${peliculas.idPelicula}</h5>
  <div class="card-body">
    <h5 class="card-title">${peliculas.titulo}</h5>
    <p class="card-text">Imagen: ${peliculas.imagen} </p>    
    <p class="card-text">Fecha de Creacion: ${peliculas.fechaDeCreacion} </p>    
    <p class="card-text">Calificacion: <fmt:formatNumber type="number" value="${peliculas.calificacion}" /> </p>        
    <c:if test="${empty peliculas.genero}">
	    <p class="card-text">Genero: <c:out value="${peliculas.genero.nombre}" default="Sin genero" /></p>
 	</c:if>
 	<c:if test="${not empty peliculas.genero}">
	    <p class="card-text">Genero: <c:out value="${peliculas.genero.nombre}"/></p>
 	</c:if>
  </div>
</div>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>