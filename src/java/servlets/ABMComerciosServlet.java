/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Comercio;
import model.Rubro;

/**
 *
 * @author jppon
 */
@WebServlet(name = "ABMComerciosServlet", urlPatterns = {"/ABMComerciosServlet"})
public class ABMComerciosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ABMComerciosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ABMComerciosServlet at " + request.getContextPath() + "</h1>");
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

        String nombre = "";
        String descripcion = "";
        int id_comercio = 0;
        int id_rubro = 0;

        nombre = request.getParameter("nombre");
        descripcion = request.getParameter("descripcion");
        if (!(request.getParameter("idComercio") == null)) {
            id_comercio = Integer.parseInt(request.getParameter("idComercio"));
        }
        if (!(request.getParameter("id_rubro") == null)) {
            id_rubro = Integer.parseInt(request.getParameter("id_rubro"));
        }
        ArrayList<Comercio> c = g.obtenerComercioPorRubro(id_rubro);
        Rubro r = g.obtenerRubroPorId(id_rubro);
        String abmOfertas = request.getParameter("opcion");
        switch (abmOfertas) {
            case "crear":
                if ((nombre.equals("")) || (descripcion.equals("")) || (id_rubro == 0)) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmComercios.jsp");
                    request.setAttribute("error", "Error al agregar el comercio, porfavor complete todo los campos");
                    rd.forward(request, response);
                } else {
                    if (g.agregarComercio(new Comercio(id_comercio, nombre, r, descripcion))) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmComercios.jsp");
                        request.setAttribute("mensaje", "Comercio Agregado correctamente!");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmComercios.jsp");
                        request.setAttribute("error", "Error al agregar el comercio, intente de nuevo");
                        rd.forward(request, response);
                    }
                }
                break;

            case "modificar":
                if ((nombre.equals("")) || (descripcion.equals("")) || (id_rubro == 0) || (id_comercio == 0)) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmComercios.jsp");
                    request.setAttribute("error", "Error al modificar el comercio, porfavor complete todo los campos");
                    rd.forward(request, response);
                } else {
                    if (g.modificarComercio(new Comercio(id_comercio, nombre, r, descripcion))) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmComercios.jsp");
                        request.setAttribute("mensaje", "Comerico modificado correctamente!");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmComercios.jsp");
                        request.setAttribute("error", "Error al modificar el comercio, intente de nuevo");
                        rd.forward(request, response);

                    }
                }
                break;

            case "eliminar":
                if (id_comercio > 0) {
                    if (g.eliminarComercio(id_comercio)) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmComercios.jsp");
                        request.setAttribute("mensaje", "Eliminado correctamente!");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmComercios.jsp");
                        request.setAttribute("error", "Error al eliminar el comercio, debera eliminar todos los comentarios en del comercio primero");
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
