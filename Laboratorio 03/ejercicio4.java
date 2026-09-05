interface DispositivoImprimir {
	void imprimir();
}

interface DispositivoEscanear {
	void escanear();
}

class Impresora implements DispositivoImprimir {

	@Override
	public void imprimir() {
		System.out.println("La impresora está imprimiendo.");
	}
}

class ImpresoraMultifuncional implements DispositivoImprimir, DispositivoEscanear {

	@Override
	public void imprimir() {
		System.out.println("La impresora multifuncional está imprimiendo.");
	}

	@Override
	public void escanear() {
		System.out.println("La impresora multifuncional está escaneando.");
	}
}

public class Main {
	public static void main(String[] args) {
		Impresora ip1 = new Impresora();
		ImpresoraMultifuncional ipm1 = new ImpresoraMultifuncional();

		ip1.imprimir();
		ipm1.imprimir();
		ipm1.escanear();
	}
}
