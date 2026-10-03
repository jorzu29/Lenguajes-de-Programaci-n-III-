import java.util.List;

public class InventarioController {
    private InventarioModel modelo;
    private InventarioView vista;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarItem(Item item) {
        modelo.agregarItem(item);
        vista.mostrarMensaje("Ítem '" + item.getNombre() + " agregado!");
    }

    public void eliminarItem(String nombre) {
        Item item = modelo.buscarItem(nombre);
        if (item != null) {
            modelo.eliminarItem(item);
            vista.mostrarMensaje("Ítem '" + nombre + " eliminado!");
        } else {
            vista.mostrarMensaje(nombre + "no encontrado");
        }
    }

    public void verInventario() {
        List<Item> items = modelo.obtenerItems();
        vista.mostrarInventario(items);
    }

    public void mostrarDetalles(String nombre) {
        Item item = modelo.buscarItem(nombre);
        vista.mostrarDetallesItem(item);
    }

    public Item buscarItem(String nombre) {
        return modelo.buscarItem(nombre);
    }
}
