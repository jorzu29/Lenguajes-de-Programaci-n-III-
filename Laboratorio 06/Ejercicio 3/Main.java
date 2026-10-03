public class Main {
    public static void main(String[] args) {
    
        InventarioModel inventario = new InventarioModel();
        Item espada = new Item("Espada de Acero", 1, "Arma", "Corta con precisión.", 25);
        Item pocion = new Item("Poción de Salud", 2, "Poción", "Cura 30 HP.");

        inventario.agregarItem(espada);
        inventario.agregarItem(pocion);

     
        Jugador jugador = new Jugador("Héroe", 100, 5, inventario);
        Enemigo enemigo = new Enemigo("Orco Berserker", 80, 4, "Bestia");

 
        InventarioView inventarioView = new InventarioView();
        InventarioController inventarioController = new InventarioController(inventario, inventarioView);

        CombateView combateView = new CombateView();
        CombateController combateController = new CombateController(jugador, enemigo, combateView);

        combateView.mostrarEstadoCombate(jugador, enemigo);

        // Equipar arma
        jugador.usarObjeto("Espada de Acero");
        combateView.mostrarMensajeCombate(jugador.getNombre() + " se ha equipado " + espada.getNombre() + "\n");

        // Ataque
        System.out.println("XXXXXXXXXXXXXX Turno 1 XXXXXXXXXXXXXX");
        combateController.Turno("atacar", null);

        // Usa pocion
        System.out.println("XXXXXXXXXXXXXX Turno 2 XXXXXXXXXXXXXX");
        combateController.Turno("usar", "Poción de Salud");

        // Ataque
        System.out.println("XXXXXXXXXXXXXX Turno 3 XXXXXXXXXXXXXX");
        combateController.Turno("atacar", null);

        inventarioController.verInventario();
    }
}
