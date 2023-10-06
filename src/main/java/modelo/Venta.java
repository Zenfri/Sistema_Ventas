package modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Venta {
    private int id_venta;
    private int id_empleado;
    private int id_cliente;
    private int id_producto;
    
    private String num_serie;
    private int num_item; //número de la fila de la tabla o de cada nuevo registro de producto en NuevaVenta, o num de orden
    private Date fecha;
    private String descripcion; //nombre del producto
    private BigDecimal precio;
    private int cantidad;
    private BigDecimal subtotal;
    private BigDecimal monto;
    private byte estado;

    public Venta() {
    }

    public Venta(int id_venta) {
        this.id_venta = id_venta;
    }

    //para la tabla
    public Venta( int num_item, int id_producto,  String descripcion, BigDecimal precio, int cantidad, BigDecimal subtotal) {
        this.id_producto = id_producto;
        this.num_item = num_item;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    
    
    public Venta(int id_venta, int id_empleado, int id_cliente, int id_producto, String num_serie, int num_item, Date fecha, String descripcion, BigDecimal precio, int cantidad, BigDecimal subtotal, BigDecimal monto, byte estado) {
        this.id_venta = id_venta;
        this.id_empleado = id_empleado;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.num_serie = num_serie;
        this.num_item = num_item;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.monto = monto;
        this.estado = estado;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.num_serie = num_serie;
    }

    public int getNum_item() {
        return num_item;
    }

    public void setNum_item(int num_item) {
        this.num_item = num_item;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    
    
}
