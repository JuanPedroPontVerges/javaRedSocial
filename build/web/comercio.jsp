<%@page import="model.Comercio"%>
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
        <title>
            Comercio 
            <% Comercio com = (Comercio) request.getAttribute("comercio");
                out.println(com.getNombre());
            %>
        </title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <main role="main">
            <div class="container">
                <div class="row">
                    <div class="col mt-4">
                        <h4>
                            <% Comercio c = (Comercio) request.getAttribute("comercio");
                                out.println(c.getNombre());
                            %>
                        </h4>
                    </div>
                </div>
                <div class="row mt-4"">
                    <h6>Ofertas</h6>
                </div>
                <div class="row">
                    <c:if test="${ofertas.size() <= 0}">
                        <div class="col">
                            <div class="alert alert-warning" role="alert">
                                <h5>No hay ofertas cargadas</h5> 
                            </div>
                            <p><a href="/lciv---parcial-2-PontVergesJuanPedro/login.jsp" style="text-decoration: none; color:"#007bff";>Inicia sesión</a> o regsitrate como usuario para cargar ofertas</p>
                        </div>
                    </c:if>
                    <c:forEach items="${ofertas}" var="oferta" varStatus="loop">
                        <div class="col-4">
                            <div class="card text-white bg-info mb-3" style="max-width: 18rem;">
                                <div class="card-header">N° ${loop.index + 1}</div>
                                <div class="card-body">
                                    <h5 class="card-title">${oferta.titulo}</h5>
                                    <p class="card-text">${oferta.descripcion}</p>
                                    <span>$${oferta.precio}</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="row align-items-center mb-3">
                    <div class="col-2">
                        <h4>Comentarios </h4>
                    </div>
                    <div class="col">
                        <a type="button" class="btn btn-outline-primary btn-sm" href="#agregarComentario">Agregar comentario</a>
                    </div>
                </div>
                <div class="row">
                    <c:forEach var = "comentario" items="${comentarios}" varStatus="loop">
                        <div class="col-12">
                            <div class="alert alert-primary" role="alert">
                                ${loop.index + 1} - ${comentario.usuario.usuario}: ${comentario.comentario}
                                <c:if test="${comentario.respuesta.id == null}">
                                    <form action="RespuestasServlet" method="POST">
                                        <div class="form-row">
                                            <div class="col-10">
                                                <input type="hidden" name="idComentario" value="${comentario.id}"/>
                                                <input type="hidden" name="idComercio" value="${comentario.comercio.id}"/>
                                                <input class="form-control mt-2 form-control-sm" type="text" name="respuesta" placeholder="Responder comentario"/>
                                            </div>
                                            <div class="col mt-2">
                                                <button type="submit" class="btn btn-primary btn-sm">Responder</button>
                                            </div>
                                        </div>
                                    </form>
                                </c:if>
                                <c:if test="${comentario.respuesta.id >= 1}">
                                    <div class="alert alert-light" role="alert">
                                        Respuesta: ${comentario.respuesta.respuesta}
                                    </div>
                                </c:if>
                            </div>

                        </div>
                    </c:forEach>
                </div>
                <div class="row" id="agregarComentario">
                    <div class="col-12">
                        <h4>Agregar un nuevo comentario</h4>
                    </div>
                    <div class="col-12">
                        <form method="POST" action="ComercioIndividualServlet">
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">Postear comentario</label>
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="4" name="comentario"></textarea>
                                <% Comercio comercio = (Comercio) request.getAttribute("comercio");
                                    out.print("<input type='hidden' name='comercio' value=" + comercio.getId() + " />");
                                %>
                                <input type="hidden" name="user" value="${sessionScope.usuario}" />
                                <button type="submit" class="btn btn-primary mt-4" href="#agregarComentario">Enviar</button>
                            </div>
                        </form>
                        <c:if test="${not empty mensaje}">
                            <p>${mensaje}</p>
                        </c:if>
                    </div>
                </div>
        </main>
    </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</html>
