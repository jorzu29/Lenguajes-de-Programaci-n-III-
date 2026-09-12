import java.util.NoSuchElementException;

class RegistroEstudiantes {
    private String[] estudiantes;
    private int cantidad;

    RegistroEstudiantes(int tamaño) {
        estudiantes = new String[tamaño];
        cantidad = 0;
    }

    public void agregarEstudiante(String nombre) {
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        estudiantes[cantidad] = nombre;
        cantidad = cantidad + 1;
    }

    public String buscarEstudiante(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].equals(nombre)) {
                return estudiantes[i];
            }
        }

        throw new NoSuchElementException("El estudiante no está registrado");
    }
}

public class Main {
    public static void main(String[] args) {

        RegistroEstudiantes registro = new RegistroEstudiantes(5);

        try {
            registro.agregarEstudiante("Kevin");
            registro.agregarEstudiante("Juan");
            registro.agregarEstudiante("");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(registro.buscarEstudiante("Pedro"));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
