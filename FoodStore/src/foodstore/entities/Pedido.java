/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstore.entities;

/**
 *
 * @author MIPC
 */
import foodstore.enums.Estado;
import foodstore.enums.FormaPago;
import foodstore.exception.StockInvalidoException;
import java.time.LocalDate;
import java.util.ArrayList;

// Clase Pedido: hereda de Base e implementa Calculable
// va a representar un pedido completo con sus detalles
public class Pedido extends Base implements Calculable {
    private LocalDate fecha;                    // Fecha del pedido
    private Estado estado;                      // Estado actual del pedido
    private double total;                       // Total calculado del pedido
    private FormaPago formaPago;                // Forma de pago elegida
    private Usuario usuario;                    // Usuario que realizó el pedido
    private ArrayList<DetallePedido> detalles;  // Lista de detalles/ítems del pedido
    
    // Constructor
    public Pedido(Usuario usuario, FormaPago formaPago) {
        super();
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.total = 0;
        this.formaPago = formaPago;
        this.usuario = usuario;
        this.detalles = new ArrayList<>();
    }
    
    // Agrega un detalle al pedido validando stock y cantidad
    public void addDetallePedido(int cantidad, double precio, Producto producto) throws StockInvalidoException {
        if (cantidad <= 0) {
            throw new StockInvalidoException("La cantidad debe ser mayor a 0.");
        }
        if (producto.getStock() < cantidad) {
            throw new StockInvalidoException("Stock insuficiente para el producto: " + producto.getNombre());
        }
        double subtotal = cantidad * precio;
        DetallePedido detalle = new DetallePedido(cantidad, subtotal, producto);
        detalles.add(detalle);
    }
    
    // Busca un detalle por producto
    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        for (DetallePedido d : detalles) {
            if (d.getProducto().getId().equals(producto.getId())) {
                return d;
            }
        }
        return null;
    }
    
    // Elimina un detalle por producto
    public void deleteDetallePedidoByProducto(Producto producto) {
        detalles.removeIf(d -> d.getProducto().getId().equals(producto.getId()));
    }
    
    // Implementación de Calculable: calcula el total sumando los subtotales
    @Override
    public void calcularTotal() {
        double suma = 0;
        for (DetallePedido d : detalles) {
            suma += d.getSubtotal();
        }
        this.total = suma;
    }
    
    public LocalDate getFecha() { return fecha; }
    
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    
    public double getTotal() { return total; }
    
    public FormaPago getFormaPago() { return formaPago; }
    public void setFormaPago(FormaPago formaPago) { this.formaPago = formaPago; }
    
    public Usuario getUsuario() { return usuario; }
    
    public ArrayList<DetallePedido> getDetalles() { return detalles; }
    
    // toString útil para mostrar en consola
    @Override
    public String toString() {
        return "ID: " + getId() + " | Usuario: " + usuario.getNombre() + 
               " | Estado: " + estado + " | Forma de pago: " + formaPago + 
               " | Total: $" + total + " | Fecha: " + fecha;
    }
}
