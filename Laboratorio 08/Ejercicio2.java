package Ejercicios;

import java.sql.*;
import java.util.*;

class Producto {
    int id;
    String nombre;
    double precio;
    int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id; this.nombre = nombre; this.precio = precio; this.stock = stock;
    }
}

public class Ejercicio2 {
    private static final String URL = "jdbc:sqlite:inventario.db";
    private static final String CLAVE = "admin123";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL); Scanner sc = new Scanner(System.in)) {
            conn.setAutoCommit(false);

            conn.createStatement().execute("CREATE TABLE IF NOT EXISTS producto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, precio REAL, stock INTEGER)");

            int op;
            do {
                System.out.println("\n1. Mostrar (Consulta Java) \n2. Ingresar \n3. Actualizar \n4. Borrar \n5. Salir");
                System.out.print("Elija opción: ");
                op = sc.nextInt(); sc.nextLine();

                if (op == 1) { 
                    List<Producto> lista = cargar(conn);
                    if (lista.isEmpty()) { System.out.println("Vacío."); continue; }

                    System.out.print("¿Mostrar ID? (s/n): "); boolean vId = sc.next().equalsIgnoreCase("s");
                    System.out.print("¿Mostrar Nombre? (s/n): "); boolean vNom = sc.next().equalsIgnoreCase("s");
                    System.out.print("¿Mostrar Precio? (s/n): "); boolean vPre = sc.next().equalsIgnoreCase("s");
                    System.out.print("¿Mostrar Stock? (s/n): "); boolean vSto = sc.next().equalsIgnoreCase("s");
                    sc.nextLine();

                    System.out.print("Filtrar por (precio/stock o enter para ninguno): ");
                    String campo = sc.nextLine().trim();
                    double val = 0;
                    if (!campo.isEmpty()) {
                        System.out.print("Valor mínimo: "); val = sc.nextDouble(); sc.nextLine();
                    }

                    System.out.print("Ordenar por (precio/stock/nombre o enter): ");
                    String ord = sc.nextLine().trim();
                    System.out.print("¿Ascendente? (true/false): "); boolean asc = sc.nextBoolean(); sc.nextLine();

                    System.out.print("Límite de registros (0 para sin límite): "); int lim = sc.nextInt(); sc.nextLine();

                    List<Producto> res = new ArrayList<>();
                    for (Producto p : lista) {
                        if (campo.equalsIgnoreCase("precio") && p.precio < val) continue;
                        if (campo.equalsIgnoreCase("stock") && p.stock < val) continue;
                        res.add(p);
                    }

                    if (!ord.isEmpty()) {
                        res.sort((a, b) -> {
                            int comp = 0;
                            if (ord.equalsIgnoreCase("precio")) comp = Double.compare(a.precio, b.precio);
                            else if (ord.equalsIgnoreCase("stock")) comp = Integer.compare(a.stock, b.stock);
                            else if (ord.equalsIgnoreCase("nombre")) comp = a.nombre.compareTo(b.nombre);
                            return asc ? comp : -comp;
                        });
                    }

                    System.out.println("\n--- RESULTADOS ---");
                    int count = 0;
                    for (Producto p : res) {
                        if (lim > 0 && count >= lim) break;
                        String out = "";
                        if (vId) out += "ID:" + p.id + " ";
                        if (vNom) out += "Nom:" + p.nombre + " ";
                        if (vPre) out += "Pre:" + p.precio + " ";
                        if (vSto) out += "Sto:" + p.stock;
                        System.out.println(out);
                        count++;
                    }

                } else if (op == 2) { // Ingresar
                    System.out.print("Nombre: "); String n = sc.nextLine();
                    System.out.print("Precio: "); double p = sc.nextDouble();
                    System.out.print("Stock: "); int s = sc.nextInt(); sc.nextLine();
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO producto (nombre, precio, stock) VALUES (?, ?, ?)");
                    ps.setString(1, n); ps.setDouble(2, p); ps.setInt(3, s); ps.executeUpdate();
                    confirmar(conn, sc);

                } else if (op == 3) { // Actualizar
                    System.out.print("ID a actualizar: "); int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo nombre: "); String n = sc.nextLine();
                    System.out.print("Nuevo precio: "); double p = sc.nextDouble();
                    System.out.print("Nuevo stock: "); int s = sc.nextInt(); sc.nextLine();
                    PreparedStatement ps = conn.prepareStatement("UPDATE producto SET nombre=?, precio=?, stock=? WHERE id=?");
                    ps.setString(1, n); ps.setDouble(2, p); ps.setInt(3, s); ps.setInt(4, id); ps.executeUpdate();
                    confirmar(conn, sc);

                } else if (op == 4) { // Borrar
                    System.out.print("ID a borrar: "); int id = sc.nextInt(); sc.nextLine();
                    PreparedStatement ps = conn.prepareStatement("DELETE FROM producto WHERE id=?");
                    ps.setInt(1, id); ps.executeUpdate();
                    confirmar(conn, sc);
                }
            } while (op != 5);

        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static List<Producto> cargar(Connection conn) throws SQLException {
        List<Producto> l = new ArrayList<>();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM producto");
        while (rs.next()) l.add(new Producto(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
        return l;
    }

    private static void confirmar(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Ingrese clave: ");
        if (sc.nextLine().equals(CLAVE)) { conn.commit(); System.out.println("Guardado."); }
        else { conn.rollback(); System.out.println("Revertido."); }
    }
}
