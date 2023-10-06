/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import config.Serie;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;

public class Controlador extends HttpServlet {

    private Empleado objEmpleado;
    private Producto objProducto;
    private Cliente objCliente;
    private EmpleadoDAO objEmpleadoDAO;
    private ProductoDAO objProductoDAO = new ProductoDAO();
    private ClienteDAO objClienteDAO = new ClienteDAO();

    //atributos para agregar a la tabla NuevaVenta.jsp
    private ArrayList<Venta> lVentas = new ArrayList<>();
    private int item = 0;
    private BigDecimal monto = BigDecimal.ZERO;

    //Para generar la serie
    private VentaDAO oVentaDAO = new VentaDAO();
    private String serie_final = "";

    //Para la sesión del empleado
    Empleado oEmpleadoSesion = new Empleado();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String menu = request.getParameter("menu");
        String opcion = request.getParameter("opcion");

        //recibir información de la sesión
        HttpSession sesion = request.getSession();
        int idEmpleadoSesion = (int) sesion.getAttribute("id_empleado_sesion");//almacenamos el id del empleado que inició sesión

        switch (menu) {
            case "Principal":
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
                break;

            case "Producto":
                if (opcion.equals("Listar")) {
                    ArrayList<Producto> listaProductos = new ArrayList<>();
                    listaProductos = objProductoDAO.listar();
                    request.setAttribute("lProductos", listaProductos);

                    request.getRequestDispatcher("Producto.jsp").forward(request, response);
                    break;
                } else if (opcion.equals("Agregar")) {
                    String precio = request.getParameter("txtPrecio");
                    BigDecimal precioBD = new BigDecimal(precio);

                    objProducto = new Producto(request.getParameter("txtNombre"),
                            precioBD,
                            Integer.parseInt(request.getParameter("txtStock")),
                            Byte.parseByte(request.getParameter("txtEstado")));
                    objProductoDAO.insertar(objProducto);
                    request.getRequestDispatcher("Controlador?menu=Producto&opcion=Listar").forward(request, response);
                    break;
                } else if (opcion.equals("Editar")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    objProducto = new Producto();
                    objProducto = objProductoDAO.buscar(id);
                    if (objProducto != null) {
                        request.setAttribute("oProducto", objProducto);
                        request.getRequestDispatcher("Controlador?menu=Producto&opcion=Listar").forward(request, response);
                        break;
                    }
                } else if (opcion.equals("Actualizar")) {
                    if (!request.getParameter("txtId").isEmpty()) {

                        String precio = request.getParameter("txtPrecio");
                        BigDecimal precioBD = new BigDecimal(precio);

                        Producto nuevoProducto = new Producto(
                                Integer.parseInt(request.getParameter("txtId")),
                                request.getParameter("txtNombre"),
                                precioBD,
                                Integer.parseInt(request.getParameter("txtStock")),
                                Byte.parseByte(request.getParameter("txtEstado")));
                        objProductoDAO.actualizar(nuevoProducto);
                    }
                    request.getRequestDispatcher("Controlador?menu=Producto&opcion=Listar").forward(request, response);
                    break;
                } else if (opcion.equals("Eliminar")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    objProductoDAO.eliminar(id);
                    request.getRequestDispatcher("Controlador?menu=Producto&opcion=Listar").forward(request, response);
                    break;
                }

            case "Empleado":
                //Enviaremos lista a la página Empleado
                if (opcion.equals("Listar")) {
                    objEmpleadoDAO = new EmpleadoDAO();
                    ArrayList<Empleado> lEmpleados = new ArrayList<>();
                    lEmpleados = objEmpleadoDAO.lista();
                    request.setAttribute("listaEmpleados", lEmpleados);

                    request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                    break;
                } else if (opcion.equals("Agregar")) {
                    //obtenemos los datos enviados del formulario nuevo Empleado
                    String dni = request.getParameter("txtDni");
                    String nombre = request.getParameter("txtNombre");
                    String telefono = request.getParameter("txtTelefono");
                    Byte estado = Byte.parseByte(request.getParameter("txtEstado"));
                    String usuario = request.getParameter("txtUsuario");
                    String clave = request.getParameter("txtClave");

                    objEmpleado = new Empleado();
                    objEmpleado.setDni(dni);
                    objEmpleado.setNombre(nombre);
                    objEmpleado.setTelefono(telefono);
                    objEmpleado.setEstado(estado);
                    objEmpleado.setUsuario(usuario);
                    objEmpleado.setClave(clave);

                    objEmpleadoDAO = new EmpleadoDAO();
                    objEmpleadoDAO.agregar(objEmpleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&opcion=Listar").forward(request, response);
                    break;
                } else if (opcion.equals("Editar")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    objEmpleado = new Empleado();
                    objEmpleado = objEmpleadoDAO.buscarPorId(id); //guardamos los datos del Empleado de la fila a editar en objEmpleado

                    //enviamos los datos del Empleado al Controlador con la opción Listar que luego se los enviará al Empleado.jsp
                    request.setAttribute("empleado", objEmpleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&opcion=Listar").forward(request, response);
                    break;
                } else if (opcion.equals("Actualizar")) {
                    //si existe un valor en el txtId (por defecto no tiene ninguno, solo tiene un placeholder) diferente de ""
                    if (!request.getParameter("txtId").equals("")) {
                        int id = Integer.parseInt(request.getParameter("txtId"));

                        //Obtenemos los nuevos valores que serán ingresados en los textbox
                        Empleado nuevoEmpleado = new Empleado(id, request.getParameter("txtDni"), request.getParameter("txtNombre"),
                                request.getParameter("txtTelefono"), Byte.parseByte(request.getParameter("txtEstado")),
                                request.getParameter("txtUsuario"), request.getParameter("txtClave"));
                        objEmpleadoDAO.actualizar(nuevoEmpleado);
                    }
                    request.getRequestDispatcher("Controlador?menu=Empleado&opcion=Listar").forward(request, response);
                    break;
                } else if (opcion.equals("Eliminar")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    objEmpleadoDAO.eliminar(id);
                    request.getRequestDispatcher("Controlador?menu=Empleado&opcion=Listar").forward(request, response);
                    break;
                }
            case "Cliente":
                if (opcion.equals("Listar")) {
                    ArrayList<Cliente> listaClientes = new ArrayList<>();
                    listaClientes = objClienteDAO.listar();
                    request.setAttribute("lClientes", listaClientes);

                    request.getRequestDispatcher("Cliente.jsp").forward(request, response);
                    break;
                } else if (opcion.equals("Agregar")) {
                    objCliente = new Cliente(request.getParameter("txtDni"),
                            request.getParameter("txtNombre"),
                            request.getParameter("txtDireccion"),
                            Byte.parseByte(request.getParameter("txtEstado")));
                    objClienteDAO.insertar(objCliente);
                    request.getRequestDispatcher("Controlador?menu=Cliente&opcion=Listar").forward(request, response);
                    break;
                } else if (opcion.equals("Editar")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    objCliente = new Cliente();
                    objCliente = objClienteDAO.buscar(id);
                    if (objCliente != null) {
                        request.setAttribute("oCliente", objCliente);
                        request.getRequestDispatcher("Controlador?menu=Cliente&opcion=Listar").forward(request, response);
                        break;
                    }
                } else if (opcion.equals("Actualizar")) {
                    if (!request.getParameter("txtId").equals("")) {
                        Cliente nuevoCliente = new Cliente(
                                Integer.parseInt(request.getParameter("txtId")),
                                request.getParameter("txtDni"),
                                request.getParameter("txtNombre"),
                                request.getParameter("txtDireccion"),
                                Byte.parseByte(request.getParameter("txtEstado")));
                        objClienteDAO.actualizar(nuevoCliente);
                    }
                    request.getRequestDispatcher("Controlador?menu=Cliente&opcion=Listar").forward(request, response);
                    break;
                } else if (opcion.equals("Eliminar")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    objClienteDAO.eliminar(id);
                    request.getRequestDispatcher("Controlador?menu=Cliente&opcion=Listar").forward(request, response);
                    break;
                }

            case "NuevaVenta":
                if (opcion.equals("Listar")) {
                    request.getRequestDispatcher("NuevaVenta.jsp").forward(request, response);
                    break;
                } else if (opcion.equals("buscarCliente")) {
                    if (!request.getParameter("txtDniCliente").equals("")) {
                        String dni = request.getParameter("txtDniCliente");
                        objCliente = new Cliente();
                        objCliente = objClienteDAO.buscarPorDni(dni);
                        if (objCliente != null) {
                            if (!request.getParameter("txtIdProducto").equals("")) {
                                int id = Integer.parseInt(request.getParameter("txtIdProducto"));
                                objProducto = new Producto();
                                objProducto = objProductoDAO.buscar(id);
                                if (objProducto != null) {
                                    request.setAttribute("oProducto", objProducto);
                                }
                            }
                            //vaciamos la lista porque si se cambia de cliente, se empezará desde cero
                            lVentas = new ArrayList<Venta>();
                            request.setAttribute("oCliente", objCliente);
                        }
                    }
                } else if (opcion.equals("buscarProducto")) {
                    if (!request.getParameter("txtIdProducto").isEmpty()) {
                        int id = Integer.parseInt(request.getParameter("txtIdProducto"));
                        objProducto = new Producto();
                        objProducto = objProductoDAO.buscar(id);
                        if (objProducto != null) {
                            if (!request.getParameter("txtDniCliente").equals("")) {
                                String dni = request.getParameter("txtDniCliente");
                                objCliente = new Cliente();
                                objCliente = objClienteDAO.buscarPorDni(dni);
                                if (objCliente != null) {
                                    request.setAttribute("oCliente", objCliente);
                                }
                            }
                            request.setAttribute("oProducto", objProducto);
                        }
                    }
                    //enviar monto
                    monto = monto.setScale(2, RoundingMode.HALF_UP);
                    request.setAttribute("monto", monto);
                    //enviar cliente seleccionado
                    request.setAttribute("oCliente", objCliente);
                    //enviar lista de ventas
                    request.setAttribute("lVentas", lVentas);

                } else if (opcion.equals("agregar")) {
                    //validar ingreso de número
                    if (!request.getParameter("txtIdProducto").isEmpty() && objCliente != null) {
                        //buscar id del producto
                        int idProducto = Integer.parseInt(request.getParameter("txtIdProducto"));
                        objProducto = objProductoDAO.buscar(idProducto);

                        //validar detección de id del producto
                        if (objProducto != null) {
                            //agregar a la lista los parámetros del cliente y del producto
                            int cantidad = 0;
                            BigDecimal precio = BigDecimal.ZERO;
                            BigDecimal sub_total = BigDecimal.ZERO;
                            String nombre = objProducto.getNombre();
                            precio = objProducto.getPrecio();
                            cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                            sub_total = precio.multiply(new BigDecimal(cantidad));

                            //buscar idproducto para que no se agregue si se repite
                            boolean bandera = true;
                            for (Venta venta : lVentas) {
                                if (venta.getId_producto() == idProducto) {
                                    bandera = false;
                                }
                            }
                            if (bandera) { //si el producto no se repite: 
                                item++; //incrementa su num_orden
                                //agregando a la lista
                                lVentas.add(new Venta(item, idProducto, nombre,
                                        precio, cantidad, sub_total));
                            }
                        }
                    }
                    //acumulando el monto
                    monto = BigDecimal.ZERO;
                    for (Venta lVenta : lVentas) {
                        monto = monto.add(lVenta.getSubtotal());
                    }
                    monto = monto.setScale(2, RoundingMode.HALF_UP); //aprox a 2 decimales al número más cercano
                    request.setAttribute("monto", monto); //enviar monto

                    //mantener datos del cliente
                    if (!request.getParameter("txtDniCliente").isEmpty()) {
                        String dni = request.getParameter("txtDniCliente");
                        objCliente = new Cliente();
                        objCliente = objClienteDAO.buscarPorDni(dni);
                        if (objCliente != null) {
                            request.setAttribute("oCliente", objCliente);
                        }
                    }
                    request.setAttribute("lVentas", lVentas);//enviar lista a la Vista

                } else if (opcion.equals("GenerarVenta")) {
                    if (!lVentas.isEmpty() && objCliente != null) {
                        //preparando atributos para insertar Venta
                        Venta oVenta = new Venta();
                        oVenta.setId_empleado(idEmpleadoSesion);//si no existe el empleado no se ejecutará la consulta satisfactoriamente InnoD
                        oVenta.setId_cliente(objCliente.getId_cliente());
                        oVenta.setNum_serie(serie_final);
                        oVenta.setMonto(monto);
                        oVenta.setEstado(Byte.parseByte("1"));

                        //insertando Venta
                        oVentaDAO.insertarVenta(oVenta);

                        //Luego de insertar la Venta, tendremos su Id por defecto, pero para obtenerlo y registrarlo en DetalleVenta:
                        //Buscando id de Venta (debe ser del último registrado)
                        int idVenta = oVentaDAO.buscarUltimaIdVenta();

                        //Insertar DetalleVenta, como son varias filas necesitamos un bucle:
                        Venta oDetalleVenta;
                        for (int i = 0; i < lVentas.size(); i++) {
                            oDetalleVenta = new Venta();
                            oDetalleVenta.setId_producto(lVentas.get(i).getId_producto());
                            oDetalleVenta.setId_venta(idVenta);
                            oDetalleVenta.setCantidad(lVentas.get(i).getCantidad());
                            oDetalleVenta.setPrecio(lVentas.get(i).getPrecio()); //este precio queda en el historial, aunque se cambie luego el 
                            //precio del producto, este precio en el DetalleVenta se mantendrá igual a como fue registrado antes

                            //insertando DetalleVenta
                            oVentaDAO.insertarDetalleVenta(oDetalleVenta);
                        }

                        //Actualizar stock
                        Producto oProductoStock;
                        int nuevoStock = 0;
                        for (int i = 0; i < lVentas.size(); i++) {
                            oProductoStock = new Producto();
                            oProductoStock = objProductoDAO.buscar(lVentas.get(i).getId_producto()); //obtenemos el Producto de la Fila
                            nuevoStock = oProductoStock.getStock() - lVentas.get(i).getCantidad(); //reducimos su stock de acuerdo a la cantidad en la fila
                            objProductoDAO.actualizarStock(lVentas.get(i).getId_producto(), nuevoStock); //actualizamos el stock del Producto
                        }

                        //limpiando datos
                        monto = BigDecimal.ZERO;
                        lVentas.clear();
                        objCliente = null; //para generar luego otra venta requiere que se busque nuevamente un cliente
                    }

                } else if (opcion.equals("EliminarFila")) {
                    int id_prod = Integer.parseInt(request.getParameter("id"));
                    int i = 0;
                    boolean encontrar = false;
                    while (i < lVentas.size() && !encontrar) { //mientras no se encuentre id del producto a eliminar
                        if (id_prod == lVentas.get(i).getId_producto()) { //si se en cuentra dicho id
                            lVentas.remove(i);//Eliminar elemento en dicha posición
                            for (int j = i; j < lVentas.size(); j++) { //todos los siguientes indices actualizan su num_item
                                lVentas.get(j).setNum_item(j + 1);  //con el valor de la posición + 1 (ya que la pos siempre empieza desde 0)
                            }
                            item--; //ya que este se recibe como parámetro Num_item(item) y así se agregue correctamente y actualizadamente en la tabla
                            encontrar = true;
                        }
                        i++; //este while actúa como un for, pero cuando encuentra el producto se sale de la condición while
                    }
                    //calculando nuevamente el monto
                    monto = BigDecimal.ZERO;
                    for (Venta lVenta : lVentas) {
                        monto = monto.add(lVenta.getSubtotal());
                    }
                    monto = monto.setScale(2, RoundingMode.HALF_UP);
                    request.setAttribute("monto", monto);

                    //enviando nueva lista
                    request.setAttribute("lVentas", lVentas); //enviar nueva lista actualizada
                } else if (opcion.equals("CancelarVenta")) {
                    objCliente = null;
                    lVentas = new ArrayList<Venta>();
                    item = 0;
                    monto = BigDecimal.ZERO;
                }

                //TODO ESTO SIEMPRE SE EJECUTARÁ en el caso NuevaVenta.jsp
                request.setAttribute("lVentas", lVentas);
                
                
                //reiniciando parámetros
                if (lVentas.isEmpty()) {
                    item = 0;//si la lista está vacía, que reinicie el num de orden
                }

                //GENERANDO NUM DE SERIE SIGUIENTE
                String max_serie = oVentaDAO.devolverMaxSerie();
                if (max_serie != null) { //si la tabla Venta no tiene una columna con num_serie vacío (si existe un registro)
                    Serie oSerie = new Serie();
                    serie_final = oSerie.generarSiguienteSerie(max_serie);
                } else { //generar por defecto el primer num_serie
                    serie_final = "N000000001";
                }
                request.setAttribute("serie", serie_final);
                
                //redirigir solicitud a la página
                request.getRequestDispatcher("NuevaVenta.jsp?menu=NuevaVenta&opcion=Listar").forward(request, response);
                break;

            default:
                System.out.println("Error");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
