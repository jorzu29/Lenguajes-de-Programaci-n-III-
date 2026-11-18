package JavaSwing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aplicaciones con Swing");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane pestañas = new JTabbedPane();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        JLabel etiqueta = new JLabel("Ingrese su nombre:");
        JTextField campo = new JTextField(15);
        JButton boton = new JButton("Mostrar");
        JLabel resultado = new JLabel("Resultado:");

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultado.setText("Hola " + campo.getText());
            }
        });

        panel1.add(etiqueta);
        panel1.add(campo);
        panel1.add(boton);
        panel1.add(resultado);

        JPanel panel2 = new JPanel() {

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Gráfico simple: rectángulo
                g.setColor(Color.BLUE);
                g.fillRect(50, 50, 100, 80);

                // Gráfico simple: círculo
                g.setColor(Color.RED);
                g.fillOval(200, 50, 80, 80);

                // Gráfico avanzado: líneas
                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(Color.GREEN);
                g2.setStroke(new BasicStroke(4));
                g2.drawLine(50, 200, 300, 200);

                // Texto
                g2.setColor(Color.BLACK);
                g2.drawString("Graficos con Swing", 150, 250);
            }
        };


        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());

        JLabel imagen = new JLabel();

        JButton botonImagen = new JButton("Mostrar imagen");

        botonImagen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ImageIcon icono = new ImageIcon("imagen.jpg");

                imagen.setIcon(new ImageIcon(
                    icono.getImage().getScaledInstance(
                        200, 150, Image.SCALE_SMOOTH
                    )
                ));
            }
        });

        panel3.add(botonImagen);
        panel3.add(imagen);


        // AGREGAR LAS TRES PESTAÑAS
        pestañas.addTab("Binding de datos", panel1);
        pestañas.addTab("Gráficos", panel2);
        pestañas.addTab("Multimedia", panel3);

        frame.add(pestañas);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

