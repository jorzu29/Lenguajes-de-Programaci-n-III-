import java.util.List;
import java.util.Scanner;

public class EmpleadoVista {
    private Scanner escaner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n=============MENÚ=============");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar un nuevo empleado");
        System.out.println("3. Buscar un empleado por su número");
        System.out.println("4. Eliminar un empleado por su número");
        System.out.println("5. Salir del programa");
        System.out.print("Elija una opción: ");
        
        return Integer.valueOf(escaner.nextLine());
    }
    
    public void mostrarEmpleados(List<Empleado> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay empleados registrados");
        } else {
            System.out.println("\n-------------Empleados-------------");
            for (Empleado e : lista){
                System.out.println("Número: " + e.getNumero() + " | Nombre: " + e.getNombre() + " | Sueldo: $" + e.getSueldo());
            }
        }
    }
    
    public void mostrarEmpleado(Empleado e) {
        System.out.println("Número: " + e.getNumero() + " | Nombre: " + e.getNombre() + " | Sueldo: $" + e.getSueldo());
    }
    
    public Empleado solicitarDatosEmpleado() {
        try {
            System.out.print("Ingrese número: ");
            int numero = Integer.valueOf(escaner.nextLine());
            System.out.print("Ingrese nombre: ");
            String nombre = escaner.nextLine();
            System.out.print("Ingrese sueldo: ");
            double sueldo = Double.valueOf(escaner.nextLine());
            return new Empleado(numero, nombre, sueldo);
        } catch (NumberFormatException e) {
            System.out.println("Error: Entrada no válida.");
            return null;
        }
    }
    
    public int solicitarNumero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.valueOf(escaner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}



