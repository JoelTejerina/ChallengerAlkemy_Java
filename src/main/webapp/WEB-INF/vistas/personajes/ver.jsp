<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:include page="/WEB-INF/vistas/template_superiorPersonaje.jsp"></jsp:include>

<div class="card">
  <h5 class="card-header">Personaje id: ${personajes.idPersonaje}</h5>
  <div class="card-body">
    <h5 class="card-title">Nombre: ${personajes.nombre}</h5>
    <p class="card-text">Imagen: </p>    
    <img src="/personajes/recuperar-imagen/${personajes.idPersonaje}">
    <p class="card-text">Edad: <fmt:formatNumber type="number" value="${personajes.edad}" /> </p>    
    <p class="card-text">Peso: <fmt:formatNumber type="number" value="${personajes.peso}" /> </p>      
    <c:if test="${empty personajes.pelicula}">
	    <p class="card-text">Pelicula: <c:out value="${personajes.pelicula.titulo}" default="Sin pelicula" /></p>
 	</c:if>
 	<c:if test="${not empty personajes.pelicula}">
	    <p class="card-text">Pelicula: <c:out value="${personajes.pelicula.titulo}"/></p>
 	</c:if>
  </div>
</div>

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>