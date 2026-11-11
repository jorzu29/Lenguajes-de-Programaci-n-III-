package JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MarcoBoton extends JFrame {
    private final JButton botonJButtonSimple;
    private final JButton botonJButtonElegante;

    public MarcoBoton() {
        super("Tacoma - Zúñiga JButton");
        setLayout(new FlowLayout());

        botonJButtonSimple = new JButton("Boton sin nada :(");
        add(botonJButtonSimple);

        Icon insecto1 = new ImageIcon(getClass().getResource("icono2.png"));
        Icon insecto2 = new ImageIcon(getClass().getResource("icono1.png"));
        
        botonJButtonElegante = new JButton("Boton elegante B)", insecto1);
        botonJButtonElegante.setRolloverIcon(insecto2);
        add(botonJButtonElegante);

        ManejadorBoton manejador = new ManejadorBoton();
        botonJButtonElegante.addActionListener(manejador);
        botonJButtonSimple.addActionListener(manejador);
    }

    private class ManejadorBoton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evento) {
            JOptionPane.showMessageDialog(MarcoBoton.this, String.format("Usted oprimio: %s", evento.getActionCommand()));
        }
    }
}
