import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class LeerEntrada {
    private Reader stream;

    public LeerEntrada(InputStream fuente) {
        this.stream = new InputStreamReader(fuente);
    }

    public char getChar() throws IOException {
        return (char) this.stream.read();
    }
}

class ExcepcionVocal extends Exception {
    public ExcepcionVocal(char c) {
        super("Excepción: Se ha ingresado la vocal '" + c + "'");
    }
}

class ExcepcionNumero extends Exception {
    public ExcepcionNumero(char c) {
        super("Excepción: Se ha ingresado el número '" + c + "'");
    }
}

class ExcepcionBlanco extends Exception {
    public ExcepcionBlanco() {
        super("Excepción: Se ha ingresado un espacio en blanco.");
    }
}

class ExcepcionSalida extends Exception {
    public ExcepcionSalida(char c) {
        super("Excepción de Salida: Se ingresó el carácter '" + c + "'. Finalizando el programa...");
    }
}

public class Main {

    private LeerEntrada lector;

    public Main() {
        this.lector = new LeerEntrada(System.in);
    }

    public void procesar() throws ExcepcionVocal, ExcepcionNumero, ExcepcionBlanco, ExcepcionSalida, IOException {
        char c = lector.getChar();

        if (c == '\n' || c == '\r') {
            return;
        }

        if (c == 'q' || c == 'Q') {
            throw new ExcepcionSalida(c);
        } else if ("aeiouAEIOU".indexOf(c) != -1) {
            throw new ExcepcionVocal(c);
        } else if (Character.isDigit(c)) {
            throw new ExcepcionNumero(c);
        } else if (Character.isWhitespace(c)) {
            throw new ExcepcionBlanco();
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        System.out.println("Ingresa caracteres (Presiona 'q' o 'Q' para salir):");

        while (true) {
            try {
                app.procesar();
            } catch (ExcepcionVocal | ExcepcionNumero | ExcepcionBlanco e) {
                System.out.println(e.getMessage());
            } catch (ExcepcionSalida e) {
                System.out.println(e.getMessage());
                break;
            } catch (IOException e) {
                System.err.println("Error al leer la entrada: " + e.getMessage());
            }
        }
    }
}
