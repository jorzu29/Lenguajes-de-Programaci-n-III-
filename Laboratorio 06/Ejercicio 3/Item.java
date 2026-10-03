public class Item {
    private String nombre;
    private int cantidad;
    private String tipo;
    private String descripcion;
    private int danioBase;
    
    public Item(String nombre, int cantidad, String tipo, String descripcion){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
    public Item(String nombre, int cantidad, String tipo, String descripcion, int danoBase) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.danioBase = danioBase;
    }
    
    public void usarItem() {
        if (cantidad > 0) {
            cantidad--;
            System.out.println("Se uso " + nombre + " | Cantidad restante: " + cantidad);
        } else{
            System.out.println("No quedan unidades de " + nombre + "!!!");
        }
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getDanioBase() { 
        return danioBase; 
    }
    public void setDanioBase(int danioBase) { 
        this.danioBase = danioBase; 
    }
}
