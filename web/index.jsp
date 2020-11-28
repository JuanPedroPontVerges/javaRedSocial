<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="./css/dashboard.css" rel="stylesheet">
        <link rel="icon" href="./img/logo.png">
        <title>Inicio</title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <jsp:useBean id="gestor" class="gestor.GestorDB"/>
        <main role="main">
            <div class="container-fluid mt-4">
                <div class="row">
                    <div class="col mt-4">
                        <h2 class="text-center">Bienvenidos a la Red Vecinal</h2>
                    </div>
                </div>
                <c:if test="${gestor.obtenerRubros().size() > 0}">
                    <div class="row">
                        <div class="col mt-4">
                            <h5>Nuestros rubros:</h5>
                        </div>
                    </div>
                </c:if>
            </div>
            <div class="container mt-4 mb-3">
                <div class="row">
                    <c:if test="${gestor.obtenerRubros().size() <= 0}">
                        <div class="col">
                            <div class="alert alert-warning" role="alert">
                                <h5>No hay rubros cargados</h5> 
                            </div>
                            <p><a href="/lciv---parcial-2-PontVergesJuanPedro/login.jsp" style="text-decoration: none; color:"#007bff";>Inicia sesión</a> o regsitrate como usuario para cargar rubros</p>
                        </div>
                    </c:if>
                    <c:forEach items="${gestor.obtenerRubros()}" var="rubro">
                        <div class="col-4 mt-4">
                            <div class="card" style="width: 18rem;">
                                <div class="card-body">
                                    <h5 class="card-title">${rubro.nombre}</h5>
                                    <p class="card-text">${rubro.descripcion}</p>
                                    <a href="/lciv---parcial-2-PontVergesJuanPedro/ComerciosServlet?id=${rubro.id}" class="btn btn-primary">Visitar</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</html>
