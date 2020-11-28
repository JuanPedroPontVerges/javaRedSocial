<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">

        <title>Modificación</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/dashboard/">

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="./css/dashboard.css" rel="stylesheet">
    </head>

    <body>
        <%
            //autenticacion
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
                    <c:if test="${not empty rubro}">
                        <h2 style="margin:20px;">Modificar rubro</h2>
                        <form method="POST" action="ABMRubrosServlet">
                            <div class="form-group">
                                <label for="exampleFormControlInput1">Nombre</label>
                                <input name="nombre" value="${rubro.nombre}" class="form-control">
                            </div>
                            <input type="hidden" name="idRubro" value="${rubro.id}">
                            <div class="form-group"> 
                                <label for="exampleFormControlTextarea1">Descripcion</label>
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="descripcion">${rubro.descripcion}</textarea>
                            </div>
                            <button class="btn btn-danger" type="submit" name="opcion" value="modificar">Modificar rubro</button>
                        </form>
                    </c:if>
                    <c:if test="${not empty comercio}">
                        <h2 style="margin:20px;">Modificar Comercio</h2>
                        <form method="POST" action="ABMComerciosServlet">
                            <div class="form-group">
                                <label for="exampleFormControlInput1">Nombre</label>
                                <input name="nombre" value="${comercio.nombre}" class="form-control">
                            </div>
                            <input type="hidden" name="idComercio" value="${comercio.id}">
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">Descripcion</label>
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="descripcion">${comercio.descripcion}</textarea>
                            </div>
                            <div class="form-group">
                                <select name="id_rubro" class="form-control" >
                                    <c:forEach items="${g.obtenerRubros()}" var="r">
                                        <option
                                            value="${r.id}"
                                            <c:if test="${r.id == comercio.rubro.id}">
                                                selected
                                            </c:if>>
                                            ${r.nombre}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button class="btn btn-danger" type="submit" name="opcion" value="modificar">Modificar comercio</button>
                        </form>
                    </c:if>
                    <c:if test="${not empty oferta}">
                        <h2 style="margin:20px;">Modificar Oferta</h2>
                        <form method="POST" action="ABMOfertasServlet">
                            <div class="form-group">
                                <label for="exampleFormControlInput1">Titulo</label>
                                <input name="titulo" value="${oferta.titulo}" class="form-control">
                            </div>
                            <input type="hidden" name="idOferta" value="${oferta.id_oferta}">
                            <div class="form-group">
                                <label for="exampleFormControlTextarea1">Descripcion</label>
                                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="descripcion">${oferta.descripcion}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="exampleFormControlInput2">Precio</label>
                                <input id="exampleFormControlInput2" name="precio" value="${oferta.precio}" class="form-control">
                            </div>
                            <div class="form-group">
                                <select name="idComercio" class="form-control" >
                                    <c:forEach items="${g.comercios}" var="c">
                                        <option
                                            value="${c.id}"
                                            <c:if test="${c.id == oferta.comercio.id}">
                                                selected
                                            </c:if>>
                                            ${c.nombre}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button class="btn btn-danger" type="submit" name="opcion" value="modificar">Modificar oferta</button>
                        </form>
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