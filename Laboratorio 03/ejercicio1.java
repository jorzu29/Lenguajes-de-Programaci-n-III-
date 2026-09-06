class Empleado {
    private String nombre;
    private double salario;
    private String departamento;
    public Empleado(String nombre, double salario, String departamento) {
        this.nombre = nombre;
        this.salario = salario;
        this.departamento = departamento;
    }
    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void MostrarDatos() {
        System.out.println("nombre: " + nombre);
        System.out.println("sueldo x mes: " + salario);
        System.out.println("departamento: " + departamento);
    }
}
class CalculoPago {
    public double calcularPago(Empleado empleado, int diasTrabajados) {
        return (empleado.getSalario() / 30) * diasTrabajados;
    }
    public double calcularHorasExtras(Empleado empleado, int horasExtras) {
        return ((empleado.getSalario() / 30) / 8) * horasExtras;
    }
}

public class Main {
    public static void main(String[] args) {
        Empleado emple1 = new Empleado("kevin", 1250.00, "secretaria");
        CalculoPago calculo = new CalculoPago();
        double pagoTotal = calculo.calcularPago(emple1, 23);
        double pagoHorasExtras = calculo.calcularHorasExtras(emple1, 5);
        emple1.MostrarDatos();
        System.out.println("salario por dias trabajados: " + pagoTotal);
        System.out.println("su pago por las horas es: "+ pagoHorasExtras);
    }
}
