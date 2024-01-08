/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.BaseDeDatos;
import Clases.Cliente;
import Clases.FacturaCab;
import java.sql.Connection;
import java.sql.Date;
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
public class FacturaCabDAO {
    
    public static boolean insertarFacturaCab(FacturaCab facturaCab) {

        String query = "INSERT INTO factura_cab (fecha,cliente_id,subtotal,iva,total,estado) VALUES (?,?,?,?,?,?) ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new Date(facturaCab.getFecha().getTime()) );
            ps.setInt(2, facturaCab.getClienteId());
            ps.setDouble(3, facturaCab.getSubtotal());
            ps.setDouble(4, facturaCab.getIva());
            ps.setDouble(5, facturaCab.getTotal());            
            ps.setString(6, facturaCab.getEstado());
            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                facturaCab.setId(generatedKeys.getInt(1));
            } else {
                System.out.println("Creating factura_cab failed, no ID obtained.");
                return false;
            }

        } catch (Exception ex) {

            Logger.getLogger(FacturaCabDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        if (facturaCab.getId() != 0) {
            return true;
        } else {
            return false;
        }

    }

    public static List<FacturaCab> obtenerFacturaPorCodigo(Integer codigo) {
        List<FacturaCab> lstFacturaCab = new ArrayList<>();
        String query = "SELECT * FROM factura_cab WHERE id = ?";        
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            System.out.println("query=" + query);
            ps.setInt(1, codigo );
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FacturaCab facturaCab = new FacturaCab();
                facturaCab.setId(rs.getInt("id"));
                facturaCab.setFecha(rs.getDate("fecha"));
                facturaCab.setClienteId(rs.getInt("cliente_id"));
                facturaCab.setSubtotal(rs.getDouble("subtotal"));
                facturaCab.setIva(rs.getDouble("iva"));
                facturaCab.setTotal(rs.getDouble("total"));                
                facturaCab.setEstado(rs.getString("estado"));
                lstFacturaCab.add(facturaCab);
            }

        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerFacturaPorCodigo");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("obtenerFacturaPorCodigo" + lstFacturaCab);
        return lstFacturaCab;
    }

    public static List<FacturaCab> obtenerFacturaPorNombreCliente(String busqueda) {
        List<FacturaCab> lstFacturaCab = new ArrayList<>();
        String query = "SELECT * FROM factura_cab a JOIN clientes b ON a.cliente_id = b.id WHERE b.apellido +' '+ a.nombre LIKE ?";        
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            System.out.println("query=" + query);
            ps.setString(1, busqueda + "%" );
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FacturaCab facturaCab = new FacturaCab();
                facturaCab.setId(rs.getInt("id"));
                facturaCab.setFecha(rs.getDate("fecha"));
                facturaCab.setClienteId(rs.getInt("cliente_id"));                
                facturaCab.setSubtotal(rs.getDouble("subtotal"));
                facturaCab.setIva(rs.getDouble("iva"));
                facturaCab.setTotal(rs.getDouble("total"));                
                facturaCab.setEstado(rs.getString("estado"));
                lstFacturaCab.add(facturaCab);
            }

        } catch (Exception ex) {

            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerFacturaPorCodigo");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("obtenerFacturaPorCodigo" + lstFacturaCab);
        return lstFacturaCab;
    }
    
}
