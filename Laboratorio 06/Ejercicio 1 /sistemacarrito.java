import java.util.ArrayList;
class Articulo{
    String nombre;
    double precio;
    String categoria;
    Articulo(String nombre,double precio,String categoria){
        this.nombre=nombre;
        this.precio=precio;
        this.categoria=categoria;
    }
    public String getNombre(){
        return nombre;
    }
    public double getPrecio(){
        return precio;
    }
    public String getCategoria(){
        return categoria;
    }
}
class Cesta{
    ArrayList<Articulo> articulos;
    Cesta(){
        articulos=new ArrayList<>();
    }
    public void agregarArticulo(Articulo articulo){
        articulos.add(articulo);
    }
    public void mostrarArticulos(){
        for(Articulo articulo:articulos){
            System.out.println(articulo.getNombre()+" - S/ "+articulo.getPrecio());
        }
    }
    public void eliminarArticulo(String nombre){
        for(int i=0;i<articulos.size();i++){
            if(articulos.get(i).getNombre().equals(nombre)){
                articulos.remove(i);
                break;
            }
        }
    }
    public double calcularTotal(){
        double total=0;
        for(Articulo articulo:articulos){
            total=total+articulo.getPrecio();
        }
        return total;
    }
}
class Tienda{
    ArrayList<Articulo> articulos;
    ArrayList<Double> historial;
    Cesta cesta;
    Tienda(){
        articulos=new ArrayList<>();
        historial=new ArrayList<>();
        cesta=new Cesta();
    }
    public void agregarArticulo(Articulo articulo){
        articulos.add(articulo);
    }
    public void mostrarArticulos(){
        for(Articulo articulo:articulos){
            System.out.println(articulo.getNombre()+" - S/ "+articulo.getPrecio()+" - "+articulo.getCategoria());
        }
    }
    public void agregarACesta(String nombre){
        for(Articulo articulo:articulos){
            if(articulo.getNombre().equals(nombre)){
                cesta.agregarArticulo(articulo);
                break;
            }
        }
    }
    public void eliminarDeCesta(String nombre){
        cesta.eliminarArticulo(nombre);
    }
    public double aplicarDescuento(){
        double total=cesta.calcularTotal();
        if(total>=100){
            total=total*0.9;
        }
        return total;
    }
    public double calcularEnvio(){
        double total=cesta.calcularTotal();
        if(total>=150){
            return 0;
        }
        return 10;
    }
    public void comprar(){
        double total=aplicarDescuento();
        double envio=calcularEnvio();
        total=total+envio;
        historial.add(total);
        System.out.println("Total a pagar: S/ "+total);
        cesta.articulos.clear();
    }
    public void mostrarHistorial(){
        for(double compra:historial){
            System.out.println("Compra: S/ "+compra);
        }
    }
}

class Pantalla{
    public void mostrarArticulos(Tienda tienda){
        tienda.mostrarArticulos();
    }
    public void mostrarCesta(Cesta cesta){
        cesta.mostrarArticulos();
    }
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}
class Gestion{
    Tienda tienda;
    Pantalla pantalla;
    Gestion(Tienda tienda,Pantalla pantalla){
        this.tienda=tienda;
        this.pantalla=pantalla;
    }
    public void agregarArticulo(Articulo articulo){
        tienda.agregarArticulo(articulo);
    }
    public void mostrarArticulos(){
        pantalla.mostrarArticulos(tienda);
    }
    public void agregarACesta(String nombre){
        tienda.agregarACesta(nombre);
    }
    public void verCesta(){
        pantalla.mostrarCesta(tienda.cesta);
    }
    public void eliminarDeCesta(String nombre){
        tienda.eliminarDeCesta(nombre);
    }
    public void comprar(){
        tienda.comprar();
    }
    public void mostrarHistorial(){
        tienda.mostrarHistorial();
    }
}

public class Main{
    public static void main(String[]args){

        Tienda tienda=new Tienda();
        Pantalla pantalla=new Pantalla();
        Gestion gestion=new Gestion(tienda,pantalla);

        Articulo laptop=new Articulo("Laptop",2500,"Tecnologia");
        Articulo celular=new Articulo("Celular",1200,"Tecnologia");

        gestion.agregarArticulo(laptop);
        gestion.agregarArticulo(celular);

        System.out.println("=== PRODUCTOS ===");
        gestion.mostrarArticulos();

        System.out.println("=== AGREGAR A LA CESTA ===");
        gestion.agregarACesta("Laptop");
        gestion.verCesta();

        System.out.println("=== ELIMINAR  ===");
        gestion.eliminarDeCesta("Laptop");
        gestion.verCesta();

        System.out.println("=== AGREGAR  Y COMPRAR ===");
        gestion.agregarACesta("Celular");
        gestion.comprar();

        System.out.println("=== HISTORIAL  ===");
        gestion.mostrarHistorial();
    }
}
