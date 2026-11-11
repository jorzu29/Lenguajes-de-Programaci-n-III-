package JList;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MarcoLista extends JFrame
{
    private final JList<String> listaJListAnimales; 
    private final JLabel etiquetaImagen;

    private static final String[] nombresAnimales = {"León", "Elefante", "Tigre", "Panda"};
    private static final String[] rutasImagenes = {
        "leon.png", 
        "elefante.png", 
        "tigre.png", 
        "panda.png"
    };

    public MarcoLista()
    {
        super("Tacoma - Zúñiga JList");
        setLayout(new FlowLayout());

        listaJListAnimales = new JList<String>(nombresAnimales); 
        listaJListAnimales.setVisibleRowCount(3); 
        listaJListAnimales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(listaJListAnimales));

        etiquetaImagen = new JLabel();
        etiquetaImagen.setIcon(new ImageIcon(getClass().getResource(rutasImagenes[0])));
        listaJListAnimales.setSelectedIndex(0);
        add(etiquetaImagen);

        listaJListAnimales.addListSelectionListener(
            new ListSelectionListener() 
            {
                @Override
                public void valueChanged(ListSelectionEvent evento)
                {
                    if (!evento.getValueIsAdjusting())
                    {
                        int indice = listaJListAnimales.getSelectedIndex();
                        if (indice != -1)
                        {
                            etiquetaImagen.setIcon(new ImageIcon(getClass().getResource(rutasImagenes[indice])));
                        }
                    }
                }
            }
        );
    }
}
