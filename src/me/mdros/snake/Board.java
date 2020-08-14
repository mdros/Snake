package me.mdros.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    Snake snake;

    private Image dot;
    private Image apple;
    private Image snakeHead;

    private final int[] x_cords = new int[GameConstants.MAX_DOTS];
    private final int[] y_cords = new int[GameConstants.MAX_DOTS];

    private int apple_x;
    private int apple_y;

    private boolean inGame = true;

    private int dotAmount;
    private Timer timer;

    public Board() {
        snake = new Snake();
        addKeyListener(new ButtonAdapter(snake));
        initializeBoard();
    }

    public void initializeBoard() {
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT));
        loadImages();
        initializeGame();
    }

    private void initializeGame() {
        dotAmount = 3;

        for (int k = 0; k < dotAmount; k++) {
            x_cords[k] = 50 - k * 10;
            y_cords[k] = 50;
        }

        placeApple();

        timer = new Timer(GameConstants.DELAY, this);
        timer.start();
    }

    private void loadImages() {
        ImageIcon dotIcon = new ImageIcon("src/resources/dot.png");
        dot = dotIcon.getImage();

        ImageIcon appleIcon = new ImageIcon("src/resources/apple.png");
        apple = appleIcon.getImage();

        ImageIcon snakeHeadIcon = new ImageIcon("src/resources/snakeHead.png");
        snakeHead = snakeHeadIcon.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (inGame) {
            g.drawImage(apple, apple_x, apple_y, this);

            for (int k = 0; k < dotAmount; k++) {
                if (k == 0) {
                    g.drawImage(snakeHead, x_cords[k], y_cords[k], this);
                }
                else {
                    g.drawImage(dot, x_cords[k], y_cords[k], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();
        }
        else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String gameOverMessage = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(small);

        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(gameOverMessage, (GameConstants.BOARD_WIDTH - metrics.stringWidth(gameOverMessage)) / 2, GameConstants.BOARD_HEIGHT / 2);
    }

    private void checkApple() {
        if ((x_cords[0] == apple_x) && (y_cords[0] == apple_y)) {
            dotAmount++;
            placeApple();
        }
    }

    private void placeApple() {
        int random = (int) (Math.random() * GameConstants.DOT_RANDOMIZE);
        apple_x = ((random * GameConstants.DOT_SIZE));

        random = (int) (Math.random() * GameConstants.DOT_RANDOMIZE);
        apple_y = ((random * GameConstants.DOT_SIZE));
    }

    private void checkCollision() {
        for (int k = dotAmount; k > 0; k--) {
            if ((x_cords[0] == x_cords[k]) && (y_cords[0] == y_cords[k])) {
                inGame = false;
            }
        }

        if (inGame) {
            if (y_cords[0] >= GameConstants.BOARD_HEIGHT) {
                inGame = false;
            } else if (y_cords[0] < 0) {
                inGame = false;
            }

            if (x_cords[0] >= GameConstants.BOARD_WIDTH) {
                inGame = false;
            } else if (x_cords[0] < 0) {
                inGame = false;
            }
        }

        if(!inGame) {
            timer.stop();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private void move() {

        for (int z = dotAmount; z > 0; z--) {
            x_cords[z] = x_cords[(z - 1)];
            y_cords[z] = y_cords[(z - 1)];
        }

        if (snake.isGoingLeft()) {
            x_cords[0] -= GameConstants.DOT_SIZE;
        }

        if (snake.isGoingRight()) {
            x_cords[0] += GameConstants.DOT_SIZE;
        }

        if (snake.isGoingUp()) {
            y_cords[0] -= GameConstants.DOT_SIZE;
        }

        if (snake.isGoingDown()) {
            y_cords[0] += GameConstants.DOT_SIZE;
        }
    }
}
