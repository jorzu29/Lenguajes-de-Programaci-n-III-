package ejercicio3;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Ejercicio3 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reproductor de Efectos de Sonido");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 150);
        frame.setLayout(new java.awt.FlowLayout());

        // Creación de los botones para cada efecto de sonido
        JButton aplausosButton = new JButton("Aplausos");
        JButton campanaButton = new JButton("Campana");
        JButton explosionButton = new JButton("Explosión");

        // Eventos de escucha asociados a cada botón
        aplausosButton.addActionListener(e -> playAudio("src/ejercicio3/aplausos.wav"));
        campanaButton.addActionListener(e -> playAudio("src/ejercicio3/campana.wav"));
        explosionButton.addActionListener(e -> playAudio("src/ejercicio3/explosion.wav"));

        // Agregar los botones al marco
        frame.add(aplausosButton);
        frame.add(campanaButton);
        frame.add(explosionButton);

        frame.setVisible(true);
    }

    // Método basado en el ejemplo AppAudio de la guía para reproducir WAV
    public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}