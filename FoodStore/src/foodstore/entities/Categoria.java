/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstore.entities;

/**
 *
 * @author MIPC
 */
public class Categoria extends Base{
    private String nombre;        // Nombre de la categoría (ej: "Pizzas", "Bebidas")
    private String descripcion;   // Descripción de la categoría
    
    // Constructor con todos los atributos
    public Categoria(String nombre, String descripcion) {
        super();  // Llama al constructor de Base (inicializa eliminado=false y createdAt)
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    // toString para mostrar en consola
    @Override
    public String toString() {
        return "ID: " + getId() + " | Nombre: " + nombre + " | Descripción: " + descripcion;
    }
}
