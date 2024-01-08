/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.BaseDeDatos;
import Clases.Cliente;
import Clases.Producto;
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
public class ProductoDAO {

    public static List<Producto> obtenerProductos() {
        List< Producto > lstProducto = new ArrayList<>();
        String query = "SELECT * FROM productos ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            System.out.println("query=" + query);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio") );
                producto.setStock(rs.getInt("stock") );
                producto.setImagen(rs.getBytes("imagen"));
                producto.setEstado(rs.getString("estado") );
                lstProducto.add(producto);            
            }
            
        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerProductos");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println(lstProducto);
        return lstProducto;
    }

    public static boolean insertarProducto(Producto producto) {

        String query = "INSERT INTO productos (descripcion, precio, stock, imagen, estado) VALUES (?,?,?,?,?) ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getDescripcion());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setBytes(4, producto.getImagen());
            ps.setString(5, producto.getEstado());
           
            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                producto.setId(generatedKeys.getInt(1));
            } else {
                System.out.println("Creating producto failed, no ID obtained.");
                return false;
            }

        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        if (producto.getId() != 0) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean actualizarProducto(Producto producto) {

        String query = "UPDATE productos SET descripcion = ?, precio = ?, stock = ?, estado = ? WHERE id  = ? AND estado = 'A'";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getDescripcion());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setString(4, producto.getEstado());
            //ps.setBytes(5, producto.getImagen());
            ps.setInt(5, producto.getId());

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
    
    public static boolean eliminarProducto(Producto producto) {

        String query = "UPDATE productos SET estado = 'E' WHERE id  = ? AND estado = 'A'";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, producto.getId());

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

    public static List<Producto> obtenerProductosPorCodigo(Integer codigo) {
        List< Producto > lstProducto = new ArrayList<>();
        String query = "SELECT * FROM productos WHERE id = ? ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setInt(1, codigo);
            System.out.println("query=" + query);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio") );
                producto.setStock(rs.getInt("stock") );
                producto.setImagen(rs.getBytes("imagen"));
                producto.setEstado(rs.getString("estado") );
                lstProducto.add(producto);            
            }
            
        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerProductosPorCodigo");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println(lstProducto);
        return lstProducto;
    }

    public static List<Producto> obtenerProductosPorNombre(String busqueda) {
        List< Producto > lstProducto = new ArrayList<>();
        String query = "SELECT * FROM productos WHERE descripcion LIKE ? ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setString(1, busqueda + "%" );
            System.out.println("query=" + query);
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio") );
                producto.setStock(rs.getInt("stock") );
                producto.setImagen(rs.getBytes("imagen"));
                producto.setEstado(rs.getString("estado") );
                lstProducto.add(producto);            
            }
            
        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerProductosPorNombre");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println(lstProducto);
        return lstProducto;
    }
    
}
