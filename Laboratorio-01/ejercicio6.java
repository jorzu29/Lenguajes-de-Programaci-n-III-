import java.util.Scanner;
public class practica {
    public static void main(String[]args) {
        Scanner teclado=new Scanner(System.in);
        int horas;
        int minutos;
        int segundos;
        int total;
        System.out.print("ingrese las horas: ");
        horas=teclado.nextInt();
        System.out.print("ingrese los minutos: ");
        minutos=teclado.nextInt();
        System.out.print("ingrese los segundos: ");
        segundos=teclado.nextInt();
        total=horas*3600+minutos*60+segundos;
        System.out.println("el total en segundos es: "+total);
    }
}
