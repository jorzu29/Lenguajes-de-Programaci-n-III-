package prueba1;

import javax.swing.JFrame;

public class DemoGridLayout
{
    public static void main(String[] args)
    {
        MarcoGridLayout marcoGridLayout = new MarcoGridLayout();
        marcoGridLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoGridLayout.setSize(300, 200);
        marcoGridLayout.setVisible(true);
    }
}
