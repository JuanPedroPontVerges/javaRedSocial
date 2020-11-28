package servlets;

import gestor.GestorDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comentario;
import model.Comercio;
import model.Oferta;
import model.Rubro;
import model.Usuario;

/**
 *
 * @author jppon
 */
@WebServlet(name = "ComercioIndividualServlet", urlPatterns = {"/ComercioIndividualServlet"})
public class ComercioIndividualServlet extends HttpServlet {

    GestorDB g;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComercioIndividualServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComercioIndividualServlet at " + request.getContextPath() + "</h1>");
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

        String id_comercio = (String) request.getParameter("id");
        int id = Integer.parseInt(id_comercio);
        g = new GestorDB();

        Comercio comercio = g.obtenerComercioPorId(id);
        ArrayList<Comentario> comentarios = g.getComentariosPorComercio(id);
        ArrayList<Oferta> ofertas = g.getOfertasPorComercio(id);
        request.setAttribute("comercio", comercio);
        request.setAttribute("comentarios", comentarios);
        request.setAttribute("ofertas", ofertas);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/comercio.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        g = new GestorDB();
        String mensaje = "";
        int idComercio = Integer.parseInt(request.getParameter("comercio"));
        String txtUsuario = String.valueOf(request.getParameter("user"));
        Comercio comercio = g.obtenerComercioPorId(idComercio);
        request.setAttribute("comercio", comercio.getId());
        String txtComentario = request.getParameter("comentario");
        if (!txtUsuario.equals("") && !txtComentario.equals("")) {
            Usuario usuario = g.obtenerUsuarioPorNombre(txtUsuario);
            Comentario comentario = new Comentario(usuario, txtComentario, comercio);
            request.setAttribute("mensajeExito", "Mensaje ingresado con exito");
            request.getSession().setAttribute("comentario", comentario);
            request.getSession().setAttribute("userComentario", usuario);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/confirmacionComentario.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/confirmacionComentario.jsp");
            if (txtUsuario.equals("")) {
                mensaje = "Porfavor,<a href=" + "login.jsp" + ">inicie sesión</a> antes de hacer un comentario";
            } else {
                mensaje = "Error al agregar comentario, campo vacío";

            }
            request.setAttribute("mensajeError", mensaje);
            rd.forward(request, response);
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
