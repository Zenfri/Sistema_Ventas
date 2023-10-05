
package modelo;

public class Cliente {
    private int id_cliente;
    private String dni;
    private String nombre;
    private String direccion;
    private Byte estado;

    public Cliente() {
    }

    public Cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Cliente(String dni, String nombre, String direccion, Byte estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Cliente(int id_cliente, String dni, String nombre, String direccion, Byte estado) {
        this.id_cliente = id_cliente;
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estado = estado;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Byte getEstado() {
        return estado;
    }

    public void setEstado(Byte estado) {
        this.estado = estado;
    }
    
    
}
