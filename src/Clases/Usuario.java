/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import DAO.UsuarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mpolo
 */
public class Usuario {

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", login=" + login + ", clave=" + clave + ", nombreApellido=" + nombreApellido + ", estado=" + estado + '}';
    }
  static  Logger logger = Logger.getLogger(Usuario.class.getName());
    private Integer id;
    private String login;
    private String clave;
    private String nombreApellido;
    private String estado;

    public Usuario(Integer id, String login, String clave, String nombreApellido, String estado) {
        this.id = id;
        this.login = login;
        this.clave = clave;
        this.nombreApellido = nombreApellido;
        this.estado = estado;
    }

    public Usuario() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the nombreApellido
     */
    public String getNombreApellido() {
        return nombreApellido;
    }

    /**
     * @param nombreApellido the nombreApellido to set
     */
    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
}
