import java.util.Scanner;

public class practica {

    public static void main(String[]args) {

        Scanner teclado=new Scanner(System.in);

        int[] numeros=new int[10];

        System.out.println("ingrese 10 numeros");

        for(int i=0;i<10;i++) {

            System.out.print("numero: ");
            int numero=teclado.nextInt();

            if(i==0) {
                numeros[i]=numero;
            } else {

                while(numero<=numeros[i-1]) {
                    System.out.println("debe ser mayor al anterior");
                    System.out.print("numero: ");
                    numero=teclado.nextInt();
                }

                numeros[i]=numero;
            }
        }

        System.out.println("numeros ingresados:");

        for(int i=0;i<10;i++) {
            System.out.println(numeros[i]);
        }
    }
}
