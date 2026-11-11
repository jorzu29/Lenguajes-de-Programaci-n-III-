package ListaSelecciónMúltiple;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MarcoSeleccionMultiple extends JFrame
{
   private final JList<String> listaJListColores; 
   private final JList<String> listaJListCopia; 
   private JButton botonJButtonCopiar; 
   private static final String[] nombresPersona = {"Juan", "Pedro", "Luis",
      "Maria", "Rosa", "Mario", "Sofia", "Cristina", "Miguel",
      "Benjamin", "Jose", "Luciana", "Diego"};

   public MarcoSeleccionMultiple()
   {
      super("Tacoma Zúñiga - Listas de seleccion multiple");
      setLayout(new FlowLayout());

      listaJListColores = new JList<String>(nombresPersona); 
      listaJListColores.setVisibleRowCount(5); 
      listaJListColores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      add(new JScrollPane(listaJListColores)); 

      botonJButtonCopiar = new JButton("Copiar >>>");
      botonJButtonCopiar.addActionListener(
         new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent evento)
            {
               listaJListCopia.setListData(listaJListColores.getSelectedValuesList().toArray(new String[0]));
            }
         }
      );

      add(botonJButtonCopiar); 

      listaJListCopia = new JList<String>(); 
      listaJListCopia.setVisibleRowCount(5); 
      listaJListCopia.setFixedCellWidth(100); 
      listaJListCopia.setFixedCellHeight(15); 
      listaJListCopia.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      add(new JScrollPane(listaJListCopia)); 
   }
}
