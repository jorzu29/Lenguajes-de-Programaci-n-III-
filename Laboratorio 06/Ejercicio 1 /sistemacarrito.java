import java.util.ArrayList;

class Articulo{
    String nombre;
    double precio;

    Articulo(String nombre,double precio){
        this.nombre=nombre;
        this.precio=precio;
    }
}

class Cesta{
    ArrayList<Articulo> articulos=new ArrayList<>();

    public void agregar(Articulo articulo){
        articulos.add(articulo);
    }

    public void eliminar(String nombre){
        for(int i=0;i<articulos.size();i++){
            if(articulos.get(i).nombre.equals(nombre)){
                articulos.remove(i);
                break;
            }
        }
    }

    public void mostrar(){
        for(Articulo articulo:articulos){
            System.out.println(articulo.nombre+" - S/ "+articulo.precio);
        }
    }

    public double total(){
        double total=0;

        for(Articulo articulo:articulos){
            total=total+articulo.precio;
        }

        return total;
    }
}

class Tienda{
    ArrayList<Articulo> articulos=new ArrayList<>();
    ArrayList<Double> historial=new ArrayList<>();
    Cesta cesta=new Cesta();

    public void agregar(Articulo articulo){
        articulos.add(articulo);
    }

    public void mostrar(){
        for(Articulo articulo:articulos){
            System.out.println(articulo.nombre+" - S/ "+articulo.precio);
        }
    }

    public void agregarCesta(String nombre){
        for(Articulo articulo:articulos){
            if(articulo.nombre.equals(nombre)){
                cesta.agregar(articulo);
                break;
            }
        }
    }

    public void comprar(){
        double total=cesta.total();

        if(total>=100){
            total=total*0.9;
        }

        if(cesta.total()<150){
            total=total+10;
        }

        historial.add(total);

        System.out.println("Total a pagar: S/ "+total);

        cesta.articulos.clear();
    }
}

class Pantalla{
    public void productos(Tienda tienda){
        tienda.mostrar();
    }

    public void cesta(Cesta cesta){
        cesta.mostrar();
    }

    public void historial(ArrayList<Double> historial){
        for(double compra:historial){
            System.out.println("Compra: S/ "+compra);
        }
    }
}

class Gestion{
    Tienda tienda;
    Pantalla pantalla;

    Gestion(Tienda tienda,Pantalla pantalla){
        this.tienda=tienda;
        this.pantalla=pantalla;
    }

    public void agregar(Articulo articulo){
        tienda.agregar(articulo);
    }

    public void mostrarProductos(){
        pantalla.productos(tienda);
    }

    public void agregarCesta(String nombre){
        tienda.agregarCesta(nombre);
    }

    public void mostrarCesta(){
        pantalla.cesta(tienda.cesta);
    }

    public void eliminar(String nombre){
        tienda.cesta.eliminar(nombre);
    }

    public void comprar(){
        tienda.comprar();
    }

    public void mostrarHistorial(){
        pantalla.historial(tienda.historial);
    }
}

public class Main{
    public static void main(String[]args){

        Tienda tienda=new Tienda();
        Pantalla pantalla=new Pantalla();
        Gestion gestion=new Gestion(tienda,pantalla);

        Articulo laptop=new Articulo("Laptop",2500);
        Articulo celular=new Articulo("Celular",1200);

        gestion.agregar(laptop);
        gestion.agregar(celular);

        System.out.println("=== PRODUCTOS ===");
        gestion.mostrarProductos();

        System.out.println("=== CESTA ===");
        gestion.agregarCesta("Laptop");
        gestion.mostrarCesta();

        System.out.println("=== ELIMINAR ===");
        gestion.eliminar("Laptop");
        gestion.mostrarCesta();

        System.out.println("=== COMPRA ===");
        gestion.agregarCesta("Celular");
        gestion.comprar();

        System.out.println("=== HISTORIAL ===");
        gestion.mostrarHistorial();
    }
}
