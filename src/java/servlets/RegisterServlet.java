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
import model.Usuario;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GestorDB g = new GestorDB();
        String nombre = request.getParameter("registerNombre");
        String usuario = request.getParameter("registerUsuario");
        String contrasena = request.getParameter("registerContrasena");

        if (g.obtenerUsuarioPorNombre(usuario) == null) {
            // usuario disponible entonces se puede registrar
            Usuario u = new Usuario(usuario, nombre, contrasena);
            g.registrarUsuario(u);

            // setear variable de sesión 
            request.getSession().setAttribute("usuario", u.getUsuario());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/indexAdmin.jsp");
            rd.forward(request, response);

        } else {
            //usuario no disponible entonces no se puede registrar
            request.setAttribute("errorRegister", "Ese nombre de usuario ya existe, porfavor pruebe con otro");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
