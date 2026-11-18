package ejercicio2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio2 extends JFrame {
    private JTextField[] txtTemperaturas = new JTextField[7];
    private String[] dias = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};
    private PanelGrafico panelGrafico;

    public Ejercicio2() {
        setTitle("Gráfico Simple de Temperaturas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        // 1. Panel superior con 7 campos de texto para cada día
        JPanel panelNorte = new JPanel(new GridLayout(2, 7));
        for (int i = 0; i < 7; i++) {
            panelNorte.add(new JLabel(dias[i], SwingConstants.CENTER));
        }
        for (int i = 0; i < 7; i++) {
            txtTemperaturas[i] = new JTextField("20", 4);
            panelNorte.add(txtTemperaturas[i]);
        }

        JButton btnMostrar = new JButton("Mostrar Gráfico");
        
        JPanel norte = new JPanel(new BorderLayout());
        norte.add(panelNorte, BorderLayout.CENTER);
        norte.add(btnMostrar, BorderLayout.SOUTH);
        add(norte, BorderLayout.NORTH);

        // 2. Panel personalizado donde se dibuja el gráfico
        panelGrafico = new PanelGrafico();
        add(panelGrafico, BorderLayout.CENTER);

        // 3. Evento del botón para capturar datos y actualizar el panel
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[] temps = new int[7];
                    for (int i = 0; i < 7; i++) {
                        temps[i] = Integer.parseInt(txtTemperaturas[i].getText());
                    }
                    panelGrafico.setTemperaturas(temps); // Actualiza y redibuja
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese solo números enteros.");
                }
            }
        });
    }

    public static void main(String[] args) {
        new Ejercicio2().setVisible(true);
    }
}

// Panel de dibujo basado en la teoría básica de la guía (extends JPanel y paintComponent)
class PanelGrafico extends JPanel {
    private int[] temperaturas = {20, 20, 20, 20, 20, 20, 20};
    private String[] dias = {"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};

    public void setTemperaturas(int[] temps) {
        this.temperaturas = temps;
        repaint(); // Llama automáticamente a paintComponent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int xInicio = 50;
        int espacioX = 55;
        int baseY = 250; // Línea base horizontal de referencia

        // Dibujar línea base del gráfico
        g.setColor(Color.BLACK);
        g.drawLine(xInicio - 10, baseY, xInicio + 6 * espacioX + 20, baseY);

        // Recorrer los 7 días para dibujar puntos, líneas y texto
        for (int i = 0; i < 7; i++) {
            int x = xInicio + (i * espacioX);
            int y = baseY - (temperaturas[i] * 3); // Posición vertical simple según la temperatura

            // Dibujar punto (círculo relleno)
            g.setColor(Color.RED);
            g.fillOval(x - 4, y - 4, 8, 8);

            // Dibujar número de temperatura encima y el día debajo
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(temperaturas[i]), x - 10, y - 10);
            g.drawString(dias[i], x - 10, baseY + 20);

            // Dibujar línea que conecta con el siguiente día
            if (i < 6) {
                int siguienteX = xInicio + ((i + 1) * espacioX);
                int siguienteY = baseY - (temperaturas[i + 1] * 3);
                g.setColor(Color.BLUE);
                g.drawLine(x, y, siguienteX, siguienteY);
            }
        }
    }
}