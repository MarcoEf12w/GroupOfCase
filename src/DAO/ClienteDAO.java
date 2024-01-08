/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.BaseDeDatos;
import Clases.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mpolo
 */
public class ClienteDAO {

    public static boolean insertarCliente(Cliente cliente) {

        String query = "INSERT INTO clientes (documento, nombre, apellido, direccion, telefono, correo, estado) VALUES (?,?,?,?,?,?,?) ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getDocumento());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getEstado());
            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getInt(1));
            } else {
                System.out.println("Creating user failed, no ID obtained.");
                return false;
            }

        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        if (cliente.getId() != 0) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean actualizarCliente(Cliente cliente) {

        String query = "UPDATE clientes SET documento = ? , nombre = ? , apellido = ? , direccion = ? , telefono = ? , correo = ? , estado = ?  WHERE id  = ? AND estado = 'A'";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getDocumento());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getEstado());
            ps.setInt(8, cliente.getId());

            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            return true;
        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        return false;

    }

    public static boolean eliminarCliente(Cliente cliente) {

        String query = "UPDATE clientes SET estado = 'E' WHERE id  = ? AND estado = 'A'";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cliente.getId());

            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            return true;
        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        return false;

    }

    public static List<Cliente> obtenerClientes() {
        List< Cliente> lstCliente = new ArrayList<>();
        String query = "SELECT * FROM clientes ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            System.out.println("query=" + query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setDocumento(rs.getString("documento"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setEstado(rs.getString("estado"));
                lstCliente.add(cliente);
            }

        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerClientes");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("lstCliente" + lstCliente);
        return lstCliente;

    }

    public static List<Cliente> obtenerClientesPorNombre(String nombre ) {
        List< Cliente> lstCliente = new ArrayList<>();
        String query = "SELECT * FROM clientes WHERE apellido LIKE ?";        
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            System.out.println("query=" + query);
            ps.setString(1, nombre + "%" );
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setDocumento(rs.getString("documento"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setEstado(rs.getString("estado"));
                lstCliente.add(cliente);
            }

        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerClientesPorNombre");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("obtenerClientesPorNombre" + lstCliente);
        return lstCliente;
    }

    public static List<Cliente> obtenerClientesPorCodigo(Integer codigo ) {
        List< Cliente> lstCliente = new ArrayList<>();
        String query = "SELECT * FROM clientes WHERE id = ? ";        
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            System.out.println("query=" + query);
            ps.setInt(1, codigo  );
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setDocumento(rs.getString("documento"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setEstado(rs.getString("estado"));
                lstCliente.add(cliente);
            }

        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerClientesPorCodigo");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("obtenerClientesPorCodigo" + lstCliente);
        return lstCliente;
    }

}
