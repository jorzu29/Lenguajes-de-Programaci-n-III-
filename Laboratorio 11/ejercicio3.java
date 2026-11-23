package Ejercicio;

interface Comando {
    void ejecutar();
}

class Televisor {
    public void encender() {
        System.out.println("El televisor está encendido");
    }

    public void apagar() {
        System.out.println("El televisor está apagado");
    }

    public void subirVolumen() {
        System.out.println("Subiendo el volumen del televisor");
    }

    public void bajarVolumen() {
        System.out.println("Bajando el volumen del televisor");
    }

    public void cambiarCanal() {
        System.out.println("Cambiando el canal del televisor");
    }
}

class ComandoEncender implements Comando {
    private Televisor televisor;

    public ComandoEncender(Televisor televisor) {
        this.televisor = televisor;
    }

    public void ejecutar() {
        televisor.encender();
    }
}

class ComandoApagar implements Comando {
    private Televisor televisor;

    public ComandoApagar(Televisor televisor) {
        this.televisor = televisor;
    }

    public void ejecutar() {
        televisor.apagar();
    }
}

class ComandoSubirVolumen implements Comando {
    private Televisor televisor;

    public ComandoSubirVolumen(Televisor televisor) {
        this.televisor = televisor;
    }

    public void ejecutar() {
        televisor.subirVolumen();
    }
}

class ComandoBajarVolumen implements Comando {
    private Televisor televisor;

    public ComandoBajarVolumen(Televisor televisor) {
        this.televisor = televisor;
    }

    public void ejecutar() {
        televisor.bajarVolumen();
    }
}

class ComandoCambiarCanal implements Comando {
    private Televisor televisor;

    public ComandoCambiarCanal(Televisor televisor) {
        this.televisor = televisor;
    }

    public void ejecutar() {
        televisor.cambiarCanal();
    }
}

class ControlRemoto {
    private Comando comando;

    public void establecerComando(Comando comando) {
        this.comando = comando;
    }

    public void presionarBoton() {
        comando.ejecutar();
    }
}

public class ejercicio3 {
    public static void main(String[] args) {
        Televisor televisor = new Televisor();

        Comando encender = new ComandoEncender(televisor);
        Comando subirVolumen = new ComandoSubirVolumen(televisor);
        Comando cambiarCanal = new ComandoCambiarCanal(televisor);
        Comando bajarVolumen = new ComandoBajarVolumen(televisor);
        Comando apagar = new ComandoApagar(televisor);

        ControlRemoto control = new ControlRemoto();

        control.establecerComando(encender);
        control.presionarBoton();

        control.establecerComando(subirVolumen);
        control.presionarBoton();

        control.establecerComando(cambiarCanal);
        control.presionarBoton();

        control.establecerComando(bajarVolumen);
        control.presionarBoton();

        control.establecerComando(apagar);
        control.presionarBoton();
    }
}
