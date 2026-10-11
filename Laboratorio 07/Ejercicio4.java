import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Ejercicio4 {

    public static void main(String[] args) {
        File archivo = seleccionarArchivo();
        if (archivo != null) {
            analizarArchivo(archivo);
        }
    }
  
    private static File seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        File archivo = null;

        while (archivo == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el archivo a analizar.");
            int resultado = fileChooser.showOpenDialog(null);

            if (resultado == JFileChooser.APPROVE_OPTION) {
                File seleccionado = fileChooser.getSelectedFile();
                if (seleccionado.exists() && seleccionado.isFile()) {
                    archivo = seleccionado;
                } else {
                    JOptionPane.showMessageDialog(null, "El archivo no existe o es inválido. Intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.");
                System.exit(0);
            }
        }
        return archivo;
    }

    private static void analizarArchivo(File archivo) {
        int totalLineas = 0;
        long totalCaracteres = 0;
        int totalPalabras = 0;
        List<String> listaPalabras = new ArrayList<>();
        List<Integer> listaConteos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();

                StringBuilder palabraActual = new StringBuilder();
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);
                    if (Character.isLetterOrDigit(c)) {
                        palabraActual.append(c);
                    } else {
                        if (palabraActual.length() > 0) {
                            registrarPalabra(palabraActual.toString(), listaPalabras, listaConteos);
                            totalPalabras++;
                            palabraActual.setLength(0);
                        }
                    }
                }
                if (palabraActual.length() > 0) {
                    registrarPalabra(palabraActual.toString(), listaPalabras, listaConteos);
                    totalPalabras++;
                }
            }

            System.out.println("\n--- ESTADÍSTICAS DEL ARCHIVO: " + archivo.getName() + " ---");
            System.out.println("o Total de líneas: " + totalLineas);
            System.out.println("o Total de palabras: " + totalPalabras);
            System.out.println("o Total de caracteres (sin fin de línea): " + totalCaracteres);
            
            double promedio = (totalLineas > 0) ? (double) totalPalabras / totalLineas : 0;
            System.out.printf("o Promedio de palabras por línea: %.2f\n", promedio);

            if (!listaConteos.isEmpty()) {
                int maxFreq = 0;
                for (int freq : listaConteos) {
                    if (freq > maxFreq) maxFreq = freq;
                }
                System.out.println("o Palabra más frecuente (Apariciones: " + maxFreq + "):");
                for (int i = 0; i < listaPalabras.size(); i++) {
                    if (listaConteos.get(i) == maxFreq) {
                        System.out.println("   - " + listaPalabras.get(i) + ": " + listaConteos.get(i) + " veces");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void registrarPalabra(String palabra, List<String> palabras, List<Integer> conteos) {
        String p = palabra.toLowerCase();
        int index = palabras.indexOf(p);
        if (index != -1) {
            conteos.set(index, conteos.get(index) + 1);
        } else {
            palabras.add(p);
            conteos.add(1);
        }
    }
}
