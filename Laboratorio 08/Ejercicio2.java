package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
}

class GestorProductos {

    public static List<Producto> cargarRegistros(Connection conn) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar registros: " + e.getMessage());
        }
        return lista;
    }

    public static void consultarArreglo(List<Producto> productos, 
                                        boolean verId, boolean verNombre, boolean verPrecio, boolean verStock,
                                        String campoFiltro, String operador, double valorFiltro,
                                        String campoOrden, boolean ascendente, int limite) {
        
        List<Producto> resultado = productos.stream().filter(p -> {
            if (campoFiltro == null || campoFiltro.isEmpty()) return true;
            double valorCampo = 0;
            switch (campoFiltro.toLowerCase()) {
                case "precio": valorCampo = p.getPrecio(); break;
                case "stock": valorCampo = p.getStock(); break;
                case "id": valorCampo = p.getId(); break;
                default: return true;
            }
            
            switch (operador) {
                case ">": return valorCampo > valorFiltro;
                case "<": return valorCampo < valorFiltro;
                case "=": return valorCampo == valorFiltro;
                default: return true;
            }
        }).collect(Collectors.toList());

        if (campoOrden != null && !campoOrden.isEmpty()) {
            Comparator<Producto> comparador;
            switch (campoOrden.toLowerCase()) {
                case "precio": comparador = Comparator.comparingDouble(Producto::getPrecio); break;
                case "stock": comparador = Comparator.comparingInt(Producto::getStock); break;
                case "nombre": comparador = Comparator.comparing(Producto::getNombre); break;
                default: comparador = Comparator.comparingInt(Producto::getId); break;
            }
            if (!ascendente) {
                comparador = comparador.reversed();
            }
            resultado.sort(comparador);
        }

        if (limite > 0) {
            resultado = resultado.stream().limit(limite).collect(Collectors.toList());
        }

        System.out.println("\n--- RESULTADO DE LA CONSULTA EN MEMORIA ---");
        if (resultado.isEmpty()) {
            System.out.println("No se encontraron registros con los criterios especificados.");
            return;
        }

        for (Producto p : resultado) {
            String fila = "";
            if (verId) fila += "ID: " + p.getId() + " | ";
            if (verNombre) fila += "Nombre: " + p.getNombre() + " | ";
            if (verPrecio) fila += "Precio: " + p.getPrecio() + " | ";
            if (verStock) fila += "Stock: " + p.getStock();
            System.out.println(fila);
        }
        System.out.println("Total registros mostrados: " + resultado.size());
    }
}

public class Ejercicio2 {
    private static final String URL = "jdbc:sqlite:inventario.db";
    private static final String CLAVE_SECRETA = "admin123";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL);
             Scanner scanner = new Scanner(System.in)) {
            
            conn.setAutoCommit(false);

            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS producto (" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "nombre TEXT, " +
                             "precio REAL, " +
                             "stock INTEGER)");
            }

            int opcion;
            do {
                System.out.println("\n--- MENÚ PRINCIPAL ---");
                System.out.println("1. Mostrar datos (Consulta personalizada)");
                System.out.println("2. Ingresar dato");
                System.out.println("3. Actualizar dato");
                System.out.println("4. Borrar dato");
                System.out.println("5. Salir");
                System.out.print("Elija una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        List<Producto> listaProductos = GestorProductos.cargarRegistros(conn);
                        if (listaProductos.isEmpty()) {
                            System.out.println("La tabla está vacía.");
                            break;
                        }

                        System.out.println("\n[a] ¿Qué campos desea mostrar? (1: Sí / 0: No)");
                        System.out.print("¿Mostrar ID?: ");
                        boolean verId = scanner.nextInt() == 1;
                        System.out.print("¿Mostrar Nombre?: ");
                        boolean verNombre = scanner.nextInt() == 1;
                        System.out.print("¿Mostrar Precio?: ");
                        boolean verPrecio = scanner.nextInt() == 1;
                        System.out.print("¿Mostrar Stock?: ");
                        boolean verStock = scanner.nextInt() == 1;
                        scanner.nextLine();

                        System.out.println("\n[b] ¿Desea aplicar alguna condición de filtrado?");
                        System.out.print("Campo a evaluar (precio / stock / id o dejar vacío para ninguno): ");
                        String campoFiltro = scanner.nextLine().trim();
                        String operador = "";
                        double valorFiltro = 0;
                        if (!campoFiltro.isEmpty()) {
                            System.out.print("Operador (> , < , =): ");
                            operador = scanner.nextLine().trim();
                            System.out.print("Valor de comparación: ");
                            valorFiltro = scanner.nextDouble();
                            scanner.nextLine();
                        }

                        System.out.println("\n[c] ¿Cómo desea ordenar los registros?");
                        System.out.print("Campo para ordenar (precio / stock / nombre / id): ");
                        String campoOrden = scanner.nextLine().trim();
                        System.out.print("¿Desea orden ascendente? (true para ascendente / false para descendente): ");
                        boolean ascendente = scanner.nextBoolean();
                        scanner.nextLine();

                        System.out.println("\n[d] ¿Desea limitar la cantidad de registros a mostrar?");
                        System.out.print("Cantidad máxima (ingrese 0 si no desea límite): ");
                        int limite = scanner.nextInt();
                        scanner.nextLine();

                        GestorProductos.consultarArreglo(listaProductos, verId, verNombre, verPrecio, verStock,
                                                         campoFiltro, operador, valorFiltro, campoOrden, ascendente, limite);
                        break;

                    case 2:
                        ingresarDato(conn, scanner);
                        break;
                    case 3:
                        actualizarDato(conn, scanner);
                        break;
                    case 4:
                        borrarDato(conn, scanner);
                        break;
                    case 5:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 5);

        } catch (SQLException e) {
            System.out.println("Error en la Base de Datos: " + e.getMessage());
        }
    }

    private static void ingresarDato(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        String sql = "INSERT INTO producto (nombre, precio, stock) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.setInt(3, stock);
            pstmt.executeUpdate();
            verificarYConfirmar(conn, scanner);
        }
    }

    private static void actualizarDato(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nuevo precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Nuevo stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        String sql = "UPDATE producto SET nombre = ?, precio = ?, stock = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.setInt(3, stock);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            verificarYConfirmar(conn, scanner);
        }
    }

    private static void borrarDato(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("ID del producto a borrar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sql = "DELETE FROM producto WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            verificarYConfirmar(conn, scanner);
        }
    }

    private static void verificarYConfirmar(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese la clave de confirmación: ");
        String clave = scanner.nextLine();

        if (CLAVE_SECRETA.equals(clave)) {
            conn.commit();
            System.out.println("¡Operación exitosa! Cambios guardados.");
        } else {
            conn.rollback();
            System.out.println("Clave incorrecta. Operación revertida.");
        }
    }
}
