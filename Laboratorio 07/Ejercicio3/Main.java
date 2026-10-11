public class Main {
    public static void main(String[] args) {
        EmpleadoModelo modelo = new EmpleadoModelo();
        EmpleadoVista vista = new EmpleadoVista();
        EmpleadoControlador controlador = new EmpleadoControlador(modelo, vista);
        
        controlador.iniciar();
    }
}
