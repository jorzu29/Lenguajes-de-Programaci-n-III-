interface Forma{
    void dibujar();
}
class Cuadrado implements Forma{
    public void dibujar(){
        System.out.println("dibujando cuadrado");
    }
}
class Circulo implements Forma{
    public void dibujar(){
        System.out.println("dibujando circulo");
    }
}
class Triangulo implements Forma{
    public void dibujar(){
        System.out.println("dibujando triangulo");
    }
}
public class Main{
    public static void main(String[]args){
        Forma form1= new Cuadrado();
        form1.dibujar();
        
        Forma form2=new Circulo();
        form2.dibujar();
        
        Forma form3=new Triangulo();
        form3.dibujar();
    }
}
