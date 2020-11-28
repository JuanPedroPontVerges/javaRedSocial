<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">

        <title>Confirmación de comentario</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/dashboard/">

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="./css/dashboard.css" rel="stylesheet">
        <style>
        </style>
    </head>

    <body>
        <%@include file="nav.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                        <h1 class="h2">Estado del mensaje</h1>
                    </div>
                    <c:if test="${not empty mensajeExito}">
                        <p>¡Mensaje grabado exitosamente!</p>
                    </c:if>
                    <c:if test="${not empty mensajeError}">
                        <h4>${mensajeError}</h4>
                    </c:if>
                    <c:if test="${not empty mensajeExito}">

                        <div class="rating" width:100px;">
                            <form action="CalificarComercioServlet" method="POST">
                                <h5><a href="/lciv---parcial-2-PontVergesJuanPedro/ComercioIndividualServlet?id=${comercio}">
                                        <button type="submit" class="btn btn-primary">Volver al comercio</button>
                                    </a>
                                </h5>
                                <input type="hidden" name="idComercio" value="${comercio}"/>
                                <p>¿Desea calificar el comercio?</p> 
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="calificacion" value="1">
                                    <label class="form-check-label" for="exampleRadios1">
                                        1 - <span data-feather="star" fill="yellow" class="estrella"></span>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="calificacion" value="2">
                                    <label class="form-check-label" for="exampleRadios2">
                                        2 - <span data-feather="star" class="estrella" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="calificacion" value="3">
                                    <label class="form-check-label" for="exampleRadios2">
                                        3 - <span data-feather="star" class="estrella" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="calificacion" value="4">
                                    <label class="form-check-label" for="exampleRadios2">
                                        4 - <span data-feather="star" class="estrella" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="calificacion" value="5">
                                    <label class="form-check-label" for="exampleRadios2">
                                        5 - <span data-feather="star" class="estrella" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span><span data-feather="star" fill="yellow" ></span>
                                    </label>
                                </div>
                                <button type="submit" class="btn btn-primary mt-2">Calificar</button>
                            </form>
                        </div>
                    </c:if>
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

    </body>
</html>