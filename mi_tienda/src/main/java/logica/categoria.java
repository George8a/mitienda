
package logica;

    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    import persistencia.Conexion;

public class categoria {
    
    private int id_categoria;
    private String nombre;
    private String descripcion;
    
    public categoria getcategoria(int id_categoria) throws SQLException {
        this.id_categoria = id_categoria;
        return this.getcategoria();
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean guardarcategoria() {
        Conexion conexion = new Conexion();
        
        String sentencia = "INSERT INTO categorias(id_categoria, nombre, descripcion) "
                + " VALUES ( '" + this.id_categoria + "','" + this.nombre + "',"
                + "'" + this.descripcion+ " ";
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

    private categoria getcategoria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
