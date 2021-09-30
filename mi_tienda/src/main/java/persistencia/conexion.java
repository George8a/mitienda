/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George
 */
public class conexion {

    private String driver = "";
    private String url = "";
    private String db = "";
    private String host = "";
    private String user = "";
    private String pass = "";
    public Connection con = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private boolean local;

    public conexion() {
        local = true;
        driver = "com.mysql.jdbc.Driver";
        if (local) {
            host = "localhost:3306";
            db = "";
            url = "jdbc:mysql://" + host + "/" + db;
            user = "root";
            pass = "";

        } else {
            host = "localhost:3306";
            db = "";
            url = "jdbc:mysql://" + host + "/" + db;
            user = "root";
            pass = "";

        }
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con = DriverManager.getConnection(url, user, pass);
            con.setTransactionIsolation(8);
            System.out.println("Conexion se ha establecido con exito :D");

        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public boolean insertarBD(String sentencia) {
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
        } catch (SQLException | RuntimeException sqlex) {
            System.out.println("ERROR RUTINA: " + sqlex);
            return false;
        }
        return true;
    }


    public Connection getConnection() {
        return con;
    }

    public void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet consultarDB(String sentencia){
        try{
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sentencia);
        }catch(SQLException sqlex){            
        }catch (RuntimeException rex){
        }catch(Exception ex){
        }
        return rs;
    }
    public boolean borrarBD(String sentencia){
        try{
            stmt = con.createStatement();
            stmt.execute(sentencia);
        }catch(SQLException | RuntimeException sqlex){
            System.out.println("ERROR EN RUTINA:"+sqlex);
            return false;
            
        }
        return true;
        
    }
    public boolean actualizarBD(String sentencia){
        try{
            stmt = con.createStatement();
            stmt.executeQuery(sentencia);
            
        }catch(SQLException | RuntimeException sqlex){
            System.out.println("ERROR EN RUTINA: "+sqlex);
            return false;
        }
        return true;
        
    }
    public boolean setAutoCommitBD(boolean parametro){
        try{
            con.setAutoCommit(parametro);
            
        }catch(SQLException sqlex){
            System.out.println("Error al configurar el autoCommit "+ sqlex.getMessage());
            return false;
        }
        return true;
    }
     public void cerrarConexion() {
        closeConnection(con);
    }
public boolean commitBD(){
    try{
        con.commit();
        return true;
    }catch(SQLException sqlex){
        System.out.println("Error al hacer commit "+sqlex.getMessage());
        return false;
    }
}
public boolean rollbackBD(){
    try{
        con.rollback();
        return true;
    }catch (SQLException sqlex){
        System.out.println("Error al hacer rollback "+sqlex.getMessage());
        return false;
    }
}
public static void main (String[] args){
    conexion b = new conexion();
    b.cerrarConexion();
}
    
}
