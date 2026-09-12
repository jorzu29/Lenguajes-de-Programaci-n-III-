class Numero {
    private double valor;

    Numero(double valor) {
        this.valor = valor;
    }
    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
        this.valor = valor;
    }
    public double getValor() {
        return valor;
    }
}
public class Main {
    public static void main(String[] args) {
        Numero num1 = new Numero(0);
        try {
            num1.setValor(-2.3);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
