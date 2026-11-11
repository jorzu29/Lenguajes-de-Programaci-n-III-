package JCheckBox;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class MarcoCasillaVerificacion extends JFrame {
    private JTextField campoTexto;
    private JCheckBox negritaJCheckBox;
    private JCheckBox cursivaJCheckBox;

    public MarcoCasillaVerificacion() {
        super("Tacoma - Zúñiga JCheckBox");
        setLayout(new FlowLayout());

        campoTexto = new JTextField("El deliniado de las letras cambia !!!! :O", 20);
        campoTexto.setFont(new Font("Serif", Font.PLAIN, 14));
        add(campoTexto);

        negritaJCheckBox = new JCheckBox("Negrita ▲");
        cursivaJCheckBox = new JCheckBox("Cursiva △");
        add(negritaJCheckBox);
        add(cursivaJCheckBox);

        ManejadorCheckBox manejador = new ManejadorCheckBox();
        negritaJCheckBox.addItemListener(manejador);
        cursivaJCheckBox.addItemListener(manejador);
    }

    private class ManejadorCheckBox implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent evento) {
            Font tipoletra = null;

            if (negritaJCheckBox.isSelected() && cursivaJCheckBox.isSelected()) {
                tipoletra = new Font("Serif", Font.BOLD + Font.ITALIC, 14);
            } else if (negritaJCheckBox.isSelected()) {
                tipoletra = new Font("Serif", Font.BOLD, 14);
            } else if (cursivaJCheckBox.isSelected()) {
                tipoletra = new Font("Serif", Font.ITALIC, 14);
            } else {
                tipoletra = new Font("Serif", Font.PLAIN, 14);
            }

            campoTexto.setFont(tipoletra);
        }
    }
}
