package prueba;
import java.util.Scanner;

public class menorDecimal {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		double[] numeros = new double[3];
		
		for (int i=0; i < numeros.length; i++) {
			System.out.println("Introduzca numero N°" + (i+1) +": ");
			numeros[i] = scanner.nextDouble();
		}
		numeroMenor(numeros);
	}
	
	public static void numeroMenor(double[] numeros) {
		double menor = Double.MAX_VALUE;
		for (int i=0; i < numeros.length; i++ ) {
			if (numeros[i] < menor) {
				menor = numeros[i];
			}
		}
		System.out.println("El menor numero es: " + menor);
	}
}
