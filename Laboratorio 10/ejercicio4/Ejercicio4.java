package ejercicio4;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Ejercicio4 {

    private static Clip clip;
    private static long posicionActual = 0; // Para almacenar la posición al pausar

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reproductor de Música");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 150);
        frame.setLayout(new java.awt.FlowLayout());

        JButton playButton = new JButton("Reproducir");
        JButton pauseButton = new JButton("Pausar");
        JButton resumeButton = new JButton("Reanudar");

        // Botón Reproducir: Carga el audio y lo reproduce desde el inicio
        playButton.addActionListener(e -> {
            try {
                // Si ya hay un clip reproduciéndose, lo detenemos y cerramos
                if (clip != null) {
                    clip.stop();
                    clip.close();
                }
                
                File audioFile = new File("src/ejercicio3/aplausos.wav"); // Asegúrate de tener este archivo en la raíz del proyecto
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

        // Botón Pausar: Detiene temporalmente el audio y guarda la posición actual
        pauseButton.addActionListener(e -> {
            if (clip != null && clip.isRunning()) {
                posicionActual = clip.getMicrosecondPosition(); // Guarda el momento exacto
                clip.stop();
            }
        });

        // Botón Reanudar: Vuelve a reproducir desde el punto donde se pausó
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