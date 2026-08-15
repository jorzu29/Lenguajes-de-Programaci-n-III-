import java.util.Scanner;

public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int horas = 0;
	do {
		System.out.println("Ingrese el numero de horas: ");
		horas = scanner.nextInt();
	} while (horas<=0);

	cargoTotal(horas);
}
	
public static void cargoTotal(int horas) {
	double cargo = 3 + (0.50 * (horas - 1));
	if (cargo > 12) {
			cargo = 12;
	}
	System.out.println("El cargo total será de: S/" + cargo);
}
