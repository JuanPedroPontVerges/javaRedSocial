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
import model.Rubro;

/**
 *
 * @author jppon
 */
@WebServlet(name = "ABMRubrosServlet", urlPatterns = {"/ABMRubrosServlet"})
public class ABMRubrosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        String abmRubros = request.getParameter("opcion");
        switch (abmRubros) {
            case "crear":
                nombre = request.getParameter("nombreRubro");
                descripcion = request.getParameter("descripcionRubro");
                if (nombre.equals("") || descripcion.equals("")) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmRubros.jsp");
                    request.setAttribute("error", "Error al agregar el producto, porfavor complete todo los campos");
                    rd.forward(request, response);
                } else {
                    if (g.agregarRubro(new Rubro(nombre, descripcion))) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmRubros.jsp");
                        request.setAttribute("mensaje", "Agregado correctamente!");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmRubros.jsp");
                        request.setAttribute("error", "Error al agregar el producto, intente de nuevo");
                        rd.forward(request, response);
                    }
                }
                break;
            case "modificar":
                if (request.getParameter("idRubro") != null) {
                    int idRubro = Integer.parseInt(request.getParameter("idRubro"));
                    if (request.getParameter("nombre") != null) {
                        nombre = request.getParameter("nombre");
                    }
                    if (request.getParameter("descripcion") != null) {
                        descripcion = request.getParameter("descripcion");
                    }

                    if (g.modificarRubro(new Rubro(idRubro, nombre, descripcion))) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmRubros.jsp");
                        request.setAttribute("mensaje", "Modificado correctamente!");
                        rd.forward(request, response);
                    }
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmRubros.jsp");
                    request.setAttribute("error", "Error al modificar el rubro, intente de nuevo");
                    rd.forward(request, response);
                }

                break;
            case "eliminar":
                int rubro = Integer.parseInt(request.getParameter("rubroEliminar"));
                if (rubro > -1) {
                    if (g.eliminarRubro(rubro)) {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmRubros.jsp");
                        request.setAttribute("mensaje", "Eliminado correctamente!");
                        rd.forward(request, response);
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/abmRubros.jsp");
                        request.setAttribute("error", "Error al eliminar el comercio, deberia eliminar todos los comercios en el rubro primero");
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
