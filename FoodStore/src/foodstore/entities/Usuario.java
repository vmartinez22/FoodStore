/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstore.entities;

/**
 *
 * @author MIPC
 */
import foodstore.enums.Rol;

public class Usuario extends Base{
     private String nombre;        // Nombre del usuario
    private String apellido;      // Apellido del usuario
    private String mail;          // Email único del usuario
    private String celular;       // Número de celular
    private String contrasena;    // Contraseña del usuario
    private Rol rol;              // Rol del usuario (ADMIN o USUARIO)
    
    // Constructor con todos los atributos
    public Usuario(String nombre, String apellido, String mail, String celular, String contrasena, Rol rol) {
        super();  // Llama al constructor de Base
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    
    // toString para mostrar en consola
    @Override
    public String toString() {
        return "ID: " + getId() + " | Nombre: " + nombre + " " + apellido + 
               " | Mail: " + mail + " | Rol: " + rol;
    }
}
