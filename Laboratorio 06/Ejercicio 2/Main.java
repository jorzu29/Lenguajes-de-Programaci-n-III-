public class Main {
    public static void main(String[] args) {

        InventarioModel modelo = new InventarioModel();
        InventarioView vista = new InventarioView();
        InventarioController controlador = new InventarioController(modelo, vista);


        Item espada = new Item("Espada Excalibur", 1, "Arma", "Una espada legendaria forjada en acero místico.");
        Item pocion = new Item("Poción de Salud", 5, "Pición", "Restaura 50 puntos de vida.");


        controlador.agregarItem(espada);
        controlador.agregarItem(pocion);


        controlador.verInventario();


        controlador.mostrarDetalles("Espada Excalibur");


        Item itemEncontrado = controlador.buscarItem("Poción de Salud");
        if (itemEncontrado != null) {
            itemEncontrado.usarItem();
        }

        controlador.eliminarItem("Espada Excalibur");


        controlador.verInventario();
    }
}
