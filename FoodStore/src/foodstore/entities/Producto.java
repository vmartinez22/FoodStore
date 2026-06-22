/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstore.entities;

/**
 *
 * @author MIPC
 */
public class Producto extends Base{
    private String nombre;          // Nombre del producto (ej: "Pizza Margarita")
    private double precio;          // Precio del producto
    private String descripcion;     // Descripción del producto
    private int stock;              // Cantidad disponible en stock
    private String imagen;          // URL o nombre de la imagen del producto
    private boolean disponible;     // Si el producto está disponible para pedir
    private Categoria categoria;    // Categoría a la que pertenece el producto
    
    // Constructor con todos los atributos
    public Producto(String nombre, double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria) {
        super();  // Llama al constructor de Base
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
    }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    
    // toString útil para mostrar en consola
    @Override
    public String toString() {
        return "ID: " + getId() + " | Nombre: " + nombre + " | Precio: $" + precio + 
               " | Stock: " + stock + " | Disponible: " + disponible + 
               " | Categoría: " + categoria.getNombre();
    }
}
