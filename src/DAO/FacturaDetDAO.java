/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.BaseDeDatos;
import Clases.FacturaCab;
import Clases.FacturaDet;
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
public class FacturaDetDAO {

    public static boolean insertarFacturaDet(FacturaDet facturaDet) {
        String query = "INSERT INTO factura_det (factura_id,detalle_id,producto_id, precio, cantidad, total,estado) VALUES (?,?,?,?,?,?,?) ";
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, facturaDet.getFacturaId() );
            ps.setInt(2, facturaDet.getDetalleId() );
            ps.setInt(3, facturaDet.getProductoId());
            ps.setDouble(4, facturaDet.getPrecio());
            ps.setInt(5, facturaDet.getCantidad());            
            ps.setDouble(6, facturaDet.getTotal());            
            ps.setString(7, facturaDet.getEstado());
            System.out.println("query=" + query);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }

//            ResultSet generatedKeys = ps.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                facturaCab.setId(generatedKeys.getInt(1));
//            } else {
//                System.out.println("Creating factura_cab failed, no ID obtained.");
//                return false;
//            }

        } catch (Exception ex) {

            Logger.getLogger(FacturaDetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally");
            BaseDeDatos.cerrarConexion();
        }
        if (facturaDet.getDetalleId() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static List<FacturaDet> obtenerDetallesPorCodigoFactura(Integer codigo) {
        List<FacturaDet> lstFacturaDet = new ArrayList<>();
        String query = "SELECT a.factura_id, a.detalle_id, a.producto_id,a.precio, a.cantidad, a.total, a.estado, b.descripcion FROM factura_det a JOIN productos b ON a.producto_id=b.id WHERE a.factura_id = ?";        
        try {
            Connection con = BaseDeDatos.obtenerConexion();
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            System.out.println("query=" + query);
            ps.setInt(1, codigo );
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FacturaDet facturaDet = new FacturaDet();
                facturaDet.setFacturaId(rs.getInt("factura_id"));
                facturaDet.setDetalleId(rs.getInt("detalle_id"));
                facturaDet.setProductoId(rs.getInt("producto_id"));
                facturaDet.setPrecio(rs.getDouble("precio"));
                facturaDet.setCantidad(rs.getInt("cantidad"));
                facturaDet.setTotal(rs.getDouble("total"));                
                facturaDet.setEstado(rs.getString("estado"));
                facturaDet.setNombreProducto(rs.getString("descripcion"));
                lstFacturaDet.add(facturaDet);
            }

        } catch (Exception ex) {

            Logger.getLogger(FacturaDetDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("finally obtenerDetallesPorCodigoFactura");
            BaseDeDatos.cerrarConexion();
        }
        System.out.println("obtenerDetallesPorCodigoFactura" + lstFacturaDet);
        return lstFacturaDet;
    }
    
}
