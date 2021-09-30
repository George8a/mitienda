
package logica;

    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    import persistencia.conexion;
 

public class producto {
    
    private int id_producto;
    private String id_categoria;
    private String nombre;
    private double precio;
    private int stock;
    
    public producto(){
    }
    
    public producto getproducto(int id_producto) throws SQLException {
        this.id_producto = id_producto;
        return this.getproducto();
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public boolean guardarproducto() {
        conexion conexion = new conexion();
        
        String sentencia = "INSERT INTO productos(id_productos, id_categoria, nombre, precio, stock) "
                + " VALUES ( '" + this.id_producto + "','" + this.id_categoria + "',"
                + "'" + this.nombre + "','" + this.precio + "','" + this.stock +  "');  ";
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
    public boolean borrarproducto(int id_producto) {
        String Sentencia = "DELETE FROM `productos` WHERE `id_producto`='" + id_producto + "'";
        conexion conexion = new conexion();
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
    public boolean actualizarproducto() {
        conexion conexion = new conexion();
        String Sentencia = "UPDATE `productos` SET nombre='" + this.nombre + "',id_categoria='" + this.id_categoria + "',precio='" + this.precio
                + "',stock='" + this.stock +  "' WHERE id_producto=" + this.id_producto + ";";
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
    public List<producto> listarproductos() throws SQLException {
        conexion conexion = new conexion();
        List<producto> listaproductos = new ArrayList<>();
        String sql = "select * from productos order by identificacion asc";
        ResultSet rs = conexion.consultarDB(sql);
        producto p;
        while (rs.next()) {
            p = new producto();
            p.setId_producto(rs.getInt("id_producto"));
            p.setId_categoria(rs.getString("id_categoria"));
            p.setNombre(rs.getString("nombre"));
            p.setPrecio(rs.getDouble("precio"));
            p.setStock(rs.getInt("stock"));
            listaproductos.add(p);

        }
        conexion.cerrarConexion();
        return listaproductos;
    }
    public producto getproducto() throws SQLException {
        conexion conexion = new conexion();
        String sql = "select * from productos where id_producto='" + this.id_producto + "'";
        ResultSet rs = conexion.consultarDB(sql);
        if (rs.next()) {
            this.id_producto = rs.getInt("id_producto");
            this.id_categoria = rs.getString("id_categoria");
            this.nombre = rs.getString("nombre");
            this.precio = rs.getDouble("precio");
            this.stock = rs.getInt("stock");
            conexion.cerrarConexion();
            return this;

        } else {
            conexion.cerrarConexion();
            return null;
        }

    }
    
    @Override
    public String toString() {
        return "Contacto{" + "id_producto=" + id_producto + ", id_categoria=" + id_categoria + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + '}';
    }
}