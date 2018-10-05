/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author Usuario
 */
public class FormularioUsuarios extends HttpServlet {


    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jsp/formulario-usuario.jsp");
        
        
        List<Usuario> listaUsuarios = new ArrayList<>();
        
        request.setAttribute("lista", listaUsuarios);
        
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("jsp/formulario-usuario.jsp");
        
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String contrasenia = request.getParameter("contrasenia");
        
        Usuario u = new Usuario();
        u.nombre = nombre;
        u.correo = correo;
        u.contrasenia = contrasenia;
        
        guardarUsuario(u);
        
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(u);
        
        request.setAttribute("lista", listaUsuarios);
        
        rd.forward(request, response);
    }
    
    public void guardarUsuario(Usuario u) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/deportes", "root", "");
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuarios (nombre, correo, contrasenia) VALUES (?, ?, ?)");
            ps.setString(1, u.nombre);
            ps.setString(2, u.correo);
            ps.setString(3, u.contrasenia);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FormularioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormularioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
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
