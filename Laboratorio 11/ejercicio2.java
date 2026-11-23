package Ejercicio;

import java.util.Scanner;

interface EstrategiaDescuento {
    double aplicarDescuento(double precio, int cantidad);
}

class SinDescuento implements EstrategiaDescuento {
    public double aplicarDescuento(double precio, int cantidad) {
        return precio * cantidad;
    }
}

class DescuentoFijo implements EstrategiaDescuento {
    public double aplicarDescuento(double precio, int cantidad) {
        return (precio * cantidad) * 0.9;
    }
}

class DescuentoPorcentual implements EstrategiaDescuento {
    public double aplicarDescuento(double precio, int cantidad) {
        if (cantidad == 2) {
            return (precio * cantidad) * 0.7;
        }
        return precio * cantidad;
    }
}

class DescuentoPorcentualAcumulado implements EstrategiaDescuento {
    public double aplicarDescuento(double precio, int cantidad) {
        if (cantidad >= 3) {
            return (precio * (cantidad - 1)) + (precio * 0.5);
        }
        return precio * cantidad;
    }
}

class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String setNombre() {
        return nombre;
    }

    public double setPrecio() {
        return precio;
    }
}

class CalculadoraDePrecios {
    private EstrategiaDescuento estrategiaDescuento;

    public void establecerEstrategiaDescuento(EstrategiaDescuento estrategiaDescuento) {
        this.estrategiaDescuento = estrategiaDescuento;
    }

    public double calcularPrecio(double precio, int cantidad) {
        return estrategiaDescuento.aplicarDescuento(precio, cantidad);
    }
}

public class ejercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CalculadoraDePrecios calculadora = new CalculadoraDePrecios();

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio unitario del producto: ");
        double precio = scanner.nextDouble();

        System.out.print("Cantidad de productos: ");
        int cantidad = scanner.nextInt();

        Producto producto = new Producto(nombre, precio);

        System.out.println("Elige una estrategia de descuento:");
        System.out.println("1. SinDescuento");
        System.out.println("2. DescuentoFijo (10%)");
        System.out.println("3. DescuentoPorcentual (2 productos iguales -> 30% de descuento)");
        System.out.println("4. DescuentoPorcentualAcumulado (3 o mas productos -> 50% al menor)");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            calculadora.establecerEstrategiaDescuento(new SinDescuento());
        } else if (opcion == 2) {
            calculadora.establecerEstrategiaDescuento(new DescuentoFijo());
        } else if (opcion == 3) {
            calculadora.establecerEstrategiaDescuento(new DescuentoPorcentual());
        } else if (opcion == 4) {
            calculadora.establecerEstrategiaDescuento(new DescuentoPorcentualAcumulado());
        } else {
            calculadora.establecerEstrategiaDescuento(new SinDescuento());
        }

        double total = calculadora.calcularPrecio(producto.setPrecio(), cantidad);
        System.out.println("Producto: " + producto.setNombre() + " | Total a pagar: " + total);

        scanner.close();
    }
}
