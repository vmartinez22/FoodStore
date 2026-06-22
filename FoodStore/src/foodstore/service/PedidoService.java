/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstore.service;

/**
 *
 * @author MIPC
 */

import foodstore.entities.Pedido;
import foodstore.entities.Producto;
import foodstore.entities.Usuario;
import foodstore.enums.Estado;
import foodstore.enums.FormaPago;
import foodstore.exception.EntidadNoEncontradaException;
import foodstore.exception.StockInvalidoException;
import java.util.ArrayList;

//Servicio de pedidos: manejara la logica de negocio y el CRUD de los pedidos
public class PedidoService {
     // Lista en memoria que almacena todos los pedidos
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private Long contadorId = 1L;
    
    // CREAR - Agrega un nuevo pedido
    public Pedido crear(Usuario usuario, FormaPago formaPago) {
        if (usuario == null) {
            System.out.println("Error: El pedido debe tener un usuario.");
            return null;
        }
        Pedido nuevo = new Pedido(usuario, formaPago);
        nuevo.setId(contadorId++);
        pedidos.add(nuevo);
        System.out.println("Pedido creado con ID: " + nuevo.getId());
        return nuevo;
    }
    
    // AGREGAR DETALLE - Agrega un producto al pedido
    public void agregarDetalle(Pedido pedido, int cantidad, Producto producto) 
                               throws StockInvalidoException {
        pedido.addDetallePedido(cantidad, producto.getPrecio(), producto);
        pedido.calcularTotal();
        System.out.println("Producto agregado al pedido correctamente.");
    }
    
    // LISTAR - Muestra todos los pedidos no eliminados
    public void listar() {
        boolean hayRegistros = false;
        for (Pedido p : pedidos) {
            if (!p.isEliminado()) {
                System.out.println(p);
                hayRegistros = true;
            }
        }
        if (!hayRegistros) {
            System.out.println("No hay pedidos cargados.");
        }
    }
    
    // BUSCAR por ID
    public Pedido buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Pedido p : pedidos) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                return p;
            }
        }
        throw new EntidadNoEncontradaException("Pedido con ID " + id + " no encontrado.");
    }
    
    // ACTUALIZAR estado y forma de pago
    public void actualizar(Long id, Estado nuevoEstado, FormaPago nuevaFormaPago) 
                           throws EntidadNoEncontradaException {
        Pedido p = buscarPorId(id);
        p.setEstado(nuevoEstado);
        p.setFormaPago(nuevaFormaPago);
        System.out.println("Pedido actualizado correctamente.");
    }
    
    // ELIMINAR - Soft delete
    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Pedido p = buscarPorId(id);
        p.setEliminado(true);
        System.out.println("Pedido eliminado correctamente.");
    }
    
    // Getter de la lista completa
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}
