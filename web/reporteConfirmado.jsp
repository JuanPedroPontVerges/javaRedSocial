<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">

        <title>Reportes</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/dashboard/">

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="./css/dashboard.css" rel="stylesheet">
    </head>

    <body>
        <%

            if (request.getSession().getAttribute("usuario") == null) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                return;
            }
        %>
        <%@include file="nav.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <%@include file="sidenav.jsp" %>
                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <div class="col-12 mt-2">
                        <h4>
                            <c:choose>
                                <c:when test="${not empty comentariosxcomercio}">
                                    - Listado de comercios ordenado por cantidad de comentarios.
                                </c:when>
                                <c:when test="${not empty comentariosNoRespondidos}">
                                    - Listado de todos los comentarios no respondidos
                                </c:when>
                                <c:when test="${not empty valoracionxcomercio}">
                                    - Promedio de valoración de cada comercio
                                </c:when>
                                <c:when test="${not empty cantidadValoracionesPorPuntaje}">
                                    - Cantidad total de valoraciones por cantidad de estrellas
                                </c:when>
                            </c:choose>
                        </h4>
                    </div>
                    <c:choose>
                        <c:when test="${not empty comentariosxcomercio}">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Descripción</th>
                                        <th scope="col">Cantidad de Comentarios</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${comentariosxcomercio}" var="c">
                                        <tr>
                                            <td>${c.nombre}</td>
                                            <td>${c.descripcion}</td>
                                            <td>${c.cantidadComentarios}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:when test="${not empty comentariosNoRespondidos}">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Usuario</th>
                                        <th scope="col">Comentario</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${comentariosNoRespondidos}" var="c">
                                        <tr>
                                            <td>${c.id}</td>
                                            <td>${c.usuario.usuario}</td>
                                            <td>${c.comentario}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:when test="${not empty valoracionxcomercio}">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Descripción</th>
                                        <th scope="col">Promedio de valoración</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${valoracionxcomercio}" var="c">
                                        <tr>
                                            <td>${c.comercio.nombre}</td>
                                            <td>${c.comercio.descripcion}</td>
                                            <td>${c.promedio}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:when test="${not empty cantidadValoracionesPorPuntaje}">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">Puntaje</th>
                                        <th scope="col">Cantidad</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${cantidadValoracionesPorPuntaje}" var="c">
                                        <tr>
                                            <td>${c.puntaje} <span data-feather="star" fill="yellow" class="estrella"></span></td>
                                            <td>${c.cantidad}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                    </c:choose>

                </main>
            </div>
        </div>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

        <!-- Icons -->
        <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
        <script>
            feather.replace()
        </script>

        <!-- Graphs -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
        <script>

        </script>
    </body>
</html>