class Par<F, S>{
    private F valor1;
    private S valor2;
    
    public Par(){}
    
    public Par(F valor1, S valor2){
        this.valor1 = valor1;
        this.valor2 = valor2;
    }
    
    public F getValor1(){
        return valor1;
    }
    
    public void setValor1(F valor1){
        this.valor1 = valor1;
    }
    
    public S getValor2(){
        return valor2;
    }
    
    public void setValor2(S valor2){
        this.valor2 = valor2;
    }
    
    public Boolean esIgual(){
        return valor1 == valor2;
    }
    
    @Override
    public String toString(){
        return "Primer valor: " + valor1 + " | Segundo valor: " + valor2;
    }
}

public class Main {
    
    public static void main(String[] args) {
        Par<Integer, Integer > par1 = new Par<>(10, 10);
        Par<String, Integer > par2 = new Par<>("Hola", 10);
        System.out.println("¿Primer par es igual? " + par1.esIgual());
        System.out.println("¿Segundo par es igual? " + par2.esIgual());
    }
}
