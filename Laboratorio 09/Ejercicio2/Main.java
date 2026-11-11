package Paquete;
import javax.swing.JFrame;
public class Main {

	public static void main(String[] args) {
		PasajeMarco marcoPasaje = new PasajeMarco();
        marcoPasaje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoPasaje.setSize(400, 550);
        marcoPasaje.setVisible(true);
	}

}
