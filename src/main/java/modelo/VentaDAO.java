package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentaDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion = new Conexion();
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
    //devuelve el mayor léxico de la columna num_serie de la tabla Venta
    public String devolverMaxSerie(){
        String maxSerie = null;
        String sql = "select max(num_serie) from Venta"; //devuelve un solo dato, por ello es una única columna
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                maxSerie = rs.getString(1);
            }
            logger.info("Se logró ejecutar la consulta de búsqueda de máximo número de serie en la tabla Venta");
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL para obtener el max(num_serie) de la tabla Venta"+e);
        }
        finally{
            conexion.desconectar(rs, ps, con);
        }
        return maxSerie;
    }
    
    //CRUD
    
    //Insertar venta 
    public void insertarVenta(Venta oVenta){
        String sql = "insert into Venta (id_empleado,id_cliente,num_serie,fecha,monto,estado) values (?,?,?,curdate(),?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, oVenta.getId_empleado());
            ps.setInt(2, oVenta.getId_cliente());
            ps.setString(3, oVenta.getNum_serie());
            //curdate() -> en sql devuelve la FECHA pactual
            ps.setBigDecimal(4, oVenta.getMonto());
            ps.setByte(5, oVenta.getEstado());
            ps.executeUpdate();
            logger.info("Se logró ejecutar la consulta SQL exitósamente para insertar nueva Venta");
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al insertar nueva Venta"+e);
        }
        finally{
            conexion.desconectar(rs, ps, con);
        }
    } 
    
    public void insertarDetalleVenta(Venta oVenta){
        String sql = "insert into DetalleVenta (id_producto,id_venta,cantidad,precio_unitario) values (?,?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, oVenta.getId_producto());
            ps.setInt(2, oVenta.getId_venta());
            ps.setInt(3, oVenta.getCantidad());
            ps.setBigDecimal(4, oVenta.getPrecio());
            ps.executeUpdate();
            logger.info("Se logró ejecutar la consulta SQL exitósamente para insertar DetalleVenta");
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al insertar DetalleVenta"+e);
        }
        finally{
            conexion.desconectar(rs, ps, con);
        }
    }
    
    public int buscarUltimaIdVenta(){
        int id = 0;
        String sql = "select max(id_venta) from Venta";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            logger.info("Se logró ejecutar la consulta SQL exitósamente al encontrar Max (id_venta) de la tabla Venta");
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al buscar Max (id_venta) de la tabla Venta"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        }
        return id;
    }
}
