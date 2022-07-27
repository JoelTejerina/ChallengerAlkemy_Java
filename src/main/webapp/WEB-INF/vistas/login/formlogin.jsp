<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="/WEB-INF/vistas/template_superiorLogin.jsp"></jsp:include>
    
<form class="form-signin" method="post" action="/validarusuario">
    <h1 class="h3 mb-3 fw-normal">Login de usuario</h1>

    <div class="form-floating">
      <input type="text" name="username" class="form-control" id="floatingInput" placeholder="Usuario" required autofocus>
      <label for="floatingInput">Usuario</label>
    </div>
    
    <div class="form-floating">
      <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password" required>
      <label for="floatingPassword">Password</label>
    </div>

    <button class="w-100 btn btn-lg btn-primary" type="submit">Entrar</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2022</p>
  </form>
	

<jsp:include page="/WEB-INF/vistas/template_inferior.jsp"></jsp:include>