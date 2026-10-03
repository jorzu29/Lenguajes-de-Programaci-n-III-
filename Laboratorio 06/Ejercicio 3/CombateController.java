import java.util.Random;

public class CombateController {
    private Jugador jugador;
    private Enemigo enemigo;
    private CombateView vista;
    private Random random;

    public CombateController(Jugador jugador, Enemigo enemigo, CombateView vista) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.vista = vista;
        this.random = new Random();
    }

    public void Turno(String accionJugador, String nombreObjeto) {
        if (!jugador.estaVivo() || !enemigo.estaVivo()) return;

        // Accion jugador
        if (accionJugador.equalsIgnoreCase("atacar")) {
            int danio = jugador.atacar();
            enemigo.recibirDanio(danio);
            vista.mostrarMensajeCombate(jugador.getNombre() + " ataca e inflige " + danio + " de daño.");
        } else if (accionJugador.equalsIgnoreCase("usar")) {
            if (jugador.usarObjeto(nombreObjeto)) {
                vista.mostrarMensajeCombate(jugador.getNombre() + " uso: " + nombreObjeto);
            } else {
                vista.mostrarMensajeCombate("No se pudo usar " + nombreObjeto);
            }
        }

        if (!enemigo.estaVivo()) {
            vista.mostrarResultadoFinal("¡VICTORIA! " + jugador.getNombre() + " derroto al enemigo!!!!!!");
            return;
        }

        // Probalbilidad de atacar
        if (random.nextInt(10) < 7) {
            int danoEnemigo = enemigo.atacar();
            jugador.recibirDano(danoEnemigo);
            vista.mostrarMensajeCombate(enemigo.getNombre() + " ataca e inflige " + danoEnemigo + " de daño.");
        } else {
            vista.mostrarMensajeCombate(enemigo.getNombre() + " ataca al jugador, pero falla D:");
        }

        if (!jugador.estaVivo()) {
            vista.mostrarResultadoFinal("¡DERROTA! " + enemigo.getNombre() + " ha ganado. :´(");
            return;
        }

        vista.mostrarEstadoCombate(jugador, enemigo);
    }
}
