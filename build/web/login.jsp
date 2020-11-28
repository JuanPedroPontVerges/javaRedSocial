<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="./css/dashboard.css" rel="stylesheet">
        <title>Login</title>
        <style>
            #formRegister {
                display: none;
            }
        </style>
    </head>
    <body>
        <%
            if (request.getSession().getAttribute("usuario") != null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/indexAdmin.jsp");
                rd.forward(request, response);
                return;
            }
        %>
        <%@include file="nav.jsp" %>
        <main role="main">
            <div class="container">
                <div class="row justify-content-center mt-4">
                    <div class="col-6">
                        <form id="formLogin" method="POST" action="LoginServlet">
                            <h2 class="text-center">Iniciar Sesión</h2>
                            <div class="form-group">
                                <label for="usuario">Usuario</label>
                                <input type="text" class="form-control" id="usuario" name="loginUsuario">
                            </div>
                            <div class="form-group">
                                <label for="contrasena">Contraseña</label>
                                <input type="password" class="form-control" id="contrasena" name="loginContrasena">
                            </div>
                            <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
                            <p class="mt-2">No te registraste todavia? Hace click <a onclick="toogleRegister()" href="#">aca</a></p>
                            <c:if test="${not empty error}">
                                <div class="alert alert-warning mt-4" role="alert">
                                    <p>Error: ${error}</p> 
                                </div>
                            </c:if>
                        </form>
                        <form id="formRegister" method="POST" action="RegisterServlet">
                            <h2 class="text-center">Registrarse</h2>
                            <div class="form-group">
                                <label for="usuario">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="registerNombre">
                            </div>
                            <div class="form-group">
                                <label for="usuario">Usuario</label>
                                <input type="text" class="form-control" id="registerUsuario" name="registerUsuario">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Contraseña</label>
                                <input type="password" class="form-control" id="registerContrasena" name="registerContrasena">
                            </div>
                            <button type="submit" class="btn btn-primary" onclick="return validarRegister()"> Registrarse</button>
                            <a class="btn btn-danger" style="cursor:pointer;" onclick="toogleLogin()">Cancelar</a>
                            <c:if test="${not empty errorRegister}">
                                <div class="alert alert-warning mt-4" role="alert">
                                    <p>Error: ${errorRegister}</p> 
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </main>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <script>
                                let formLogin = document.getElementById("formLogin")
                                let formRegister = document.getElementById("formRegister")
                                function toogleRegister() {
                                    formLogin.style.display = "none"
                                    formRegister.style.display = "block"
                                }
                                function toogleLogin() {
                                    formLogin.style.display = "block"
                                    formRegister.style.display = "none"
                                }
                                function validarRegister(e) {
                                    const values = formRegister.elements.values();
                                    console.log(values)
                                }
    </script>
</html>
