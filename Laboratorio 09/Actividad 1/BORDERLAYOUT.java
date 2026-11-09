package admin;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;

public class MarcoPanel extends JFrame
{
    private final JPanel panelBotones;
    private final JButton[] botones;
    List<String>integrantes=new ArrayList<>();

    public MarcoPanel()
    {
        super("Integrantes-Kevin Tacoma Gonzales-Jorge Zuñiga Silva");
        integrantes.add("kevin Tacoma Gonzales");
        integrantes.add("Jorge Zuñiga Silva");
        
        botones = new JButton[2];
        panelBotones = new JPanel();
        //linea de codigo para cambio por BorderLayout
        panelBotones.setLayout(new BorderLayout());

        for (int cuenta = 0; cuenta < botones.length; cuenta++)
        {
            botones[cuenta] = new JButton("Boton " + (cuenta + 1)+": "+integrantes.get(cuenta));
            
            
        }
      //cambio para colocar en el centro
        panelBotones.add(botones[0],BorderLayout.CENTER);
        panelBotones.add(botones[1],BorderLayout.CENTER);
        add(panelBotones);
    }
}
