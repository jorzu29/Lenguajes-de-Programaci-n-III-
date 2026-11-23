package Ejercicio;

import java.util.ArrayList;
import java.util.List;

interface Observador {
    void actualizar(String mensaje);
}

class Usuario implements Observador {
    private String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public void actualizar(String mensaje) {
        System.out.println(nombre + " recibió: " + mensaje);
    }
}

class Notificacion {
    private String mensaje;

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
    }

    public String obtenerMensaje() {
        return mensaje;
    }
}

class Sujeto {
    private List<Observador> observadores = new ArrayList<>();

    public void adjuntar(Observador observador) {
        observadores.add(observador);
    }

    public void desadjuntar(Observador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(String mensaje) {
        for (Observador observador : observadores) {
            observador.actualizar(mensaje);
        }
    }
}

public class ejercicio1 {
    public static void main(String[] args) {
        Sujeto sujeto = new Sujeto();

        Observador usuario1 = new Usuario("Usuario1");
        Observador usuario2 = new Usuario("Usuario2");

        sujeto.adjuntar(usuario1);
        sujeto.adjuntar(usuario2);

        Notificacion promocion = new Notificacion("Promoción especial del día");
        sujeto.notificarObservadores(promocion.obtenerMensaje());

        sujeto.desadjuntar(usuario2);

        Notificacion actualizacion = new Notificacion("Actualización de producto disponible");
        sujeto.notificarObservadores(actualizacion.obtenerMensaje());
    }
}
