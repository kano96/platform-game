package main;

import javax.swing.*;

public class GameWindow {

    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();

        // Termina el programa cuando se cierra la ventana
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setResizable(false);
        // ajusta el tamaño de la ventana al tamaño de los componentes (solo hay uno: GamePanel)
        jFrame.pack();
        // Hace que la ventana del juego aparezca en la mitad de la pantalla
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

    }


}
