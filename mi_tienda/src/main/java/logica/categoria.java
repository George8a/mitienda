
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
    
    public boolean actualizarcategoria() {
        Conexion conexion = new Conexion();
        String Sentencia = "UPDATE `categorias` SET nombre='" + this.nombre + "',descripcion='" + this.descripcion + "' WHERE id_categoria=" + this.id_categoria + ";";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
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
    
    public List<categoria> listarcategorias() throws SQLException {
        Conexion conexion = new Conexion();
        List<categoria> listacategorias = new ArrayList<>();
        String sql = "select * from categorias order by identificacion asc";
        ResultSet rs = conexion.consultarBD(sql);
        categoria c;
        while (rs.next()) {
            c = new categoria();
            c.setId_categoria(rs.getInt("id_categoria"));
            c.setnombre(rs.getString("nombre"));
            c.setdescripcion(rs.getString("descripcion"));
            listacategorias.add(c);

        }
        conexion.cerrarConexion();
        return listacategorias;
    }
    
    public categoria getcategoria() throws SQLException {
        Conexion conexion = new Conexion();
        String sql = "select * from categorias where id_categoria='" + this.id_categoria + "'";
        ResultSet rs = conexion.consultarBD(sql);
        if (rs.next()) {
            this.id_categoria = rs.getInt("id_categoria");
            this.nombre = rs.getString("nombre");
            this.descripcion = rs.getString("descripcion");
            conexion.cerrarConexion();
            return this;

        } else {
            conexion.cerrarConexion();
            return null;
        }

    }

    private void setnombre(String string) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void setdescripcion(String string) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}
