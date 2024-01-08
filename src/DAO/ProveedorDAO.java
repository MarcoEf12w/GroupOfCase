/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.BaseDeDatos;
import Clases.Proveedor;
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
public class ProveedorDAO {
    
    public static boolean insertarProveedor(Proveedor proveedor) {

        String query = "INSERT INTO proveedores (documento, nombre_apellido, direccion, telefono, correo, estado) VALUES (?,?,?,?,?,?) ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proveedor.getDocumento());
            ps.setString(2, proveedor.getNombreApellido());            
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getCorreo());
            ps.setString(6, proveedor.getEstado());
            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                proveedor.setId(generatedKeys.getInt(1));
            } else {
                System.out.println("Creating proveedor failed, no ID obtained.");
                return false;
            }

        } catch (Exception ex) {

            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        if (proveedor.getId() != 0) {
            return true;
        } else {
            return false;
        }

    }
    
    public static boolean actualizarProveedor(Proveedor proveedor) {

        String query = "UPDATE proveedores SET documento = ? , nombre_apellido = ? , direccion = ? , telefono = ? , correo = ? , estado = ?  WHERE id  = ? AND estado = 'A'";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proveedor.getDocumento());
            ps.setString(2, proveedor.getNombreApellido());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getCorreo());
            ps.setString(6, proveedor.getEstado());
            ps.setInt(7, proveedor.getId());

            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            return true;
        } catch (Exception ex) {

            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        return false;

    }
    
    public static boolean eliminarProveedor(Proveedor proveedor) {

        String query = "UPDATE proveedores SET estado = 'E' WHERE id  = ? AND estado = 'A'";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, proveedor.getId());

            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            return true;
        } catch (Exception ex) {

            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        return false;

    }
    
    public static List<Proveedor> obtenerProveedores() {
        List< Proveedor> lstProveedor = new ArrayList<>();
        String query = "SELECT * FROM proveedores ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            System.out.println("query=" + query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setDocumento(rs.getString("documento"));
                proveedor.setNombreApellido(rs.getString("nombre_apellido"));                
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setEstado(rs.getString("estado"));
                lstProveedor.add(proveedor);
            }

        } catch (Exception ex) {

            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerProveedores");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("obtenerProveedores" + lstProveedor);
        return lstProveedor;

    }

    public static List<Proveedor> obtenerProveedoresPorCodigo(Integer codigo) {
        List< Proveedor> lstProveedor = new ArrayList<>();
        String query = "SELECT * FROM proveedores WHERE id = ? ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setInt(1, codigo);
            System.out.println("query=" + query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setDocumento(rs.getString("documento"));
                proveedor.setNombreApellido(rs.getString("nombre_apellido"));                
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setEstado(rs.getString("estado"));
                lstProveedor.add(proveedor);
            }

        } catch (Exception ex) {

            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerProveedoresPorCodigo");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("obtenerProveedoresPorCodigo" + lstProveedor);
        return lstProveedor;

    }

    public static List<Proveedor> obtenerProveedoresPorNombre(String busqueda) {
        List< Proveedor> lstProveedor = new ArrayList<>();
        String query = "SELECT * FROM proveedores WHERE nombre_apellido LIKE ? ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setString(1, busqueda + "%");
            System.out.println("query=" + query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setDocumento(rs.getString("documento"));
                proveedor.setNombreApellido(rs.getString("nombre_apellido"));                
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setEstado(rs.getString("estado"));
                lstProveedor.add(proveedor);
            }

        } catch (Exception ex) {

            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerProveedoresPorNombre");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("obtenerProveedoresPorNombre" + lstProveedor);
        return lstProveedor;
    }
    
}
