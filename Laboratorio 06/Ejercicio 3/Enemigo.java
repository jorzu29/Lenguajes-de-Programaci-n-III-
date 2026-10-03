public class Enemigo {
    private String nombre;
    private int salud;
    private int nivel;
    private String tipo;
    
    public Enemigo(String nombre, int salud, int nivel, String tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.tipo = tipo;
    }
    
    public int atacar() {
        return (nivel*4) + (int) (Math.random() * 5);
    }
    
    public void recibirDanio(int danio){
        this.salud = Math.max(0, this.salud - danio);
    }
    
    public boolean estaVivo() {
        return salud > 0;
    }
    
    public String getNombre() { 
        return nombre;
    }
    public int getSalud() { 
        return salud; 
    }
    public int getNivel() { 
        return nivel; 
    }
    public String getTipo() { 
        return tipo; 
    }
}
