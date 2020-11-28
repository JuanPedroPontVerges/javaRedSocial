<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">

        <title>Comentarios</title>

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
        <jsp:useBean class="gestor.GestorDB" id="g"/>
        <div class="container-fluid">
            <div class="row">
                <%@include file="sidenav.jsp" %>
                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <h2 style="margin:20px;">Comentarios</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col" style="border-right:1px solid grey;">#</th>
                                <th scope="col">ID</th>
                                <th scope="col">Usuario</th>
                                <th scope="col">Comentario</th>
                                <th scope="col">Comercio</th>
                                <th scope="col">Eliminar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${g.comentarios}" var="comentario" varStatus="loop">
                                <tr>
                                    <th scope="row" style="border-right:1px solid grey;">${loop.index + 1}</th>
                                    <th scope="row">${comentario.id}</th>
                                    <td>${comentario.usuario.usuario}</td>
                                    <td>${comentario.comentario}</td>
                                    <td>${comentario.comercio.nombre}</td>
                                    <td>
                                        <a href="/lciv---parcial-2-PontVergesJuanPedro/EliminarComentarioServlet?id=${comentario.id}"class="btn btn-danger btn-sm">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
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
            let button = document.getElementById("boton");
            button.addEventListener("click", () => {
                alert("Esta seguro que desea eliminar el comentario?")
            })
        </script>
    </body>
</html>