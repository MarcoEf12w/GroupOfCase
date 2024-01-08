/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mpolo
 */
public class BaseDeDatos {
    private  static Connection con;
    
    private final static String SERVIDOR = "192.168.100.144";
    private final static String DATABASE = "ventasDB";
    private final static String USUARIO = "mpolo";
    private final static String PUERTO = "3306";
    private final static String PASSWORD = "C4puch1n00r30";
 
    public static Connection obtenerConexion(){
         //VARIABLES DE CONEXION
       
        
        String url = "";         
      
        try{
            //Endica al interprete de Java que en tiempo real mande a cargar la libreria jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");             
            //com.mysql.jdbc.Driver
            //cadena con datos de servidor
            url = "jdbc:mysql://" + SERVIDOR + ":" + PUERTO + "/" + DATABASE;
            //variable de conexion obteniendo el servidor , usuario y password
            con = DriverManager.getConnection(url, USUARIO, PASSWORD);
            System.out.println("Conexion a Base de Datos: " + url + " --> OK --> Id Conexión = [" + con.hashCode() + "]" ); 
        }catch (SQLException ex) {
            System.out.println("Error Cadena: " + ex);
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("Error Clase Conexion: " + ex);
            return null;
        }        
        catch (Exception ex){ 
            System.out.println("Error General Clase Conexion: " + ex);
            return null;
        }
        return con;
    }
    public static void cerrarConexion (){
        try { 
            Integer numCon = con.hashCode();
            System.out.println("Cerrando Conexion a Base de Datos con Id Conexión = [" + numCon + "]"); 
            con.close();
            System.out.println("Cerrada Conexion a Base de Datos con Id Conexión = [" + numCon + "]"); 
        } catch (SQLException ex) {
            Logger.getLogger(BaseDeDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
