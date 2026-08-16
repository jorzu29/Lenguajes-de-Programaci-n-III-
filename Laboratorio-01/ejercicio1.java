public class SumaArreglos {

	public static void main(String[] args) {
		int [] arreglo = {1, 2, 3, 4, 100};
		sumarArreglo(arreglo);
	}
	
	public static void sumarArreglo (int[] arreglo) {
		int suma = 0;
		for (int i=0; i < arreglo.length; i++) {
			suma += arreglo[i];
		}
		System.out.println("La suma total del arreglo es: " + suma);
	}

}
