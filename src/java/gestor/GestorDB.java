package gestor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comercio;
import model.Rubro;
import model.Usuario;
import model.Comentario;
import model.DTOComentariosComercios;
import model.DTOPromedioComercio;
import model.DTOValoracionesPorPuntaje;
import model.Oferta;
import model.Respuesta;
import org.mindrot.jbcrypt.BCrypt;

public class GestorDB {

    private String CONN = "jdbc:sqlserver://JUANPEDRO;databaseName=PruebaBD";
    private String PASS = "123456";
    private String USER = "sa";
    private Connection con;

    private void abrirConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            con = DriverManager.getConnection(CONN, USER, PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cerrarConexion() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Rubro> obtenerRubros() {
        ArrayList<Rubro> lista = new ArrayList<Rubro>();
        try {
            abrirConexion();
            String sql = "select * from rubros";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id_rubro");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                lista.add(new Rubro(id, nombre, descripcion));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Rubro obtenerRubroPorId(int id) {
        Rubro resultado = null;
        try {
            abrirConexion();
            String sql = "SELECT * FROM rubros WHERE id_rubro = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id_r = rs.getInt("id_rubro");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                resultado = new Rubro(id_r, nombre, descripcion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return resultado;
    }

    public ArrayList<Comercio> obtenerComercioPorRubro(int idRubro) {
        ArrayList<Comercio> lista = new ArrayList<Comercio>();
        try {
            abrirConexion();
            String sql = "select * from comercios where id_rubro = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idRubro);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id_comercio = rs.getInt("id_comercio");
                int id_rubro = rs.getInt("id_rubro");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                ArrayList<Comentario> comentarios = getComentariosPorComercio(id_comercio);
                Rubro r = obtenerRubroPorId(id_rubro);
                lista.add(new Comercio(id_comercio, nombre, r, descripcion, comentarios));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Comercio obtenerComercioPorId(int id) {
        Comercio resultado = null;
        try {
            abrirConexion();
            String sql = "SELECT * FROM comercios WHERE id_comercio = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id_comercio = rs.getInt("id_comercio");
                int id_rubro = rs.getInt("id_rubro");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Rubro r = obtenerRubroPorId(id_rubro);
                resultado = new Comercio(id_comercio, nombre, r, descripcion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }

        return resultado;
    }

    public Usuario obtenerUsuarioPorNombre(String user) {
        Usuario resultado = null;
        try {
            abrirConexion();
            String sql = "SELECT * FROM usuarios WHERE usuario = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");
                resultado = new Usuario(id_usuario, usuario, nombre, contrasena);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultado;
    }

    public Usuario obtenerUsuarioPorId(int id) {
        Usuario resultado = null;
        try {
            abrirConexion();
            String sql = "SELECT * FROM usuarios WHERE id_usuario = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");
                resultado = new Usuario(id_usuario, usuario, nombre, contrasena);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultado;
    }

    public boolean logearse(String usuario, String contrasena) {
        boolean resultado = false;
        Usuario fetchedUser = obtenerUsuarioPorNombre(usuario);
        if (!(fetchedUser == null)) {
            if (fetchedUser.getUsuario().equals(usuario) && checkContrasena(contrasena, fetchedUser.getContrasena())) {
                resultado = true;
            }
        }
        return resultado;
    }

    public String hashContrasena(String textoContrasena) {
        return BCrypt.hashpw(textoContrasena, BCrypt.gensalt());
    }

    public boolean checkContrasena(String textoContrasena, String hashedContrasena) {
        boolean contrasenaCorrecta = false;
        if (BCrypt.checkpw(textoContrasena, hashedContrasena)) {
            contrasenaCorrecta = true;
        }
        return contrasenaCorrecta;
    }

    public boolean registrarUsuario(Usuario u) {
        boolean inserto = false;
        try {
            abrirConexion();
            String sql = "INSERT INTO usuarios (nombre, usuario, contrasena) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, u.getNombre());
            st.setString(2, u.getUsuario());
            st.setString(3, hashContrasena(u.getContrasena()));
            st.execute();
            inserto = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return inserto;
    }

    public boolean agregarRubro(Rubro r) {
        boolean inserto = false;
        try {
            abrirConexion();
            String sql = "insert into rubros (nombre, descripcion) values (?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, r.getNombre());
            st.setString(2, r.getDescripcion());
            st.execute();
            inserto = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return inserto;
    }

    public boolean modificarRubro(Rubro r) {
        boolean modificado = false;
        try {
            abrirConexion();
            String sql = "update rubros set nombre = ?, descripcion = ? where id_rubro = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, r.getNombre());
            st.setString(2, r.getDescripcion());
            st.setInt(3, r.getId());
            st.executeUpdate();
            modificado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return modificado;
    }

    public boolean eliminarRubro(int id) {
        boolean eliminado = false;
        try {
            abrirConexion();
            String sql = "DELETE FROM rubros WHERE id_rubro = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            eliminado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return eliminado;
    }

    public boolean agregarComercio(Comercio c) {
        boolean inserto = false;
        try {
            abrirConexion();
            String sql = "insert into comercios (id_rubro, nombre, descripcion) values (?,?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, c.getRubro().getId());
            st.setString(2, c.getNombre());
            st.setString(3, c.getDescripcion());
            st.execute();
            inserto = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return inserto;
    }

    public boolean modificarComercio(Comercio c) {
        boolean modificado = false;
        try {
            abrirConexion();
            String sql = "update comercios set nombre = ?, descripcion = ?, id_rubro = ? where id_comercio = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, c.getNombre());
            st.setString(2, c.getDescripcion());
            st.setInt(3, c.getRubro().getId());
            st.setInt(4, c.getId());
            st.executeUpdate();
            modificado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return modificado;
    }

    public boolean eliminarComercio(int id) {
        boolean resultado = false;
        try {
            abrirConexion();
            String sql = "DELETE FROM comercios WHERE id_comercio = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            resultado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return resultado;
    }

    public ArrayList<Comercio> getComercios() {
        ArrayList<Comercio> comercios = new ArrayList<Comercio>();
        try {
            abrirConexion();
            String sql = "select * from comercios";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_comercio = rs.getInt("id_comercio");
                int id_rubro = rs.getInt("id_rubro");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Rubro rubro = obtenerRubroPorId(id_rubro);
                comercios.add(new Comercio(id_comercio, nombre, rubro, descripcion));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return comercios;
    }

    public boolean insertarComentario(Comentario coment, Usuario u) {
        boolean insertado = false;
        try {
            abrirConexion();
            String sql = "INSERT INTO comentarios (id_usuario, comentario, id_comercio) values(?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, u.getId_usuario());
            st.setString(2, coment.getComentario());
            st.setInt(3, coment.getComercio().getId());
            st.execute();
            insertado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return insertado;
    }

    public boolean insertarComentario(Comentario coment, Usuario u, float puntaje) {
        boolean insertado = false;
        try {
            abrirConexion();
            String sql = "INSERT INTO comentarios (id_usuario, comentario, id_comercio, puntaje) values(?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, u.getId_usuario());
            st.setString(2, coment.getComentario());
            st.setInt(3, coment.getComercio().getId());
            st.setFloat(4, puntaje);
            st.execute();
            insertado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return insertado;
    }

    public ArrayList<Comentario> getComentariosPorComercio(int id) {
        ArrayList<Comentario> lista = new ArrayList<Comentario>();
        try {
            abrirConexion();
            String sql = "select id_comentario, id_usuario, comentario, id_comercio, id_respuesta from comentarios where id_comercio = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id_comentario = rs.getInt("id_comentario");
                int id_usuario = rs.getInt("id_usuario");
                String comentario = rs.getString("comentario");
                int id_comercio = rs.getInt("id_comercio");
                int id_respuesta = rs.getInt("id_respuesta");
                Usuario u = obtenerUsuarioPorId(id_usuario);
                Comercio comercio = obtenerComercioPorId(id_comercio);
                Respuesta r = getRespuestaPorId(id_respuesta);
                Comentario c = new Comentario(id_comentario, u, comentario, comercio, r);
                lista.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public ArrayList<Comentario> getComentarios() {
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        try {
            abrirConexion();
            String sql = "select * from comentarios";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_comentario = rs.getInt("id_comentario");
                int id_usuario = rs.getInt("id_usuario");
                String comentario = rs.getString("comentario");
                int id_comercio = rs.getInt("id_comercio");
                float puntaje = rs.getFloat("puntaje");
                Usuario u = obtenerUsuarioPorId(id_usuario);
                Comercio c = obtenerComercioPorId(id_comercio);
                comentarios.add(new Comentario(id_comentario, u, comentario, c, puntaje));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return comentarios;
    }

    public Comentario getComentarioPorId(int id) {
        Comentario comentario = null;
        try {
            abrirConexion();
            String sql = "SELECT * FROM comentarios WHERE id_comentario = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id_comentario = rs.getInt("id_comentario");
                int id_usuario = rs.getInt("id_usuario");
                String cmt = rs.getString("comentario");
                int id_comercio = rs.getInt("id_comercio");
                Usuario u = obtenerUsuarioPorId(id_usuario);
                Comercio c = obtenerComercioPorId(id_comercio);
                comentario = new Comentario(id_comentario, u, cmt, c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return comentario;
    }

    public void eliminarComentario(int id) {
        try {
            abrirConexion();
            String sql = "DELETE FROM comentarios WHERE id_comentario = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public boolean agregarOferta(Oferta o) {
        boolean inserto = false;
        try {
            abrirConexion();
            String sql = "insert into ofertas (titulo, descripcion, precio, id_comercio) values (?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, o.getTitulo());
            st.setString(2, o.getDescripcion());
            st.setFloat(3, o.getPrecio());
            st.setInt(4, o.getComercio().getId());
            st.execute();
            inserto = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return inserto;
    }

    public ArrayList<Oferta> getOfertas() {
        ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
        try {
            abrirConexion();
            String sql = "select * from ofertas";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_oferta = rs.getInt("id_oferta");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                float precio = rs.getFloat("precio");
                int id_comercio = rs.getInt("id_comercio");
                Comercio c = obtenerComercioPorId(id_comercio);
                ofertas.add(new Oferta(id_oferta, titulo, descripcion, precio, c));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return ofertas;
    }

    public Oferta obtenerOfertaPorId(int id) {
        Oferta oferta = null;
        try {
            abrirConexion();
            String sql = "SELECT * FROM ofertas WHERE id_oferta = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id_oferta = rs.getInt("id_oferta");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                float precio = rs.getFloat("precio");
                int id_comercio = rs.getInt("id_comercio");
                Comercio c = obtenerComercioPorId(id_comercio);
                oferta = new Oferta(id_oferta, titulo, descripcion, precio, c);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return oferta;
    }

    public ArrayList<Oferta> getOfertasPorComercio(int id) {
        ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
        try {
            abrirConexion();
            String sql = "select * from ofertas where id_comercio = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id_oferta = rs.getInt("id_oferta");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                float precio = rs.getFloat("precio");
                int id_comercio = rs.getInt("id_comercio");
                Comercio c = obtenerComercioPorId(id_comercio);
                ofertas.add(new Oferta(id_oferta, titulo, descripcion, precio, c));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return ofertas;
    }

    public boolean modificarOferta(Oferta o) {
        boolean modificado = false;
        try {
            abrirConexion();
            String sql = "update ofertas set titulo = ?, descripcion = ?, precio = ?, id_comercio = ? where id_oferta = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, o.getTitulo());
            st.setString(2, o.getDescripcion());
            st.setFloat(3, o.getPrecio());
            st.setInt(4, o.getComercio().getId());
            st.setInt(5, o.getId_oferta());
            st.executeUpdate();
            modificado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return modificado;
    }

    public boolean eliminarOferta(int id) {
        boolean eliminado = false;
        try {
            abrirConexion();
            String sql = "DELETE FROM ofertas WHERE id_oferta = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            eliminado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return eliminado;
    }

    public void calificarComercio(int valoracion, int idComercio, int idComentario) {
        try {
            abrirConexion();
            String sql = "update comentarios set puntaje = ? where id_comercio = ? AND where id_comentario = )";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, valoracion);
            st.setInt(2, idComercio);
            st.setInt(3, idComentario);
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public float getPromedioValoracionPorComercio(int idComercio) {
        float promedio = 0;
        try {
            abrirConexion();
            String sql = "select SUM(puntaje)/COUNT(id_comercio) from comentarios where id_comercio = ? and puntaje is not null";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, idComercio);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                promedio = rs.getFloat(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return promedio;
    }

    public ArrayList<DTOPromedioComercio> getPromedioValoracionPorComercio() {
        ArrayList<DTOPromedioComercio> comercios = new ArrayList<DTOPromedioComercio>();;
        try {
            abrirConexion();
            String sql = "select id_comercio, SUM(puntaje)/COUNT(id_comercio) promedio from comentarios where puntaje is not null group by id_comercio order by promedio desc";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id_comercio = rs.getInt("id_comercio");
                float promedio = rs.getInt("promedio");
                Comercio c = obtenerComercioPorId(id_comercio);
                comercios.add(new DTOPromedioComercio(c, promedio));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return comercios;
    }

    public ArrayList<Oferta> getOfertasBusqueda(String busqueda) {
        ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
        try {
            abrirConexion();
            String sql = "select * from ofertas where titulo like ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, busqueda);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id_oferta = rs.getInt("id_oferta");
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                float precio = rs.getFloat("precio");
                int id_comercio = rs.getInt("id_comercio");
                Comercio c = obtenerComercioPorId(id_comercio);
                ofertas.add(new Oferta(id_oferta, titulo, descripcion, precio, c));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return ofertas;
    }

    public ArrayList<DTOComentariosComercios> getComentariosPorComercios() {
        ArrayList<DTOComentariosComercios> comercios = new ArrayList<DTOComentariosComercios>();
        try {
            abrirConexion();
            String sql = "select comercios.nombre, comercios.descripcion, COUNT(comentarios.id_comercio) Comentarios from comentarios\n"
                    + "join comercios on comentarios.id_comercio = comercios.id_comercio\n"
                    + "group by comentarios.id_comercio, comercios.nombre, comercios.descripcion\n"
                    + "ORDER BY COUNT(comentarios.id_comercio) DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int cantidadComentarios = rs.getInt("Comentarios");
                comercios.add(new DTOComentariosComercios(nombre, descripcion, cantidadComentarios));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return comercios;
    }

    // Primero agrego la respuesta
    public boolean agregarRespuestas(Respuesta r) {
        boolean inserto = false;
        try {
            abrirConexion();
            String sql = "insert into respuestas values (?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, r.getRespuesta());
            st.execute();
            inserto = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return inserto;
    }

    // Metodo intermedio
    public int getIdRespuesta() {
        int id_respuesta = 0;
        try {
            abrirConexion();
            String sql = "select IDENT_CURRENT('respuestas') id_respuesta";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id_respuesta = rs.getInt("id_respuesta");
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return id_respuesta;
    }

    // Segundo updateo el id de respuesta del comentario asi ese comentario tiene una respuesta
    public void agregarRespuestaComentario(int idComentario) {
        try {
            int id_respuesta = getIdRespuesta();
            abrirConexion();
            String sql = "update comentarios set id_respuesta = ? where id_comentario = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_respuesta);
            st.setInt(2, idComentario);
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
    }

    public Respuesta getRespuestaPorId(int id_respuesta) {
        Respuesta r = null;
        try {
            abrirConexion();
            String sql = "select * from respuestas where id_respuesta = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_respuesta);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id_respuesta");
                String respuesta = rs.getString("comentario");
                r = new Respuesta(id, respuesta);
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return r;
    }

    public ArrayList<Comentario> getComentariosSinRespuesta() {
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        try {
            abrirConexion();
            String sql = "select * from comentarios where id_respuesta is null";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id_comentario = rs.getInt("id_comentario");
                int id_usuario = rs.getInt("id_usuario");
                String comentario = rs.getString("comentario");
                int id_comercio = rs.getInt("id_comercio");
                float puntaje = rs.getFloat("puntaje");
                Usuario u = obtenerUsuarioPorId(id_usuario);
                Comercio c = obtenerComercioPorId(id_comercio);
                comentarios.add(new Comentario(id_comentario, u, comentario, c, puntaje));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return comentarios;
    }

    public ArrayList<DTOValoracionesPorPuntaje> valoracionesPorPuntaje() {
        ArrayList<DTOValoracionesPorPuntaje> lista = new ArrayList<DTOValoracionesPorPuntaje>();
        try {
            abrirConexion();
            String sql = "select puntaje, count(*) cantidad from comentarios group by puntaje order by puntaje desc";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int puntaje = rs.getInt("puntaje");
                int cantidad = rs.getInt("cantidad");
                lista.add(new DTOValoracionesPorPuntaje(puntaje, cantidad));
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarConexion();
        }
        return lista;
    }

}
