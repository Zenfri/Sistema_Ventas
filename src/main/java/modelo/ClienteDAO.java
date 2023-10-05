package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion = new Conexion();
    private Logger logger = Logger.getLogger(getClass().getName());
    
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "select * from Cliente";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listaClientes.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getByte(5)));
            }
            logger.info("Se logró listar a los Clientes exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL para listar los Clientes"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        }
        return listaClientes;
    }
    
    public void insertar(Cliente oCliente){
        String sql = "insert into Cliente (dni, nombre, direccion, estado)values (?,?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, oCliente.getDni());
            ps.setString(2, oCliente.getNombre());
            ps.setString(3, oCliente.getDireccion());
            ps.setByte(4, oCliente.getEstado());
            ps.executeUpdate();
            logger.info("Se logró insertar al Cliente exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al insertar el Cliente"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        } 
    }
    
    public Cliente buscar(int id){
        Cliente oCliente = null;
        String sql = "select * from Cliente where id_cliente=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {                
                oCliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getByte(5));
            }
            logger.info("Se logró encontrar al Cliente exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al buscar el Cliente"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        }
        return oCliente;
    }
    
    public Cliente buscarPorDni(String dni){
        Cliente oCliente = null;
        String sql = "select * from Cliente where dni=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            while (rs.next()) {                
                oCliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getByte(5));
            }
            logger.info("Se logró encontrar con el dni al Cliente exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al buscar el Cliente por dni"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        }
        return oCliente;
    }
    
    public void actualizar(Cliente nuevoCliente){
        String sql = "update Cliente set dni=?, nombre=?, direccion=?, estado=? where id_cliente=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nuevoCliente.getDni());
            ps.setString(2, nuevoCliente.getNombre());
            ps.setString(3, nuevoCliente.getDireccion());
            ps.setByte(4, nuevoCliente.getEstado());
            ps.setInt(5, nuevoCliente.getId_cliente());
            ps.executeUpdate();
            logger.info("Se logró actualizar al Cliente exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al intentar actualizar al Cliente"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        } 
    }
    public void eliminar(int id){
        String sql = "delete from Cliente where id_cliente=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Se logró eliminar al Cliente exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL al intentar eliminar al Cliente"+e);
        }finally{
            conexion.desconectar(rs, ps, con);
        } 
    }
}
