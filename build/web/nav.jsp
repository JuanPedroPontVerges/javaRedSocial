<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow ">
            <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/lciv---parcial-2-PontVergesJuanPedro/index.jsp">Red Vecinal</a>
            <form class="form-inline my-2 my-lg-0" method="POST" action="BusquedaOfertaServlet">
                <input class="form-control mr-sm-2" type="search" placeholder="Buscar oferta" aria-label="Buscar oferta" name="busqueda">
                <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Buscar</button>
            </form>
            <ul class="navbar-nav px-3">
                <div class="my-2 my-lg-0">
                    <c:if test="${sessionScope.usuario == null}">
                        <a href="/lciv---parcial-2-PontVergesJuanPedro/login.jsp">Iniciar Sesion</a>
                    </c:if>
                    <c:if test="${sessionScope.usuario != null}">
                        <a href="/lciv---parcial-2-PontVergesJuanPedro/indexAdmin.jsp">Home Admin</a>
                        <span style="color:white;">|</span>
                        <a href="/lciv---parcial-2-PontVergesJuanPedro/LogoutServlet">Cerrar Sesion</a>
                    </c:if>
                </div>
            </ul>

        </nav>
    </div>
</div>