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
        System.out.println(nombre + " recibió una notificación: " + message);
    }

}

class SistemaNotificaciones {

    private List<Observer> usuarios = new ArrayList<>();

    public void agregarUsuario(Observer usuario) {
        usuarios.add(usuario);
    }

    public void eliminarUsuario(Observer usuario) {
        usuarios.remove(usuario);
    }

    public void enviarNotificacion(String message) {
        for (Observer usuario : usuarios) {
            usuario.update(message);
        }
    }

}

public class ejericicio1 {

    public static void main(String[] args) {

        SistemaNotificaciones sistema = new SistemaNotificaciones();

        Observer usuario1 = new Usuario("Kevin");
        Observer usuario2 = new Usuario("Carlos");
        Observer usuario3 = new Usuario("Maria");

        sistema.agregarUsuario(usuario1);
        sistema.agregarUsuario(usuario2);
        sistema.agregarUsuario(usuario3);

        sistema.enviarNotificacion("Hay una nueva actualización disponible.");

    }

}
