package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion = new Conexion();
    private Logger logger = Logger.getLogger(getClass().getName());

    public ArrayList<Producto> listar() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        String sql = "select * from Producto";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listaProductos.add(new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getByte(5)));
            }
            logger.info("Se logró listar los productos exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL para listar los productos"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        }
        return listaProductos;
    }
    
    public void insertar(Producto oProducto){
        String sql = "insert into Producto (nombre, precio, stock, estado)values (?,?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, oProducto.getNombre());
            ps.setDouble(2, oProducto.getPrecio());
            ps.setInt(3, oProducto.getStock());
            ps.setByte(4, oProducto.getEstado());
            ps.executeUpdate();
            logger.info("Se logró insertar el producto exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL insertar los productos"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        } 
    }
    
    public Producto buscar(int id){
        Producto oProducto = null;
        String sql = "select * from Producto where id_producto=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                oProducto = new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getByte(5));
            }
            logger.info("Se logró encontrar el producto exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al buscar el producto"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        }
        return oProducto;
    }
    
    public void actualizar(Producto nuevoProducto){
        String sql = "update Producto set nombre=?, precio=?, stock=?, estado=? where id_producto=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nuevoProducto.getNombre());
            ps.setDouble(2, nuevoProducto.getPrecio());
            ps.setInt(3, nuevoProducto.getStock());
            ps.setByte(4, nuevoProducto.getEstado());
            ps.setInt(5, nuevoProducto.getId_producto());
            ps.executeUpdate();
            logger.info("Se logró actualizar el producto exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al intentar actualizar el producto"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        } 
    }
    public void eliminar(int id){
        String sql = "delete from Producto where id_producto=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Se logró eliminar el producto exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al intentar eliminar el producto"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        } 
    }
    
    //le pasaremos el id del producto y su nuevo stock (depende de la cantidad en la tabla de Nueva Venta)
    public void actualizarStock(int id, int nuevo_stock){
        String sql = "update Producto set stock = ? where id_producto=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nuevo_stock);
            ps.setInt(2, id);
            ps.executeUpdate();
            logger.info("Se logró actualizar el stock del producto exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al intentar actualizar el stock del producto"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        } 
    }
}
