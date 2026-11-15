package JavaSwing;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Tienda Virtual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);

        // Modelo de datos
        Producto producto = new Producto("Leche", 2.50, 3,"Lacteo");

        // Componentes de interfaz para cada atributo
        JTextField nombreField = new JTextField(producto.getNombre(), 15);
        JTextField precioField = new JTextField(String.valueOf(producto.getPrecio()), 15);
        JTextField stockField = new JTextField(String.valueOf(producto.getStock()), 15);
        JTextField categoriaField = new JTextField(producto.getCategoria(),15);
        
        JButton button = new JButton("Actualizar Producto");

        // Listener para actualizar el modelo cuando el botón es presionado
        JLabel nombre = new JLabel("Nombre: "+producto.getNombre());
        JLabel precio=new JLabel("Precio: "+producto.getPrecio());
        JLabel stock=new JLabel("Stock: "+producto.getStock());
        JLabel categoria=new JLabel("Categoria: "+producto.getCategoria());
       
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                producto.setNombre(nombreField.getText());
                

                try {
                    double precio = Double.parseDouble(precioField.getText());
                    int stock = Integer.parseInt(stockField.getText());
                    if (precio < 0 || stock < 0) {
                        JOptionPane.showMessageDialog(frame, "No se permiten numeros negativos.");
                        return;
                } 
                    producto.setPrecio(precio);
                    producto.setStock(stock);
                
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un numero valido .");
                }

                producto.setCategoria(categoriaField.getText());
                nombre.setText("Nombre: " + producto.getNombre());
                precio.setText("Precio: " + producto.getPrecio());
                stock.setText("Stock: " + producto.getStock());
                categoria.setText("Categoria: " + producto.getCategoria());
               
            }
        });

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(new JLabel("Nombre:"));
        frame.add(nombreField);
        frame.add(new JLabel("Precio:"));
        frame.add(precioField);
        frame.add(new JLabel("Stock:"));
        frame.add(stockField);
        frame.add(new JLabel("Categoria:"));
        frame.add(categoriaField);
        
        frame.add(button);
        frame.add(nombre);
        frame.add(precio);
        frame.add(stock);
        frame.add(categoria);

        frame.setVisible(true);
        
    }
}

class Producto {

    private String nombre;
    private double precio;
    private String categoria;
    private int stock;

    public Producto(String nombre, double precio,int stock, String categoria ) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock=stock;
        this.categoria = categoria;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;

    }
}
