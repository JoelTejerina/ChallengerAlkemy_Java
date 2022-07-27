<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Inicio</title>
    
	<script type="text/javascript" src="<c:url value="/js/bootstrap.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/messages_es_AR.js"/>"></script>
	
	<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet" >
	<link href="<c:url value="/css/general.css"/>" rel="stylesheet" >
    
  </head>
  <body>  
  <div class="b-example-divider"></div>
 
  <div class="bg-dark text-secondary px-20 py-20 text-center">
    <div class="py-5">
      <h1 class="display-5 fw-bold text-white">Joel Tejerina</h1>
      <div class="col-lg-6 mx-auto">
        <p class="fs-5 mb-4">Hola, este es un proyecto final que realice para un curso, tome como base el challenge de alkemy, por esto mismo es que se veran en el codigo algunas peticiones adicionales, y una pequeña parte de frontend</p>
        <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
          <a type="button" href="/personajes" class="btn btn-outline-info btn-lg px-4 me-sm-3 fw-bold"">Personajes</a>
          <a type="button" href="/peliculas" class="btn btn-outline-info btn-lg px-4 me-sm-3 fw-bold"">Peliculas</a>
          <a type="button" href="/generos" class="btn btn-outline-info btn-lg px-4 me-sm-3 fw-bold"">Generos</a>
          <a type="button" href="https://drive.google.com/file/d/1ICHCzERR_tC9yB9crJyxVoqtNXsduOky/view?usp=sharing" class="btn btn-outline-info btn-lg px-4 me-sm-3 fw-bold"">Challenger</a>
        </div>
      </div>
    </div>
  </div>
  
   <div class="b-example-divider mb-0"></div>
  </body>
 </head>
  
</html>
