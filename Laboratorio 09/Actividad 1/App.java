package admin;

import javax.swing.JFrame;

public class App extends JFrame
{
    public static void main(String[] args)
    {
        MarcoPanel marcoPanel = new MarcoPanel();
        marcoPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoPanel.setSize(450, 200);
        marcoPanel.setVisible(true);
    }
}

