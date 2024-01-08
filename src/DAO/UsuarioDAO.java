/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.BaseDeDatos;
import Clases.Cliente;
import Clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mpolo
 */
public class UsuarioDAO {
    
    public static boolean insertarUsuario(Usuario usuario) {

        String query = "INSERT INTO usuarios (login, clave, nombre_apellido, estado) VALUES (?,?,?,?) ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getNombreApellido());         
            ps.setString(4, usuario.getEstado());
            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                usuario.setId(generatedKeys.getInt(1));
            } else {
                System.out.println("Creating user failed, no ID obtained.");
                return false;
            }

        } catch (Exception ex) {

            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        if (usuario.getId() != 0) {
            return true;
        } else {
            return false;
        }

    }
    
    public static boolean actualizarUsuario(Usuario usuario) {

        String query = "UPDATE usuarios SET login = ? , clave = ? , nombre_apellido = ?  WHERE id  = ? AND estado = 'A'";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getNombreApellido());                       
            ps.setInt(4, usuario.getId());

            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            return true;
        } catch (Exception ex) {

            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        return false;
    }
    
    public static boolean eliminarUsuario(Usuario usuario) {

        String query = "UPDATE usuarios SET estado = 'E' WHERE id  = ? AND estado = 'A'";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, usuario.getId());

            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            return true;
        } catch (Exception ex) {

            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        return false;

    }
    
    public static List<Usuario> obtenerUsuarios() {
        List< Usuario> lstUsuario = new ArrayList<>();
        String query = "SELECT * FROM usuarios ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            System.out.println("query=" + query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNombreApellido(rs.getString("nombre_apellido"));                
                usuario.setEstado(rs.getString("estado"));
                lstUsuario.add(usuario);
            }

        } catch (Exception ex) {

            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerUsuarios");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("obtenerUsuarios" + lstUsuario);
        return lstUsuario;

    }

    public static Usuario obtenerUsuarioPorLogin(String login) {
        Usuario usuario;
        try {
            String query = "SELECT * FROM usuarios WHERE login = ? ";

            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, login);
            System.out.println("query=" + query);
            ResultSet rs = ps.executeQuery();
            usuario = new Usuario();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNombreApellido(rs.getString("nombre_apellido"));
                usuario.setEstado(rs.getString("estado"));

            } else {
                System.out.println("No se obtuvo registros de la base");
                usuario = null;
            }
            BaseDeDatos.cerrarConexion();
            return usuario;
        } catch (Exception e) {
            BaseDeDatos.cerrarConexion();
            System.out.println("Usuario.login: " + e);
            return null;
        }

    }

    public static Usuario login(String login, String clave) {

        try {

            Usuario usuario = obtenerUsuarioPorLogin(login);

            if (usuario != null) {

                if (clave.equals(usuario.getClave())) {
                    System.out.println("Login OK - " + usuario.toString());
                } else {
                    System.out.println("Login encontrado con clave incorrecta");
                    usuario = null;
                }
            } else {
                System.out.println("No se obtuvo registros de la base");
                usuario = null;
            }
            return usuario;

        } catch (Exception e) {

            System.out.println("Usuario.login: " + e);
            return null;
        }

    }

}
