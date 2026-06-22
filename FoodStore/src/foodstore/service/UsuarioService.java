/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstore.service;

/**
 *
 * @author MIPC
 */
import foodstore.entities.Usuario;
import foodstore.enums.Rol;
import foodstore.exception.EntidadNoEncontradaException;
import java.util.ArrayList;

// Servicio de Usuarios: va a manejar la logica del negocio y el CRUD de usuario
public class UsuarioService {
     // Lista en memoria que almacena todos los usuarios
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private Long contadorId = 1L;
    
    // CREAR - Agrega un nuevo usuario
    public Usuario crear(String nombre, String apellido, String mail, 
                         String celular, String contrasena, Rol rol) {
        // Validar que el mail sea unico
        for (Usuario u : usuarios) {
            if (u.getMail().equalsIgnoreCase(mail) && !u.isEliminado()) {
                System.out.println("Error: Ya existe un usuario con ese mail.");
                return null;
            }
        }
        Usuario nuevo = new Usuario(nombre, apellido, mail, celular, contrasena, rol);
        nuevo.setId(contadorId++);
        usuarios.add(nuevo);
        System.out.println("Usuario creado con ID: " + nuevo.getId());
        return nuevo;
    }
    
    // LISTAR - Muestra todos los usuarios no eliminados
    public void listar() {
        boolean hayRegistros = false;
        for (Usuario u : usuarios) {
            if (!u.isEliminado()) {
                System.out.println(u);
                hayRegistros = true;
            }
        }
        if (!hayRegistros) {
            System.out.println("No hay usuarios cargados.");
        }
    }
    
    // BUSCAR por ID
    public Usuario buscarPorId(Long id) throws EntidadNoEncontradaException {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id) && !u.isEliminado()) {
                return u;
            }
        }
        throw new EntidadNoEncontradaException("Usuario con ID " + id + " no encontrado.");
    }
    
    // EDITAR - Modifica los datos de un usuario
    public void editar(Long id, String nuevoNombre, String nuevoApellido, 
                       String nuevoMail, String nuevoCelular) throws EntidadNoEncontradaException {
        Usuario u = buscarPorId(id);
        // Validar unicidad del nuevo mail
        for (Usuario otro : usuarios) {
            if (otro.getMail().equalsIgnoreCase(nuevoMail) && 
                !otro.getId().equals(id) && !otro.isEliminado()) {
                System.out.println("Error: Ya existe un usuario con ese mail.");
                return;
            }
        }
        u.setNombre(nuevoNombre);
        u.setApellido(nuevoApellido);
        u.setMail(nuevoMail);
        u.setCelular(nuevoCelular);
        System.out.println("Usuario actualizado correctamente.");
    }
    
    // ELIMINAR - Soft delete
    public void eliminar(Long id) throws EntidadNoEncontradaException {
        Usuario u = buscarPorId(id);
        u.setEliminado(true);
        System.out.println("Usuario eliminado correctamente.");
    }
    
    // Getter de la lista completa
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
