package main;

interface Command {

    void ejecutar();

}
class Televisor {
    public void encender() {
        System.out.println("Televisor encendido.");
    }
    public void apagar() {
        System.out.println("Televisor apagado.");
    }

    public void subirVolumen() {
        System.out.println("Volumen aumentado.");
    }

    public void bajarVolumen() {
        System.out.println("Volumen disminuido.");
    }

    public void cambiarCanal() {
        System.out.println("Canal cambiado.");
    }

}

class EncenderCommand implements Command {

    private Televisor televisor;

    public EncenderCommand(Televisor televisor) {
        this.televisor = televisor;
    }

    public void ejecutar() {
        televisor.encender();
    }

}

class ApagarCommand implements Command {
    private Televisor televisor;
    public ApagarCommand(Televisor televisor) {
        this.televisor = televisor;
    }
    public void ejecutar() {
        televisor.apagar();
    }
}

class SubirVolumenCommand implements Command {

    private Televisor televisor;

    public SubirVolumenCommand(Televisor televisor) {
        this.televisor = televisor;
    }

    public void ejecutar() {
        televisor.subirVolumen();
    }

}

class BajarVolumenCommand implements Command {

    private Televisor televisor;

    public BajarVolumenCommand(Televisor televisor) {
        this.televisor = televisor;
    }

    public void ejecutar() {
        televisor.bajarVolumen();
    }

}

class CambiarCanalCommand implements Command {

    private Televisor televisor;

    public CambiarCanalCommand(Televisor televisor) {
        this.televisor = televisor;
    }

    public void ejecutar() {
        televisor.cambiarCanal();
    }

}

class ControlRemoto {

    public void presionarBoton(Command comando) {
        comando.ejecutar();
    }

}
public class ejericicio1 {
    public static void main(String[] args) {
        Televisor televisor = new Televisor();
        ControlRemoto control = new ControlRemoto();
        control.presionarBoton(new EncenderCommand(televisor));
        control.presionarBoton(new SubirVolumenCommand(televisor));
        control.presionarBoton(new BajarVolumenCommand(televisor));
        control.presionarBoton(new CambiarCanalCommand(televisor));
        control.presionarBoton(new ApagarCommand(televisor));
    }
}
