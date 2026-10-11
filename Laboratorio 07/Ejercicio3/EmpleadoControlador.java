public class EmpleadoControlador {
    private EmpleadoModelo modelo;
    private EmpleadoVista vista;

    public EmpleadoControlador(EmpleadoModelo modelo, EmpleadoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        // Cargar y mostrar empleados al iniciar
        vista.mostrarEmpleados(modelo.leerEmpleados());

        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    vista.mostrarEmpleados(modelo.leerEmpleados());
                    break;
                case 2:
                    Empleado nuevo = vista.solicitarDatosEmpleado();
                    if (nuevo != null) {
                        try {
                            modelo.agregarEmpleado(nuevo);
                            vista.mostrarMensaje("Éxito: Empleado agregado correctamente.");
                        } catch (Exception e) {
                            vista.mostrarMensaje("Error: " + e.getMessage());
                        }
                    }
                    break;
                case 3:
                    int numBuscar = vista.solicitarNumero("Ingrese el número del empleado a buscar: ");
                    Empleado encontrado = modelo.buscarEmpleado(numBuscar);
                    if (encontrado != null) {
                        vista.mostrarMensaje("Empleado encontrado:");
                        vista.mostrarEmpleado(encontrado);
                    } else {
                        vista.mostrarMensaje("Aviso: No se encontró un empleado con ese número.");
                    }
                    break;
                case 4:
                    int numEliminar = vista.solicitarNumero("Ingrese el número del empleado a eliminar: ");
                    boolean eliminado = modelo.eliminarEmpleado(numEliminar);
                    if (eliminado) {
                        vista.mostrarMensaje("Éxito: Empleado eliminado correctamente.");
                    } else {
                        vista.mostrarMensaje("Aviso: No se encontró el empleado a eliminar.");
                    }
                    break;
                case 5:
                    vista.mostrarMensaje("Saliendo del programa...");
                    break;
                default:
                    vista.mostrarMensaje("Error: Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }
}
