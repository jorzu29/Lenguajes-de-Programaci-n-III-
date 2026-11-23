package main;

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

    public void mostrarPrecioFinal() {

        double descuento = estrategia.calcularDescuento(precio);
        double precioFinal = precio - descuento;

        System.out.println("Producto: " + nombre);
        System.out.println("Precio original: S/ " + precio);
        System.out.println("Descuento: S/ " + descuento);
        System.out.println("Precio final: S/ " + precioFinal);
        System.out.println("-------------------------");

    }

}

public class ejericicio1 {

    public static void main(String[] args) {

        Producto producto1 = new Producto("Laptop", 2000, new Descuento10());

        Producto producto2 = new Producto("Celular", 1500, new Descuento20());

        Producto producto3 = new Producto("Audifonos", 200, new SinDescuento());

        producto1.mostrarPrecioFinal();

        producto2.mostrarPrecioFinal();

        producto3.mostrarPrecioFinal();

    }

}
