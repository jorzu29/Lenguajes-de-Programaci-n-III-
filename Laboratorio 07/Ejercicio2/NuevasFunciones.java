import java.io.*;
import java.util.Scanner;
import java.util.Random;
class Personaje{
    String nombre;
    int vida;
    int ataque;
    int defensa;
    int alcance;
    
    Personaje(int vida,int ataque,int defensa,int alcance){
        this.vida=vida;
        this.ataque=ataque;
        this.defensa=defensa;
        this.alcance=alcance;
    }
 
    public void CrearPersonaje()throws Exception{
        RandomAccessFile archivo=new RandomAccessFile("PersonajesVideojuego.txt","rw");
        
        Scanner ingreso= new Scanner(System.in);
        System.out.println("ingresa el nombre del personaje ");
        nombre=ingreso.nextLine();
        this.nombre=nombre;
        
        String linea;
    
        while ((linea = archivo.readLine()) != null) {
            if (linea.contains(nombre)) {
                System.out.println("Personaje existente");
                archivo.close();
                return;
            }
        }
       
        System.out.println("ingresa la vida del personaje ");
        vida=ingreso.nextInt();
        while (vida<=0){
            System.out.println("ingrese un numero mayor");
            vida=ingreso.nextInt();
        }
        this.vida=vida;
        
        System.out.println("ingrese el ataque del personaje");
        ataque=ingreso.nextInt();
        while (ataque<=0){
            System.out.println("ingrese un numero mayor");
            ataque=ingreso.nextInt();
        }
        this.ataque=ataque;
        
        System.out.println("ingrese la defensa del personaje");
        defensa=ingreso.nextInt();
        while (defensa<=0){
            System.out.println("ingrese un numero mayor");
            defensa=ingreso.nextInt();
        }
        this.defensa=defensa;
        System.out.println("ingrese el alcance del personaje");
        alcance=ingreso.nextInt();
        while (alcance<=0){
            System.out.println("ingrese un numero mayor");
            alcance=ingreso.nextInt();
        }
        this.alcance=alcance;
        
        archivo.writeBytes("\n"+nombre+" "+vida+" "+ataque+" "+defensa+" "+alcance+"\n");
        archivo.close();
    }
}
    
class Gestor extends Personaje{
    Gestor(String nombre,int vida,int ataque,int defensa,int alcance){
        super(vida,ataque,defensa,alcance);
    }
    public void BorrarPersonaje()throws Exception{
        File archivo=new File("PersonajesVideojuego.txt");
        
        System.out.println("ingrese el nombre del personaje que desea borrar");
        Scanner ingreso=new Scanner(System.in);
        
        String nombreBorrar=ingreso.nextLine();
        Scanner lector = new Scanner(archivo);
        PrintWriter nuevo = new PrintWriter("Temporal.txt");
        
        String linea;
        
        while (lector.hasNextLine()) {
            linea = lector.nextLine();
            if (!linea.contains(nombreBorrar)) {
                nuevo.println(linea);
            }
        }
        lector.close();
        nuevo.close();
        File nuevoArchivo = new File("Temporal.txt");
        archivo.delete();
        nuevoArchivo.renameTo(archivo);
    }
    
    
    public void MostrarPersonaje()throws Exception{
        File archivo=new File("PersonajesVideojuego.txt");
        Scanner lector=new Scanner(archivo);
        while(lector.hasNextLine()){
            System.out.println(lector.nextLine());
        }
        lector.close();
    }
    
    
    public void ModificarValores()throws Exception{
        File archivo=new File("PersonajesVideojuego.txt");
        System.out.println("escribe el personaje que se desea modificar");
        System.out.println("1)vida\n"+"2)ataque\n"+"3)defensa\n"+"4)alcance\n");
        Scanner entrada=new Scanner(System.in);
        
        int opcion=entrada.nextInt();
        
        if(opcion<1 || opcion>4){
            System.out.println("opcion incorrecta");
            return;
        }
        entrada.nextLine();
        System.out.println("ingrese el nombre del personaje");
        String nombreModificar=entrada.nextLine();
        
        System.out.println("ingrese el nuevo valor");
        int nuevoValor=entrada.nextInt();
        
        while(nuevoValor<=0){
            System.out.println("ingrese un numero mayor");
            nuevoValor=entrada.nextInt();
        }
        Scanner lector=new Scanner(archivo);
        PrintWriter nuevo=new PrintWriter("Temporal.txt");

        String linea;
        while(lector.hasNextLine()){
            linea=lector.nextLine();
            String datos[]=linea.split(" ");

            if(datos[0].equals(nombreModificar)){
                datos[opcion]=String.valueOf(nuevoValor);
                nuevo.println(datos[0]+" "+datos[1]+" "+datos[2]+" "+datos[3]+" "+datos[4]);
            }else{
                nuevo.println(linea);
            }
        }

        lector.close();
        nuevo.close();

        File nuevoArchivo=new File("Temporal.txt");
        archivo.delete();
        nuevoArchivo.renameTo(archivo);
    }
    public void Ordenamiento()throws Exception{
        File archivo=new File("PersonajesVideojuego.txt");
        Scanner lector=new Scanner(archivo);
    
        String p[]=new String[100];
        int n=0;
    
        while(lector.hasNextLine()){
            String linea=lector.nextLine();
            if(!linea.trim().isEmpty()){
                p[n]=linea;
                n++;
            }
        }
        lector.close();
        //ordenar
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                String datos1[]=p[i].split(" ");
                String datos2[]=p[j].split(" ");
    
                if(Integer.parseInt(datos1[1])<
                   Integer.parseInt(datos2[1])){
                    String aux=p[i];
                    p[i]=p[j];
                    p[j]=aux;
                }
            }
        }
        
        PrintWriter nuevo=new PrintWriter("Temporal.txt");
        for(int i=0;i<n;i++){
            nuevo.println(p[i]);
        }
        nuevo.close();
    
    
        archivo.delete();
        File nuevoArchivo=new File("Temporal.txt");
        nuevoArchivo.renameTo(archivo);
    }
    
    public void CrearPersonajesAleatorios()throws Exception{
        RandomAccessFile archivo=new RandomAccessFile("PersonajesVideojuego.txt","rw");
        System.out.println("ingrese el nombre del personaje aleatorio");
        Scanner entrada=new Scanner(System.in);
        String nombre=entrada.nextLine();
        
        Random random=new Random();
        int vida=random.nextInt(80)+100;
        int ataque=random.nextInt(150)+1;
        int defensa=random.nextInt(150)+1;
        int alcance=random.nextInt(100)+1;
        
        if(archivo.length()>0){
            archivo.seek(archivo.length()-1);
        if(archivo.readByte()!='\n'){
                archivo.writeBytes("\n");
            }
            archivo.seek(archivo.length());
        }
        archivo.writeBytes(nombre+" "+vida+" "+ataque+" "+defensa+" "+alcance+"\n");
        archivo.close();
        
    }
    public void ImportarPersonajes()throws Exception{
        File archivo=new File("PersonajesImportar.txt");
        Scanner lector=new Scanner(archivo);
    
        RandomAccessFile nuevo=new RandomAccessFile("PersonajesVideojuego.txt","rw");
        nuevo.seek(nuevo.length());
    
        while(lector.hasNextLine()){
            String linea=lector.nextLine();
    
            if(!linea.trim().isEmpty()){
                nuevo.writeBytes(linea+"\n");
            }
        }
    
        lector.close();
        nuevo.close();
    }
}
class Main{
    public static void main(String[] args)throws Exception{
        Gestor gestor=new Gestor("",0,0,0,0);
        Scanner entrada=new Scanner(System.in);
        int opcion;

        do{
            System.out.println("\n--- MENU DE PERSONAJES ---");
            System.out.println("1 crear personaje");
            System.out.println("2 mostrar personajes");
            System.out.println("3 modificar valores");
            System.out.println("4 borrar personaje");
            System.out.println("5 ordenar personajes");
            System.out.println("6 crear personajes aleatorios");
            System.out.println("7 importar personajes");
            System.out.println("8 Salir");
            
            System.out.println("Elige una opcion:");
            opcion=entrada.nextInt();

            switch(opcion){
                case 1:
                    gestor.CrearPersonaje();
                    break;
                case 2:
                    gestor.MostrarPersonaje();
                    break;
                case 3:
                    gestor.ModificarValores();
                    break;
                case 4:
                    gestor.BorrarPersonaje();
                    break;
                case 5:
                    gestor.Ordenamiento();
                    break;
                case 6:
                    gestor.CrearPersonajesAleatorios();
                    break;
                case 7:
                    gestor.ImportarPersonajes();
                    break;
                case 8:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }

        }while(opcion!=8);
    }
}

        
        
