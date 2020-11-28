package servlets;

import gestor.GestorDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comercio;
import model.Oferta;

@WebServlet(name = "ABMOfertasServlet", urlPatterns = {"/ABMOfertasServlet"})
public class ABMOfertasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ABMOfertasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ABMOfertasServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GestorDB g = new GestorDB();

        String titulo = "";
        String descripcion = "";
        int precio = 0;
        int id_comercio = 0;
        int idOferta = -1;
        int id = 0;
        String prueba = "";

        titulo = request.getParameter("titulo");
        descripcion = request.getParameter("descripcion");

        if (request.getParameter("precio") != null) {
            precio = Integer.parseInt(request.getParameter("precio"));
        }
        if (!(request.getParameter("idComercio") == null)) {
            id_comercio = Integer.parseInt(request.getParameter("idComercio"));
        }
        if (!(request.getParameter("idOferta") == null)) {
            id = Integer.parseInt(request.getParameter("idOferta"));
        }
        Comercio c = g.obtenerComercioPorId(id_comercio);

        String abmOfertas = request.getParameter("opcion");
        switch (abmOfertas) {
            case "crear":
                if ((c == null) || (titulo.equals("")) || (descripcion.equals("")) || (precio == 0)) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmOfertas.jsp");
                    request.setAttribute("error", "Error al agregar la oferta, porfavor complete todo los campos");
                    rd.forward(request, response);
                } else {
                    if (g.agregarOferta(new Oferta(titulo, descripcion, precio, c))) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmOfertas.jsp");
                        request.setAttribute("mensaje", "Agregado correctamente!");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmOfertas.jsp");
                        request.setAttribute("error", "Error al agregar la oferta, intente de nuevo");
                        rd.forward(request, response);
                    }
                }
                break;

            case "modificar":
                if ((c == null) || (titulo.equals("")) || (descripcion.equals("")) || (precio == 0)) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmOfertas.jsp");
                    request.setAttribute("error", "Error al modificar la oferta, porfavor complete todo los campos");
                    rd.forward(request, response);
                } else {
                    if (g.modificarOferta(new Oferta(id, titulo, descripcion, precio, c))) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmOfertas.jsp");
                        request.setAttribute("mensaje", "Modificado correctamente!");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmOfertas.jsp");
                        request.setAttribute("error", "Error al modificar la oferta, intente de nuevo");
                        rd.forward(request, response);

                    }
                }
                break;

            case "eliminar":
                if (id >= 0) {
                    if (g.eliminarOferta(id)) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmOfertas.jsp");
                        request.setAttribute("mensaje", "Eliminado correctamente!");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmOfertas.jsp");
                        request.setAttribute("error", "Error al eliminar la oferta, intente de nuevo");
                        rd.forward(request, response);
                    }
                }
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
