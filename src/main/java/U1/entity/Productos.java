package U1.entity;

import jakarta.persistence.*;

// Entidad JPA que representa los registros de la tabla productos.
@Entity
@Table(name = "productos")
public class Productos {

    // Identificador generado automáticamente por PostgreSQL.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Nombre comercial del producto.
    @Column
    private String nombre;

    // Precio unitario de venta.
    @Column
    private double precio;

    // Cantidad disponible en inventario.
    @Column
    private int cantidad;

    // Categoría a la que pertenece el producto.
    @Column
    private String categoria;

    // Constructor requerido por JPA.
    public Productos() {
    }

    // Constructor utilizado para crear productos desde la interfaz.
    public Productos(int id, String nombre, double precio, int cantidad, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
