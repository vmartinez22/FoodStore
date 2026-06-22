/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstore.service;

/**
 *
 * @author MIPC
 */
import foodstore.entities.Categoria;
import foodstore.entities.Producto;
import foodstore.exception.EntidadNoEncontradaException;
import foodstore.exception.StockInvalidoException;
import java.util.ArrayList;

//Servicio de Productos: manejara la logica del negocio y el CRUD de productos
public class ProductoService {
     // Lista en memoria que almacena todos los productos
    private ArrayList<Producto> productos = new ArrayList<>();
    private Long contadorId = 1L; // Contador para asignar IDs automaticamente
    
    // CREAR - Agrega un nuevo producto
    public Producto crear(String nombre, double precio, String descripcion, 
                          int stock, String imagen, boolean disponible, 
                          Categoria categoria) throws StockInvalidoException {
        // Validar precio y stock
        if (precio < 0) {
            throw new StockInvalidoException("El precio no puede ser negativo.");
        }
        if (stock < 0) {
            throw new StockInvalidoException("El stock no puede ser negativo.");
        }
        Producto nuevo = new Producto(nombre, precio, descripcion, stock, imagen, disponible, categoria);
        nuevo.setId(contadorId++);
        productos.add(nuevo);
        System.out.println("Producto creado con ID: " + nuevo.getId());
        return nuevo;
    }
    
    // LISTAR - Muestra todos los productos no eliminados
    public void listar() {
        boolean hayRegistros = false;
        for (Producto p : productos) {
            if (!p.isEliminado()) {
                System.out.println(p);
                hayRegistros = true;
            }
        }
        if (!hayRegistros) {
            System.out.println("No hay productos cargados.");
        }
    }
    
    // BUSCAR por ID
    public Producto buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Producto p : productos) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                return p;
            }
        }
        throw new EntidadNoEncontradaException("Producto con ID " + id + " no encontrado.");
    }
    
    // EDITAR - Modifica los datos de un producto
    public void editar(Long id, String nuevoNombre, double nuevoPrecio, 
                       int nuevoStock) throws EntidadNoEncontradaException, StockInvalidoException {
        Producto p = buscarPorId(id);
        if (nuevoPrecio < 0) {
            throw new StockInvalidoException("El precio no puede ser negativo.");
        }
        if (nuevoStock < 0) {
            throw new StockInvalidoException("El stock no puede ser negativo.");
        }
        p.setNombre(nuevoNombre);
        p.setPrecio(nuevoPrecio);
        p.setStock(nuevoStock);
        System.out.println("Producto actualizado correctamente.");
    }
    
    // ELIMINAR - Soft delete
    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Producto p = buscarPorId(id);
        p.setEliminado(true);
        System.out.println("Producto eliminado correctamente.");
    }
    
    // Getter de la lista completa
    public ArrayList<Producto> getProductos() {
        return productos;
    }
}
