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
import foodstore.exception.EntidadNoEncontradaException;
import java.util.ArrayList;

public class CategoriaService {
    // Lista en memoria que almacena todas las categorias
    private ArrayList<Categoria> categorias = new ArrayList<>();
    private Long contadorId = 1L; // Contador para asignar IDs automaticamente
    
    // CREAR - Agrega una nueva categoria
    public Categoria crear(String nombre, String descripcion) {
        // Validar que no exista otra categoria con el mismo nombre
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombre) && !c.isEliminado()) {
                System.out.println("Error: Ya existe una categoria con ese nombre.");
                return null;
            }
        }
        Categoria nueva = new Categoria(nombre, descripcion);
        nueva.setId(contadorId++);
        categorias.add(nueva);
        System.out.println("Categoria creada con ID: " + nueva.getId());
        return nueva;
    }
    
    // LISTAR - Muestra todas las categorias no eliminadas
    public void listar() {
        boolean hayRegistros = false;
        for (Categoria c : categorias) {
            if (!c.isEliminado()) {
                System.out.println(c);
                hayRegistros = true;
            }
        }
        if (!hayRegistros) {
            System.out.println("No hay categorias cargadas.");
        }
    }
    
    // BUSCAR por ID
    public Categoria buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Categoria c : categorias) {
            if (c.getId().equals(id) && !c.isEliminado()) {
                return c;
            }
        }
        throw new EntidadNoEncontradaException("Categoria con ID " + id + " no encontrada.");
    }
    
    // EDITAR - Modifica nombre y descripcion de una categoria
    public void editar(Long id, String nuevoNombre, String nuevaDescripcion) throws EntidadNoEncontradaException {
        Categoria c = buscarPorId(id);
        c.setNombre(nuevoNombre);
        c.setDescripcion(nuevaDescripcion);
        System.out.println("Categoria actualizada correctamente.");
    }
    
    // ELIMINAR - Soft delete: marca como eliminada sin borrarla fisicamente
    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Categoria c = buscarPorId(id);
        c.setEliminado(true);
        System.out.println("Categoria eliminada correctamente.");
    }
    
    // Getter de la lista completa (para uso interno)
    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
}
