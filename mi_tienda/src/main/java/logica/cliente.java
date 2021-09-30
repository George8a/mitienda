/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.conexion;
import java.util.Date;

/**
 *
 * @author George
 */
public class cliente {

    private String idcliente;
    private String nombres;
    private String apellidos;
    private String direccion;
    private Date fechaNacimiento;
    private String telefono;
    private String email;
    private String estado;

    public cliente() {

    }

    public cliente getCliente(String idcliente) throws SQLException {
        this.idcliente = idcliente;
        return this.getCliente();

    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   // private cliente getcliente() {
   //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    public boolean guardarcliente() {
        conexion conexion = new conexion();
        String sentencia = "INSERT INTO cliente(idcliente, nombres, apellidos, direccion, fechaNacimiento, telefono, email, estado)"
                + "VALUES ('" + this.idcliente + "','" + this.nombres + "',"
                + "'" + this.apellidos + "','" + this.direccion + "','" + this.fechaNacimiento + "',"
                + "," + this.telefono + "','" + this.email + "','" + this.estado + "');";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean borrarcliente(String idcliente) {
        String sentencia = "DELETE FROM cliente WHERE idcliente='" + idcliente + "'";
        conexion conexion = new conexion();
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }

    }

    public boolean actualizarcontacto() {
        conexion conexion = new conexion();
        String sentencia = "UPDATE contacto SET nombres='" + this.nombres + "',apellidos='" + this.apellidos + "',direccion='" + this.direccion
                + "',fechaNacimiento='" + this.fechaNacimiento + "',telefono='" + this.telefono + "',email='" + this.email + "',ESTADO='" + this.estado
                + "'WHERE idcliente=" + this.idcliente + ";";
        if (conexion.setAutoCommitBD(false)){
            if(conexion.actualizarBD(sentencia)){
                conexion.commitBD();
                conexion.cerrarConexion();
                return false;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        }else{
            conexion.cerrarConexion();
            return false;
        }

    }
    
    public List<cliente> ListarClientes()throws SQLException{
        conexion conexion = new conexion();
        List<cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente order by idcliente asc";
        ResultSet rs = conexion.consultarDB(sql);
        cliente c;
        while (rs.next()){
            c = new cliente();
            c.setIdcliente(rs.getString("idcliente"));
            c.setNombres(rs.getString("nombres"));
            c.setApellidos(rs.getString("apellidos"));
            c.setDireccion(rs.getString("direccion"));
            c.setFechaNacimiento(rs.getDate("fechaNacimiento"));
            c.setTelefono(rs.getString("telefono"));
            c.setEmail(rs.getString("ESTADO"));
            listaClientes.add(c);
              
            
                    
            
        }
        conexion.cerrarConexion();
        return listaClientes;
    }
    public cliente getCliente() throws SQLException {
        conexion conexion = new conexion();
        String sql = "select *from clientes where idcliente='"+this.idcliente+"'";
        ResultSet rs = conexion.consultarDB(sql);
        if(rs.next()){
            this.idcliente = rs.getString("idcliente");
            this.nombres = rs.getString("nombres");
            this.apellidos = rs.getString("apellidos");
            this.direccion = rs.getString("direccion");
            this.fechaNacimiento = rs.getDate("fechaNacimiento");
            this.telefono = rs.getString("telefono");
            this.email = rs.getString("email");
            this.estado = rs.getString("ESTADO");
            conexion.cerrarConexion();
            return this;
            
                    

        }else{
            conexion.cerrarConexion();
            return null;
        }
        
        
    }

    @Override
    public String toString() {
        return "cliente{"+"idcliente="+idcliente+", nombres=" + nombres+", apellidos="+apellidos+", direccion="+direccion+", fechaNacimiento,"+fechaNacimiento+", telefono="+telefono+", email"+email+", ESTADO="+estado+'}'; //To change body of generated methods, choose Tools | Templates.;
    }
   
}


