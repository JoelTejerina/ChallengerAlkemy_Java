<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Personaje</title>

	<script type="text/javascript" src="<c:url value="/js/jquery-3.6.0.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/bootstrap.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/bootbox.all.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.validate.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/messages_es_AR.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/select2.min.js"/>"></script>
	
	<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" >
	<link href="<c:url value="/css/select2.min.css"/>" rel="stylesheet" >
	<link href="<c:url value="/css/general.css"/>" rel="stylesheet" >
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#select-autocomplete-personajes').select2({ 
				width: '300px',
				placeholder: 'Buscador',
				minimumInputLength: 2,
				ajax: {
				    url: '/api/personajes/buscar',
				    data: function (params) {
				        var query = {
				          nombre: params.term
				        };
				        return query;
				    },
				    processResults: function (data) {
				        var nuevosDatos = [];
				    	
				        for(var personaje of data) {
				        	nuevosDatos.push({ id: personaje.idPersonaje, text: personaje.nombre + ' ' + personaje.edad});
				        }
				        
				    	return {
				          results: nuevosDatos
				        };
				    },
				    dataType: 'json'
				}
			}).on('select2:select', function(event) {
				window.location = '/personajes/' + event.params.data.id;
			});
		});
		
	</script>
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<header>
			<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		    <div class="container-fluid">
		     <a class="navbar-brand" href="<c:url value="/personajes"/>">Personajes</a>
		      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
		        <span class="navbar-toggler-icon"></span>
		      </button>
		      <div class="collapse navbar-collapse" id="navbarCollapse">
		        <ul class="navbar-nav me-auto mb-2 mb-md-0">
		          <li class="nav-item">
		            <a class="nav-link" href="<c:url value="/personajes/nuevo"/>">Nuevo personaje</a>
		          </li>
		          
		          <li class="nav-item">
		            <a class="nav-link" href="<c:url value="/logout"/>">Cerrar session</a>
        	  	  </li>
		        </ul>
		         <form class="form-inline mt-2 mt-md-0">
			      	<select id="select-autocomplete-personajes"></select>
			      </form>
		      </div>
		    </div>
		  </nav>
		</header>
	</sec:authorize>

	<div class="container">
	<!-- INICIO CONTENIDO --> 