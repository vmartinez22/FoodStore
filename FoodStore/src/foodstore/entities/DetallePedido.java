/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstore.entities;

/**
 *
 * @author MIPC
 */
public class DetallePedido extends Base{
    private int cantidad;       // Cantidad de unidades del producto
    private double subtotal;    // Subtotal = cantidad * precio del producto
    private Producto producto;  // Producto asociado a este detalle
    
    // Constructor con todos los atributos
    public DetallePedido(int cantidad, double subtotal, Producto producto) {
        super();  // Llama al constructor de Base
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    
    // toString para mostrar en consola
    @Override
    public String toString() {
        return "Producto: " + producto.getNombre() + 
               " | Cantidad: " + cantidad + 
               " | Subtotal: $" + subtotal;
    }
}
