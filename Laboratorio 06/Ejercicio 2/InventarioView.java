import java.util.List;

public class InventarioView {

    public void mostrarInventario(List<Item> items) {
        System.out.println("\n================ Inventario================");
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío D:");
        } else {
            for (Item item : items) {
                System.out.println("- " + item.getNombre() + " (x" + item.getCantidad() + ") [" + item.getTipo() + "]");
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("Mensaje de Sistema: " + mensaje);
    }

    public void mostrarDetallesItem(Item item) {
        System.out.println("\n------------ Detalles - Item ------------");
        if (item != null) {
            System.out.println("Nombre: " + item.getNombre());
            System.out.println("Cantidad: " + item.getCantidad());
            System.out.println("Tipo: " + item.getTipo());
            System.out.println("Descripción: " + item.getDescripcion());
        } else {
            System.out.println("El ítem no existe D:");
        }
    }
}

