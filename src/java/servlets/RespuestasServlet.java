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
import model.Respuesta;
import model.Usuario;

/**
 *
 * @author jppon
 */
@WebServlet(name = "RespuestasServlet", urlPatterns = {"/RespuestasServlet"})
public class RespuestasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RespuestasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RespuestasServlet at " + request.getContextPath() + "</h1>");
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
        int idComentario = Integer.parseInt(request.getParameter("idComentario"));
        String respuesta = request.getParameter("respuesta");
        String idComercio = request.getParameter("idComercio");
        String url = "/lciv---parcial-2-PontVergesJuanPedro/ComercioIndividualServlet?id=" + idComercio + "";
        Respuesta r = new Respuesta(respuesta);
        g.agregarRespuestas(r);
        g.agregarRespuestaComentario(idComentario);
        response.sendRedirect(url);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
