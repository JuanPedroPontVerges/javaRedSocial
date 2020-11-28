<%

    if (request.getSession().getAttribute("usuario") == null) {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
        return;
    }
%>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="indexAdmin.jsp">
                    <span data-feather="home"></span>
                    Inicio <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="abmRubros.jsp">
                    <span data-feather="file"></span>
                    Rubros
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="abmComercios.jsp">
                    <span data-feather="shopping-cart"></span>
                    Comercios
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="abmOfertas.jsp">
                    <span data-feather="dollar-sign"></span>
                    Ofertas
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="consultas.jsp">
                    <span data-feather="bell"></span>
                    Consultas
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="reportes.jsp">
                    <span data-feather="bar-chart-2"></span>
                    Reportes
                </a>
            </li>
        </ul>
    </div>
</nav>