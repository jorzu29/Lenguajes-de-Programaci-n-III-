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
    
    @Override
    public String toString(){
        return "Primer valor: " + valor1 + " | Segundo valor: " + valor2;
    }
}

public class Main {
    
    public static void main(String[] args) {
        Par<String, Double> alumno = new Par<>("Jose Perez", 15.6);
        System.out.println(alumno.toString());
        
        alumno.setValor1("Pepe Quispe");
        alumno.setValor2(19.5);
        System.out.println(alumno.toString());
    }
}
