package JRadioButton;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class MarcoBotonOpcion extends JFrame {
    private final JTextField campoTexto;
    private final Font tipoLetraSimple;
    private final Font tipoLetraNegrita;
    private final Font tipoLetraMonospaced;
    private final Font tipoLetraNegritaMonospaced;
    private final JRadioButton simpleJRadioButton;
    private final JRadioButton negritaJRadioButton;
    private final JRadioButton monospacedJRadioButton;
    private final JRadioButton negritaMonospacedJRadioButton;
    private ButtonGroup grupoOpciones;

    public MarcoBotonOpcion() {
        super("Tacoma - Zúñiga RadioButton");
        setLayout(new FlowLayout());

        campoTexto = new JTextField("OJO! Que el estilo de letra cambia D:", 25);
        add(campoTexto);

        simpleJRadioButton = new JRadioButton("Simple", true);
        negritaJRadioButton = new JRadioButton("Negrita", false);
        monospacedJRadioButton = new JRadioButton("Monospaced", false);
        negritaMonospacedJRadioButton = new JRadioButton("Negrita/Monospaced", false);
        
        add(simpleJRadioButton);
        add(negritaJRadioButton);
        add(monospacedJRadioButton);
        add(negritaMonospacedJRadioButton);

        grupoOpciones = new ButtonGroup();
        grupoOpciones.add(simpleJRadioButton);
        grupoOpciones.add(negritaJRadioButton);
        grupoOpciones.add(monospacedJRadioButton);
        grupoOpciones.add(negritaMonospacedJRadioButton);

        tipoLetraSimple = new Font("Serif", Font.PLAIN, 14);
        tipoLetraNegrita = new Font("Serif", Font.BOLD, 14);
        tipoLetraMonospaced = new Font("Monospaced", Font.PLAIN, 14);
        tipoLetraNegritaMonospaced = new Font("Monospaced", Font.BOLD, 14);
        campoTexto.setFont(tipoLetraSimple);

        simpleJRadioButton.addItemListener(new ManejadorBotonOpcion(tipoLetraSimple));
        negritaJRadioButton.addItemListener(new ManejadorBotonOpcion(tipoLetraNegrita));
        monospacedJRadioButton.addItemListener(new ManejadorBotonOpcion(tipoLetraMonospaced));
        negritaMonospacedJRadioButton.addItemListener(new ManejadorBotonOpcion(tipoLetraNegritaMonospaced));
    }

    private class ManejadorBotonOpcion implements ItemListener {
        private Font tipoLetra;

        public ManejadorBotonOpcion(Font f) {
            tipoLetra = f;
        }

        @Override
        public void itemStateChanged(ItemEvent evento) {
            campoTexto.setFont(tipoLetra);
        }
    }
}
