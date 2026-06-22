/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstore.ui;

/**
 *
 * @author MIPC
 */
import foodstore.entities.Categoria;
import foodstore.entities.Pedido;
import foodstore.entities.Producto;
import foodstore.entities.Usuario;
import foodstore.enums.Estado;
import foodstore.enums.FormaPago;
import foodstore.enums.Rol;
import foodstore.exception.EntidadNoEncontradaException;
import foodstore.exception.StockInvalidoException;
import foodstore.service.CategoriaService;
import foodstore.service.PedidoService;
import foodstore.service.ProductoService;
import foodstore.service.UsuarioService;
import java.util.Scanner;

// la clase menu va a manejar toda la interaccion con el usuario por la consola
public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private CategoriaService categoriaService = new CategoriaService();
    private ProductoService productoService = new ProductoService();
    private UsuarioService usuarioService = new UsuarioService();
    private PedidoService pedidoService = new PedidoService();
    
    // Menu principal
    public void mostrarMenuPrincipal() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1: menuCategorias(); break;
                    case 2: menuProductos(); break;
                    case 3: menuUsuarios(); break;
                    case 4: menuPedidos(); break;
                    case 0: System.out.println("Saliendo del sistema..."); break;
                    default: System.out.println("Opcion invalida."); break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
    }
    
    // Submenu Categorias
    private void menuCategorias() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== CATEGORIAS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        categoriaService.listar();
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Descripcion: ");
                        String descripcion = scanner.nextLine();
                        categoriaService.crear(nombre, descripcion);
                        break;
                    case 3:
                        categoriaService.listar();
                        System.out.print("ID a editar: ");
                        Long idEditar = Long.parseLong(scanner.nextLine());
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nueva descripcion: ");
                        String nuevaDesc = scanner.nextLine();
                        try {
                            categoriaService.editar(idEditar, nuevoNombre, nuevaDesc);
                        } catch (EntidadNoEncontradaException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        categoriaService.listar();
                        System.out.print("ID a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());
                        System.out.print("Confirma eliminacion? (S/N): ");
                        String confirmacion = scanner.nextLine();
                        if (confirmacion.equalsIgnoreCase("S")) {
                            try {
                                categoriaService.eliminar(idEliminar);
                            } catch (EntidadNoEncontradaException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;
                    case 0: break;
                    default: System.out.println("Opcion invalida."); break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
    }
    
    // Submenu Productos
    private void menuProductos() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== PRODUCTOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        productoService.listar();
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Precio: ");
                        double precio = Double.parseDouble(scanner.nextLine());
                        System.out.print("Descripcion: ");
                        String descripcion = scanner.nextLine();
                        System.out.print("Stock: ");
                        int stock = Integer.parseInt(scanner.nextLine());
                        System.out.print("Imagen (URL o nombre): ");
                        String imagen = scanner.nextLine();
                        System.out.print("Disponible (S/N): ");
                        boolean disponible = scanner.nextLine().equalsIgnoreCase("S");
                        categoriaService.listar();
                        System.out.print("ID de categoria: ");
                        Long idCat = Long.parseLong(scanner.nextLine());
                        try {
                            Categoria cat = categoriaService.buscarPorId(idCat);
                            productoService.crear(nombre, precio, descripcion, stock, imagen, disponible, cat);
                        } catch (EntidadNoEncontradaException | StockInvalidoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        productoService.listar();
                        System.out.print("ID a editar: ");
                        Long idEditar = Long.parseLong(scanner.nextLine());
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevo precio: ");
                        double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                        System.out.print("Nuevo stock: ");
                        int nuevoStock = Integer.parseInt(scanner.nextLine());
                        try {
                            productoService.editar(idEditar, nuevoNombre, nuevoPrecio, nuevoStock);
                        } catch (EntidadNoEncontradaException | StockInvalidoException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        productoService.listar();
                        System.out.print("ID a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());
                        System.out.print("Confirma eliminacion? (S/N): ");
                        String confirmacion = scanner.nextLine();
                        if (confirmacion.equalsIgnoreCase("S")) {
                            try {
                                productoService.eliminar(idEliminar);
                            } catch (EntidadNoEncontradaException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;
                    case 0: break;
                    default: System.out.println("Opcion invalida."); break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
    }
    
    // Submenu Usuarios
    private void menuUsuarios() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== USUARIOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        usuarioService.listar();
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apellido: ");
                        String apellido = scanner.nextLine();
                        System.out.print("Mail: ");
                        String mail = scanner.nextLine();
                        System.out.print("Celular: ");
                        String celular = scanner.nextLine();
                        System.out.print("Contrasena: ");
                        String contrasena = scanner.nextLine();
                        System.out.println("Rol (1. ADMIN / 2. USUARIO): ");
                        int rolOpcion = Integer.parseInt(scanner.nextLine());
                        Rol rol = rolOpcion == 1 ? Rol.ADMIN : Rol.USUARIO;
                        usuarioService.crear(nombre, apellido, mail, celular, contrasena, rol);
                        break;
                    case 3:
                        usuarioService.listar();
                        System.out.print("ID a editar: ");
                        Long idEditar = Long.parseLong(scanner.nextLine());
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevo apellido: ");
                        String nuevoApellido = scanner.nextLine();
                        System.out.print("Nuevo mail: ");
                        String nuevoMail = scanner.nextLine();
                        System.out.print("Nuevo celular: ");
                        String nuevoCelular = scanner.nextLine();
                        try {
                            usuarioService.editar(idEditar, nuevoNombre, nuevoApellido, nuevoMail, nuevoCelular);
                        } catch (EntidadNoEncontradaException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        usuarioService.listar();
                        System.out.print("ID a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());
                        System.out.print("Confirma eliminacion? (S/N): ");
                        String confirmacion = scanner.nextLine();
                        if (confirmacion.equalsIgnoreCase("S")) {
                            try {
                                usuarioService.eliminar(idEliminar);
                            } catch (EntidadNoEncontradaException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;
                    case 0: break;
                    default: System.out.println("Opcion invalida."); break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
    }
    
    // Submenu Pedidos
    private void menuPedidos() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=== PEDIDOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Actualizar estado/forma de pago");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        pedidoService.listar();
                        break;
                    case 2:
                        usuarioService.listar();
                        System.out.print("ID de usuario: ");
                        Long idUsuario = Long.parseLong(scanner.nextLine());
                        System.out.println("Forma de pago (1. TARJETA / 2. TRANSFERENCIA / 3. EFECTIVO): ");
                        int fpOpcion = Integer.parseInt(scanner.nextLine());
                        FormaPago fp;
                        switch (fpOpcion) {
                            case 1: fp = FormaPago.TARJETA; break;
                            case 2: fp = FormaPago.TRANSFERENCIA; break;
                            default: fp = FormaPago.EFECTIVO; break;
                        }
                        try {
                            Usuario usuario = usuarioService.buscarPorId(idUsuario);
                            Pedido pedido = pedidoService.crear(usuario, fp);
                            // Agregar detalles
                            String continuar = "S";
                            while (continuar.equalsIgnoreCase("S")) {
                                productoService.listar();
                                System.out.print("ID de producto: ");
                                Long idProducto = Long.parseLong(scanner.nextLine());
                                System.out.print("Cantidad: ");
                                int cantidad = Integer.parseInt(scanner.nextLine());
                                try {
                                    Producto producto = productoService.buscarPorId(idProducto);
                                    pedidoService.agregarDetalle(pedido, cantidad, producto);
                                } catch (EntidadNoEncontradaException | StockInvalidoException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                                System.out.print("Agregar otro producto? (S/N): ");
                                continuar = scanner.nextLine();
                            }
                            pedido.calcularTotal();
                            System.out.println("Pedido finalizado. Total: $" + pedido.getTotal());
                        } catch (EntidadNoEncontradaException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        pedidoService.listar();
                        System.out.print("ID de pedido: ");
                        Long idPedido = Long.parseLong(scanner.nextLine());
                        System.out.println("Nuevo estado (1.PENDIENTE / 2.CONFIRMADO / 3.TERMINADO / 4.CANCELADO): ");
                        int estadoOpcion = Integer.parseInt(scanner.nextLine());
                        Estado estado;
                        switch (estadoOpcion) {
                            case 1: estado = Estado.PENDIENTE; break;
                            case 2: estado = Estado.CONFIRMADO; break;
                            case 3: estado = Estado.TERMINADO; break;
                            default: estado = Estado.CANCELADO; break;
                        }
                        System.out.println("Nueva forma de pago (1.TARJETA / 2.TRANSFERENCIA / 3.EFECTIVO): ");
                        int fpOpcion2 = Integer.parseInt(scanner.nextLine());
                        FormaPago fp2;
                        switch (fpOpcion2) {
                            case 1: fp2 = FormaPago.TARJETA; break;
                            case 2: fp2 = FormaPago.TRANSFERENCIA; break;
                            default: fp2 = FormaPago.EFECTIVO; break;
                        }
                        try {
                            pedidoService.actualizar(idPedido, estado, fp2);
                        } catch (EntidadNoEncontradaException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        pedidoService.listar();
                        System.out.print("ID a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());
                        System.out.print("Confirma eliminacion? (S/N): ");
                        String confirmacion = scanner.nextLine();
                        if (confirmacion.equalsIgnoreCase("S")) {
                            try {
                                pedidoService.eliminar(idEliminar);
                            } catch (EntidadNoEncontradaException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;
                    case 0: break;
                    default: System.out.println("Opcion invalida."); break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido.");
            }
        }
    }
}
