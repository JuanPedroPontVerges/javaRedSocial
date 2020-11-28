<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="./css/dashboard.css" rel="stylesheet">
        <title>Ofertas</title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <jsp:useBean id="gestor" class="gestor.GestorDB"/>
        <main role="main">
            <div class="container-fluid mt-4">
                <div class="row">
                    <div class="col mt-4">
                        <h2 class="text-center">Ofertas que contengan "${busqueda}"</h2>
                    </div>
                </div>
            </div>
            <div class="container mt-4">
                <div class="row">
                    <c:if test="${ofertas.size() <= 0}">
                        <div class="col">
                            <div class="alert alert-warning" role="alert">
                                <h5>No se han encontrado resultados</h5> 
                            </div>
                            <h4 class="text-center">Volver al <a href="/lciv---parcial-2-PontVergesJuanPedro/index.jsp" style="text-decoration: none; color:"#007bff";>Inicio</a></h4>
                        </div>
                    </c:if>
                    <c:forEach items="${ofertas}" var="oferta">
                        <div class="col-4 mt-4">
                            <div class="card" style="width: 18rem;">
                                <div class="card text-white bg-info">
                                    <div class="card-header">
                                        <h5 class="card-title">${oferta.titulo}</h5>
                                    </div>
                                    <div class="card-body">
                                        <p class="card-text">${oferta.descripcion}</p>
                                        <p class="card-text">$ ${oferta.precio}</p>
                                        <a href="/lciv---parcial-2-PontVergesJuanPedro/ComercioIndividualServlet?id=${oferta.comercio.id}" class="btn btn-light">Visitar Comercio</a>
                                    </div>
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
