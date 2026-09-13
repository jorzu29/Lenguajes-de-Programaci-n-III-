class DivisionPorCeroException extends ArithmeticException {
    public DivisionPorCeroException(String mensaje) {
        super(mensaje);
    }
}

public class Main {

    public double sumar(double a, double b) {
        validarNumero(a);
        validarNumero(b);
        return a + b;
    }

    public double restar(double a, double b) {
        validarNumero(a);
        validarNumero(b);
        return a - b;
    }

    public double multiplicar(double a, double b) {
        validarNumero(a);
        validarNumero(b);
        return a * b;
    }

    public double dividir(double a, double b) throws DivisionPorCeroException {
        validarNumero(a);
        validarNumero(b);
        if (b == 0) {
            throw new DivisionPorCeroException("Error: No es posible dividir entre cero.");
        }
        return a / b;
    }

    private void validarNumero(double n) {
        if (Double.isNaN(n) || Double.isInfinite(n)) {
            throw new IllegalArgumentException("Error: El valor ingresado no es un número válido.");
        }
    }

    public static void main(String[] args) {
        Main calc = new Main();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- CALCULADORA BÁSICA ---");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();

            if (opcion == 5) {
                System.out.println("Saliendo de la calculadora...");
                continuar = false;
                break;
            }

            if (opcion < 1 || opcion > 5) {
                System.out.println("Opción no válida. Intenta de nuevo.");
                continue;
            }

            System.out.print("Ingresa el primer número: ");
            double num1 = scanner.nextDouble();
            System.out.print("Ingresa el segundo número: ");
            double num2 = scanner.nextDouble();

            try {
                double resultado = 0;
                switch (opcion) {
                    case 1:
                        resultado = calc.sumar(num1, num2);
                        break;
                    case 2:
                        resultado = calc.restar(num1, num2);
                        break;
                    case 3:
                        resultado = calc.multiplicar(num1, num2);
                        break;
                    case 4:
                        resultado = calc.dividir(num1, num2);
                        break;
                }
                System.out.println("Resultado: " + resultado);

            } catch (ArithmeticException e) {
                System.err.println("Capturada ArithmeticException / DivisionPorCeroException: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.err.println("Capturada IllegalArgumentException: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
