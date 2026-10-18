package actividad1;

import java.sql.*;
import java.util.Scanner;

public class actividad1{

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Connection con = DriverManager.getConnection("jdbc:sqlite:basedatos.db");

        Statement st = con.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS Producto (id INTEGER PRIMARY KEY, nombre TEXT, precio REAL)");
        st.close();

        int opcion;

        do {
            System.out.println("\n1. Insertar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Actualizar precio");
            System.out.println("4. Borrar producto");
            System.out.println("5. Transaccion");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1 || opcion == 5) {

                if (opcion == 5) {
                    con.setAutoCommit(false);
                }

                try {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO Producto(nombre, precio) VALUES(?,?)");

                    ps.setString(1, nombre);
                    ps.setDouble(2, precio);
                    ps.executeUpdate();
                    ps.close();

                    if (opcion == 5) {
                        System.out.print("Guardar cambios? (s/n): ");
                        String respuesta = sc.nextLine();

                        if (respuesta.equalsIgnoreCase("s")) {
                            con.commit();
                            System.out.println("Cambios confirmados");
                        } else {
                            con.rollback();
                            System.out.println("Cambios cancelados");
                        }

                        con.setAutoCommit(true);
                    } else {
                        System.out.println("Producto registrado");
                    }

                } catch (Exception e) {
                    if (opcion == 5) {
                        con.rollback();
                        con.setAutoCommit(true);
                    }
                    System.out.println("Error: " + e.getMessage());
                }
            }

            if (opcion == 2) {
                PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Producto");

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(
                        rs.getInt("id") + " - " +
                        rs.getString("nombre") + " - " +
                        rs.getDouble("precio"));
                }

                rs.close();
                ps.close();
            }

            if (opcion == 3) {
                System.out.print("ID del producto: ");
                int id = sc.nextInt();

                System.out.print("Nuevo precio: ");
                double precio = sc.nextDouble();
                sc.nextLine();

                PreparedStatement ps = con.prepareStatement(
                    "UPDATE Producto SET precio = ? WHERE id = ?");

                ps.setDouble(1, precio);
                ps.setInt(2, id);

                ps.executeUpdate();
                ps.close();

                System.out.println("Precio actualizado");
            }

            if (opcion == 4) {
                System.out.print("ID del producto: ");
                int id = sc.nextInt();
                sc.nextLine();

                PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Producto WHERE id = ?");

                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();

                System.out.println("Producto eliminado");
            }

        } while (opcion != 0);

        con.close();
        sc.close();
    }
}
