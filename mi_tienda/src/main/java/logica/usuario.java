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
import java.util.Date;
import persistencia.conexion;

/**
 *
 * @author George
 */
public class usuario {

    private int idUsuario;
    private int idRol;
    private String usuario;
    private String contrasena;
    private Date ultimoIngreso;

    public usuario() {

    }

    public usuario getUsuario(int idUsuario) throws SQLException {
        this.idUsuario = idUsuario;
        return this.getUsuario();

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getString() {
        return usuario;
    }

    public void setusuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(Date ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public boolean guardarUsuario() {
        conexion conexion = new conexion();
        String sentencia = "INSERT INTO usuario(idRol, usuario, contraseña, ultimoInggreso)"
                + "VALUES ('" + this.idRol + "','" + this.usuario + "','" + this.contrasena + "',"
                + "'" + this.ultimoIngreso + "');";
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

    public boolean borrarCliente(int idusuario) {
        String sentencia = "DELETE FROM usuario WHERE idUsuario='" + idusuario + "'";
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

    public boolean actualizarusuario() {
        conexion conexion = new conexion();
        String sentencia = "UPDATE usuario SET idRol='" + this.idRol + "',usuario='" + this.usuario + "',contraseña='" + this.contrasena + "',ultimoIngreso'" + this.ultimoIngreso
                + "'WHERE idUsuario=" + this.idUsuario + ";";
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

    public List<usuario> ListarClientes() throws SQLException {
        conexion conexion = new conexion();
        List<usuario> ListaUsuario = new ArrayList<>();
        String sql = "SELECT * FROM usuario ORDER BY idUsuario asc";
        ResultSet rs = conexion.consultarDB(sql);
        usuario u;
        while (rs.next()) {
            u = new usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setIdRol(rs.getInt("idRol"));
            u.setusuario(rs.getString("usuario"));
            u.setContrasena(rs.getString("contraseña"));
            u.setUltimoIngreso(rs.getDate("ultimoIngreso"));
        }
        conexion.cerrarConexion();
        return ListaUsuario;
        
    }
    public usuario getUsuario() throws SQLException{
        conexion conexion = new conexion();
        String sql = "select * from usuario where idUsuario='"+this.idUsuario+"'";
        ResultSet rs =conexion.consultarDB(sql);
        if(rs.next()){
            this.idUsuario = rs.getInt("idUsuario");
            this.idRol = rs.getInt("idRol");
            this.usuario = rs.getString("usuario");
            this.contrasena = rs.getString("contraseña");
            this.ultimoIngreso = rs.getDate("ultimoIngresp");
            conexion.cerrarConexion();
            return this;
            
            
        }else{
            conexion.cerrarConexion();
            return null;
            
        }
    }

    @Override
    public String toString() {
        return "usuario["+"idUsuario="+idUsuario+",idRol="+idRol+", usuario="+usuario+", contraseña="+contrasena+", ultimoIngreso="+ultimoIngreso+'}'; 
    }
    
    

}
