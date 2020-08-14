package me.mdros.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ButtonAdapter extends KeyAdapter {

    Snake snake;

    public ButtonAdapter(Snake snake) {
        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();


        if ((keyCode == KeyEvent.VK_LEFT) && (!snake.isGoingRight())) {
            snake.setGoingLeft(true);
            snake.setGoingUp(false);
            snake.setGoingDown(false);
        }
        else if ((keyCode == KeyEvent.VK_RIGHT) && (!snake.isGoingLeft())) {
            snake.setGoingRight(true);
            snake.setGoingUp(false);
            snake.setGoingDown(false);
        }
        else if ((keyCode == KeyEvent.VK_UP) && (!snake.isGoingDown())) {
            snake.setGoingUp(true);
            snake.setGoingLeft(false);
            snake.setGoingRight(false);
        }
        else if ((keyCode == KeyEvent.VK_DOWN) && (!snake.isGoingUp())) {
            snake.setGoingDown(true);
            snake.setGoingLeft(false);
            snake.setGoingRight(false);

        }

    }

}
