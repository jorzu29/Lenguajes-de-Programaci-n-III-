interface Vehiculo {
	void acelerar();
}

class Bicicleta implements Vehiculo {

	@Override
	public void acelerar() {
		System.out.println("La bicicleta acelera pedaleando.");
	}
}

class Coche implements Vehiculo {
	@Override
	public void acelerar() {
		System.out.println("El coche acelera usando el motor.");
	}
}

public class Main {

	public static void main(String[] args) {

		Bicicleta bi1 = new Bicicleta();
		Coche co1 = new Coche();
		bi1.acelerar();
		co1.acelerar();

	}

}
