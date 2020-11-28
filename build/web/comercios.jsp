<%@page import="model.Rubro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="./css/dashboard.css" rel="stylesheet">
        <title>Comercios</title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <jsp:useBean class="gestor.GestorDB" id="gestor"/>
        <main role="main">
            <div class="container">
                <div class="row">
                    <div class="col  mt-2">
                        <c:if test="${lista.size() > 0}">
                            <h5>Comercios en el rubro 
                                <% Rubro r = (Rubro) request.getAttribute("rubro");
                                    out.println(r.getNombre());
                                %>
                            </h5>
                        </c:if>
                        <c:if test="${lista.size() <= 0}">
                            <div class="alert alert-warning" role="alert">
                                <h5>No hay comercios cargados en el rubro 
                                    <% Rubro ru = (Rubro) request.getAttribute("rubro");
                                        out.println(ru.getNombre());
                                    %>
                                </h5> 
                            </div>
                            <p><a href="/lciv---parcial-2-PontVergesJuanPedro/login.jsp" style="text-decoration: none; color:"#007bff";>Inicia sesión</a> o registrate como usuario para cargar comercios</p>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="container mt-4 mb-3">
                <div class="row">
                    <c:forEach items="${lista}" var="comercio">
                        <div class="col-4 mt-4">
                            <div class="card border-primary" style="width: 18rem;">
                                <div class="card-header text-center">
                                    <img style="width:25%;" class="card-img-top" src="./img/comercio.png" alt="Card image cap">
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">${comercio.nombre}</h5>
                                    <div style="padding:5px;">
                                        <h6>Puntaje promedio</h6>
                                        <c:choose>
                                            <c:when test="${gestor.getPromedioValoracionPorComercio(comercio.id) > 4.5}"> <span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span> </c:when>
                                            <c:when test="${gestor.getPromedioValoracionPorComercio(comercio.id) > 3.5 && gestor.getPromedioValoracionPorComercio(comercio.id) <= 4.5}"> <span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span> </c:when>
                                            <c:when test="${gestor.getPromedioValoracionPorComercio(comercio.id) > 2.5 && gestor.getPromedioValoracionPorComercio(comercio.id) <= 3.5}"> <span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span></c:when>
                                            <c:when test="${gestor.getPromedioValoracionPorComercio(comercio.id) > 1.5 && gestor.getPromedioValoracionPorComercio(comercio.id) <= 2.5}"> <span data-feather="star" fill="yellow" class="estrella"></span><span data-feather="star" fill="yellow" class="estrella"></span></c:when>
                                            <c:when test="${gestor.getPromedioValoracionPorComercio(comercio.id) > 0 && gestor.getPromedioValoracionPorComercio(comercio.id) <= 1.5}"> <span data-feather="star" fill="yellow" class="estrella"></span></c:when>
                                            <c:otherwise><i style="margin:auto; font-size: 12px;">El comercio no ha recibido calificaciones</i></c:otherwise>
                                        </c:choose>

                                    </div> 
                                    <p class="card-text">${comercio.descripcion}</p>
                                    <a href="/lciv---parcial-2-PontVergesJuanPedro/ComercioIndividualServlet?id=${comercio.id}" class="btn btn-primary">Ver ofertas</a>
                                    <p class="mt-2">Comentarios: ${gestor.getComentariosPorComercio(comercio.id).size()}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
        feather.replace()
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</html>
