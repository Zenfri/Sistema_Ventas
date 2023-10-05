package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpleadoDAO {

    //Instanciamos a la clase Conexion
    private Conexion conexion = new Conexion();

    //Atributos para acceder a la DB:
    private Connection con; //Proporciona métodos para conectarnos a la DB
    private PreparedStatement ps; //Sentencia preparada, almacena sentencia preparada que se ejeuctará y evita inyecciones SQL
    private ResultSet rs; //Almacena resultados de la consulta
    private Logger logger = Logger.getLogger(getClass().getName());

    //método para validar los parámetros de envío
    public Empleado validar(String testUser, String testPass) {
        Empleado empleado = new Empleado();

        //Hacemos la consulta contraseña y dni (password), aquí con el where haremos la validación
        String sql = "select * from Empleado where usuario=? and clave=?"; //?: marcadores de posicion, estos parámetros serán 
        // proporcionados luego, esto para evitar cololar el testUser y testPass directamente directamente en la consulta
        //por un riesgo de seguridad conocido como inyeccion SQL
        try {
            con = conexion.conectar(); //nos conectamos con la DB

            //Asignamos valores a los marcadores de posicion:
            ps = con.prepareStatement(sql);
            ps.setString(1, testUser);
            ps.setString(2, testPass);

            rs = ps.executeQuery(); //ejecuta consulta select (hacemos la validación) y almacena el resultado

            //bucle que nos permitirá recorrer los registros uno por uno y realizar operaciones especificas en cada una de ellas
            while (rs.next()) {
                //obtenemos los valores de las columnas respectivamente y lo guardamos en el objeto Empleado
                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setDni(rs.getString("dni"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setEstado(rs.getByte("estado")); //estado es de tipo bit (0=inactivo o 1=activo)
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setClave(rs.getString("clave"));
            }
        } catch (Exception e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        finally{
            conexion.desconectar(rs, ps, con);
        }
        return empleado;
    }

    //CRUD
    //Devolverá la lista de empleados que tenemos en la base de datos.
    public ArrayList<Empleado> lista() {
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "select * from Empleado";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado objEmpleado = new Empleado();
                objEmpleado.setIdEmpleado(rs.getInt(1));
                objEmpleado.setDni(rs.getString(2));
                objEmpleado.setNombre(rs.getString(3));
                objEmpleado.setTelefono(rs.getString(4));
                objEmpleado.setEstado(rs.getByte(5));
                objEmpleado.setUsuario(rs.getString(6));
                objEmpleado.setClave(rs.getString(7));

                listaEmpleados.add(objEmpleado);
                logger.info("Se logró listar a los empleados exitósamente");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL para listar los empleados"+e);
        }
        finally{
            conexion.desconectar(rs, ps, con);
        }
        return listaEmpleados;
    }

    public void agregar(Empleado objEmpleado) {
        String sql = "insert into Empleado (dni,nombre,telefono,estado,usuario,clave) values (?,?,?,?,?,?)";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, objEmpleado.getDni());
            ps.setString(2, objEmpleado.getNombre());
            ps.setString(3, objEmpleado.getTelefono());
            ps.setByte(4, objEmpleado.getEstado());
            ps.setString(5, objEmpleado.getUsuario());
            ps.setString(6, objEmpleado.getClave());
            ps.executeUpdate(); //para insertar datos, aqui no se usa rs porque no se devuelve un conjunto de resultados
            //como sí pasaría en un select
            logger.info("Se logró agregar al empleado exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL para agregar al empleado"+e);
        }
        finally{
            conexion.desconectar(rs, ps, con);
        }
    }

    //Devolverá el empleado que tenga el mismo id que el pasado por parámetro, caso contrario retorna null
    public Empleado buscarPorId(int id) {
        String sql = "select * from Empleado where id_empleado=?";
        Empleado empleado = null;

        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                empleado = new Empleado(id, rs.getString(2), rs.getString(3), rs.getString(4), //getString(NUM_COLUMNA)
                        rs.getByte(5), rs.getString(6), rs.getString(7));
            }
            logger.info("Se logró encontrar el id del empleado exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL para encontrar el id del empleado"+e);
        }
        finally{
            conexion.desconectar(rs, ps, con);
        }
        return empleado;
    }
    
    
    //Actualizará los datos de un Empleado por los del nuevoEmpleado que se pasa por parámetro
    public void actualizar(Empleado nuevoEmpleado) {
        String sql = "update Empleado set dni=?, nombre=?, telefono=?, estado=?, usuario=?, clave=? where id_empleado=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nuevoEmpleado.getDni());
            ps.setString(2, nuevoEmpleado.getNombre());
            ps.setString(3, nuevoEmpleado.getTelefono());
            ps.setByte(4, nuevoEmpleado.getEstado());
            ps.setString(5, nuevoEmpleado.getUsuario());
            ps.setString(6, nuevoEmpleado.getClave());
            ps.setInt(7, nuevoEmpleado.getIdEmpleado());
            ps.executeUpdate();
            logger.info("Se logró actualizar al empleado exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL para actualizar al empleado"+e);
        }
        finally{
            conexion.desconectar(rs, ps, con);
        }
    }

    public void eliminar(int id){
        String sql = "delete from Empleado where id_empleado=?";
        try {
            con = conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Se logró eliminar al empleado exitósamente");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Error al ejecutar la consulta SQL para eliminar al empleado"+e);
        }
        finally{
            conexion.desconectar(rs, ps, con);
        }
    }
}
