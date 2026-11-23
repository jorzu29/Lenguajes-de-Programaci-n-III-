package main;

import java.util.ArrayList;
import java.util.List;

  
interface Observer {

    void update(String message);

}

class Usuario implements Observer {

    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public void update(String message) {
        System.out.println(nombre + " recibió: " + message);
    }

}

class SistemaNotificaciones {

    private List<Observer> usuarios = new ArrayList<>();

    public void agregarUsuario(Observer usuario) {
        usuarios.add(usuario);
    }

    public void enviarNotificacion(String message) {
        for (Observer usuario : usuarios) {
            usuario.update(message);
        }
    }

}

 
interface EstrategiaPromocion {

    double calcularDescuento(double precio);

}

class Descuento10 implements EstrategiaPromocion {

    public double calcularDescuento(double precio) {
        return precio * 0.10;
    }

}

class Descuento20 implements EstrategiaPromocion {

    public double calcularDescuento(double precio) {
        return precio * 0.20;
    }

}

class SinDescuento implements EstrategiaPromocion {

    public double calcularDescuento(double precio) {
        return 0;
    }

}

class Producto {

    private String nombre;
    private double precio;
    private EstrategiaPromocion estrategia;

    public Producto(String nombre, double precio, EstrategiaPromocion estrategia) {
        this.nombre = nombre;
        this.precio = precio;
        this.estrategia = estrategia;
    }

    public double calcularPrecioFinal() {
        return precio - estrategia.calcularDescuento(precio);
    }

    public void mostrarProducto() {
        System.out.println("Producto: " + nombre);
        System.out.println("Precio final: S/ " + calcularPrecioFinal());
    }

}
 
interface Command {

    void ejecutar();

}

class Tienda {

    public void registrarVenta() {
        System.out.println("Venta registrada correctamente.");
    }

    public void cancelarVenta() {
        System.out.println("Venta cancelada.");
    }

    public void mostrarProductos() {
        System.out.println("Mostrando productos disponibles.");
    }

    public void consultarTotal() {
        System.out.println("Consultando total de la compra.");
    }

    public void cerrarCaja() {
        System.out.println("Caja cerrada correctamente.");
    }

}

class RegistrarVentaCommand implements Command {

    private Tienda tienda;

    public RegistrarVentaCommand(Tienda tienda) {
        this.tienda = tienda;
    }

    public void ejecutar() {
        tienda.registrarVenta();
    }

}

class CancelarVentaCommand implements Command {

    private Tienda tienda;

    public CancelarVentaCommand(Tienda tienda) {
        this.tienda = tienda;
    }

    public void ejecutar() {
        tienda.cancelarVenta();
    }

}

class MostrarProductosCommand implements Command {

    private Tienda tienda;

    public MostrarProductosCommand(Tienda tienda) {
        this.tienda = tienda;
    }

    public void ejecutar() {
        tienda.mostrarProductos();
    }

}

class ConsultarTotalCommand implements Command {

    private Tienda tienda;

    public ConsultarTotalCommand(Tienda tienda) {
        this.tienda = tienda;
    }

    public void ejecutar() {
        tienda.consultarTotal();
    }

}

class CerrarCajaCommand implements Command {

    private Tienda tienda;

    public CerrarCajaCommand(Tienda tienda) {
        this.tienda = tienda;
    }

    public void ejecutar() {
        tienda.cerrarCaja();
    }

}

class ControlTienda {

    public void presionarBoton(Command comando) {
        comando.ejecutar();
    }

}
 
public class ejericicio1 {
    public static void main(String[] args) {
        System.out.println("===== SISTEMA DE VENTAS =====");
        System.out.println("\n--- NOTIFICACIONES ---");
        SistemaNotificaciones sistema = new SistemaNotificaciones();
        Observer usuario1 = new Usuario("Kevin");
        Observer usuario2 = new Usuario("Carlos");
        Observer usuario3 = new Usuario("Maria");
        sistema.agregarUsuario(usuario1);
        sistema.agregarUsuario(usuario2);
        sistema.agregarUsuario(usuario3);

        System.out.println("\n--- PROMOCIONES ---");

        Producto producto1 = new Producto("Laptop", 2000, new Descuento10());
        Producto producto2 = new Producto("Celular", 1500, new Descuento20());
        Producto producto3 = new Producto("Audifonos", 200, new SinDescuento());

        producto1.mostrarProducto();
        producto2.mostrarProducto();
        producto3.mostrarProducto();
        System.out.println("\n--- FUNCIONES DE LA TIENDA ---");
        Tienda tienda = new Tienda();
        ControlTienda control = new ControlTienda();
        control.presionarBoton(new MostrarProductosCommand(tienda));
        control.presionarBoton(new ConsultarTotalCommand(tienda));
        control.presionarBoton(new RegistrarVentaCommand(tienda));
        System.out.println("\n--- NOTIFICACIONES DE COMPRA ---");
        sistema.enviarNotificacion("Se ha realizado una nueva venta.");
        control.presionarBoton(new CancelarVentaCommand(tienda));
        control.presionarBoton(new CerrarCajaCommand(tienda));
    }
}
