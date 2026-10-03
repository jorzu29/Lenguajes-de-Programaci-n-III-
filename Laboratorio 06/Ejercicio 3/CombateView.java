public class CombateView {

    public void mostrarEstadoCombate(Jugador jugador, Enemigo enemigo) {
        System.out.println("\n=================== Estado del combate >:D===================");
        System.out.println(" Jugador: " + jugador.getNombre() + " (Nv. " + jugador.getNivel() + ")");
        System.out.println(" HP: " + jugador.getSalud() + "/" + jugador.getSaludMax());
        System.out.println(" Arma equipada: " + (jugador.getArma() != null ? jugador.getArma().getNombre() : "Ninguna (Ataque desarmado)"));
        System.out.println(" ---------------------------------------------------------");
        System.out.println(" Enemigo: " + enemigo.getNombre() + " (" + enemigo.getTipo() + " Nv. " + enemigo.getNivel() + ")");
        System.out.println(" HP: " + enemigo.getSalud());
        System.out.println("==========================================================\n");
    }

    public void mostrarMensajeCombate(String mensaje) {
        System.out.println("[COMBATE]: " + mensaje);
    }

    public void mostrarResultadoFinal(String resultado) {
        System.out.println("Restultado Final: ");
        System.out.println(" " + resultado);
    }
}
