package ejercicio4;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Ejercicio4 {

    private static Clip clip;
    private static long posicionActual = 0; 

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reproductor de Música");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 150);
        frame.setLayout(new java.awt.FlowLayout());

        JButton playButton = new JButton("Reproducir");
        JButton pauseButton = new JButton("Pausar");
        JButton resumeButton = new JButton("Reanudar");

        // Reproducir
        playButton.addActionListener(e -> {
            try {
                if (clip != null) {
                    clip.stop();
                    clip.close();
                }
                
                File audioFile = new File("src/ejercicio3/aplausos.wav"); 
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                
                posicionActual = 0;
                clip.setMicrosecondPosition(posicionActual);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });

        // Pausar
        pauseButton.addActionListener(e -> {
            if (clip != null && clip.isRunning()) {
                posicionActual = clip.getMicrosecondPosition(); 
                clip.stop();
            }
        });

        // Reaunudar
        resumeButton.addActionListener(e -> {
            if (clip != null && !clip.isRunning()) {
                clip.setMicrosecondPosition(posicionActual); // Restablece la posición guardada
                clip.start();
            }
        });

        frame.add(playButton);
        frame.add(pauseButton);
        frame.add(resumeButton);

        frame.setVisible(true);
    }
}
