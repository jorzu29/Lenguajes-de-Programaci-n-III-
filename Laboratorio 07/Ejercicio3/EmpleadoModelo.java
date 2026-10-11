import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoModelo {
	private static final String texto = "empleados.txt";

	public List<Empleado> leerEmpleados() {
		List<Empleado> lista = new ArrayList<>();
		File archivo = new File(texto);
		if (!archivo.exists()) return lista;

		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(",");
				if(partes.length == 3) {
					int num = Integer.valueOf(partes[0]);
					String nom = partes[1];
					double sue = Double.valueOf(partes[2]);
					lista.add(new Empleado(num, nom, sue));
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
		return lista;
	}
	
	public void guardarEmpleados(List<Empleado> lista) {
	    try (PrintWriter pw = new PrintWriter(new FileWriter(texto))) {
	        for (Empleado e : lista) {
	            pw.println(e.getNumero() + "," + e.getNombre() + "," + e.getSueldo());
	        }
	    } catch (IOException e) {
	        System.out.println("Error al escribir archivo: " + e.getMessage());
	    }
	}
	
	public void agregarEmpleado(Empleado emp) throws IOException {
	    List<Empleado> lista = leerEmpleados();
	    for (Empleado e: lista) {
	        if (e.getNumero() == emp.getNumero()) {
	            throw new IllegalArgumentException("Numero repetido D:");
	        } 
	    }
	    lista.add(emp);
	    guardarEmpleados(lista);
	}
	
	public Empleado buscarEmpleado(int numero) {
	    List<Empleado> lista = leerEmpleados();
	    for (Empleado e : lista) {
	        if (e.getNumero() == numero) {
	            return e;
	        }
	    }
	    return null;
	}
	
	public boolean eliminarEmpleado(int numero) {
        List<Empleado> lista = leerEmpleados();
        boolean eliminado = lista.removeIf(e -> e.getNumero() == numero);
        if (eliminado) {
            guardarEmpleados(lista);
        }
        return eliminado;
    }
}
