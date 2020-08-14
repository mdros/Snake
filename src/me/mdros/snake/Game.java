package me.mdros.snake;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new Game();
            frame.setVisible(true);
        });
    }

    public Game() {
        initGUI();
    }

    private void initGUI() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
