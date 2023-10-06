package modelo;

import java.math.BigDecimal;

public class Producto { 
    private int id_producto;
    private String nombre;
    private BigDecimal precio;
    private int stock;
    private byte estado;

    public Producto() {
    }
    
    public Producto(String nombre, BigDecimal precio, int stock, byte estado) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public Producto(int id_producto, String nombre, BigDecimal precio, int stock, byte estado) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }
    
    
}
