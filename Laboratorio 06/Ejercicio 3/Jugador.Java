public class Jugador{
    private String nombre;
    private int salud;
    private int saludMax;
    private int nivel;
    private InventarioModel inventario;
    private Item arma;
    
    public Jugador(String nombre, int salud, int nivel, InventarioModel inventario) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMax = salud;
        this.nivel = nivel;
        this.inventario = inventario;
        this.arma = null;
    }
    
    public int atacar(){
        if (arma != null && arma.getTipo().equalsIgnoreCase("Arma")){
            return arma.getDanioBase() + (nivel*2); //Ataque con arma >:)
        }
        return 5 + nivel; //Ataque sin arma :/
    }
    
    public boolean usarObjeto(String nombreObjeto){
        Item item = inventario.buscarItem(nombreObjeto);
        if (item != null && item.getCantidad() > 0){
            if (item.getTipo().equalsIgnoreCase("Poción")){
                salud = Math.min(saludMax,salud + 30); //Devuelve el menor valor (para no sobreparas el maximo)
                item.usarItem();
                if (item.getCantidad() == 0){
                    inventario.eliminarItem(item); 
                }
                return true;
            } else if (item.getTipo().equalsIgnoreCase("Arma")){
                arma = item;
                return true;
            }
        }
        return false;
    }
    
    public void recibirDano (int danio) {
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
    public int getSaludMax() { 
        return saludMax; 
    }
    public int getNivel() { 
        return nivel; 
    }
    public InventarioModel getInventario() { 
        return inventario; 
    }
    public Item getArma() { 
        return arma; 
    }
    public void setArma(Item arma) { 
        this.arma = arma; 
    }
}
