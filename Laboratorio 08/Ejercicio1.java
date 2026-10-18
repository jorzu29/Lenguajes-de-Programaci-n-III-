package Ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio1 {
	private static final String URL = "jdbc:sqlite:inventario.db";
	private static final String CLAVE_SECRETA = "admin123"; // Clave para confirmar cambios

	private static void mostrarDatos(Connection conn) throws SQLException {
		String sql = "SELECT * FROM producto";
		try (Statement stmt = conn.createStatement();
			        ResultSet rs = stmt.executeQuery(sql)) {
			System.out.println("\n--- LISTA DE PRODUCTOS ---");
			while (rs.next()) {
				System.out.printf("ID: %d | Nombre: %s | Precio: %.2f | Stock: %d\n",
				                  rs.getInt("id"),
				                  rs.getString("nombre"),
				                  rs.getDouble("precio"),
				                  rs.getInt("stock"));
			}
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
			conn.commit(); // Confirma los cambios definitivamente
			System.out.println("¡Operación exitosa! Cambios guardados.");
		} else {
			conn.rollback(); // Revierte los cambios si la clave es incorrecta
			System.out.println("Clave incorrecta. Operación revertida.");
		}
	}


	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection(URL);
			        Scanner scanner = new Scanner(System.in)) {

			// Desactivar el auto-commit para controlar transacciones (confirmar/revertir)
			conn.setAutoCommit(false);

			// Crear la tabla con 4 campos si no existe
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
				System.out.println("1. Mostrar datos");
				System.out.println("2. Ingresar dato");
				System.out.println("3. Actualizar dato");
				System.out.println("4. Borrar dato");
				System.out.println("5. Salir");
				System.out.print("Elija una opción: ");
				opcion = scanner.nextInt();
				scanner.nextLine(); // Limpiar el buffer

				switch (opcion) {
				case 1:
					mostrarDatos(conn);
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

}
