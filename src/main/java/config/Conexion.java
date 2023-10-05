
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private Connection con ;//La clase conexión permite establecer una conexión, hacer consultas, administrar transacciones
    private static final String nombreBD = "sistema_ventas_0"; //nombre de la base de datos
    //private String driver =  "com.mysql.jdbc.Driver"; //dirección del driver [no es necesario desde JDBC 4.0]
    private static final String URL = "jdbc:mysql://localhost:3306/"+nombreBD; //URL de conexión para conectarse a una BD de MySQL
    private static final String USER = "root"; //usuario por defecto
    private static final String PASSWORD = "";
    private Logger logger = Logger.getLogger(getClass().getName());
    
    public Conexion(){
    }
    //Método para conectar a la DB
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver"); //carga dinámicamente la clase controlador JDBC de mysql 
            con = DriverManager.getConnection(URL,USER,PASSWORD);
            if(con != null){
                logger.info("Se logró conectar a la DB exitósamente");
            }
            
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al intentar conectarse a la DB",e);
        }
        return con;
    }
    
    //Método para desconectarse de la DB
    public void desconectar(ResultSet rs, PreparedStatement ps, Connection con){
        try {
            if(rs != null){
                rs.close();
            }
            if(ps != null){
                ps.close();
            }
            if(con != null){
                con.close();
            }
            logger.info("Recursos cerrados exitosamente");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE,"Error al cerrar los recursos JDBC",ex);
        }
    }
    
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.conectar();
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    
}
