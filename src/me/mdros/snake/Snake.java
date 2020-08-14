package me.mdros.snake;

public class Snake {

    private boolean goingLeft = false;
    private boolean goingRight = true;
    private boolean goingUp = false;
    private boolean goingDown = false;

    public boolean isGoingLeft() {
        return goingLeft;
    }

    public void setGoingLeft(boolean goingLeft) {
        this.goingLeft = goingLeft;
    }

    public boolean isGoingRight() {
        return goingRight;
    }

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    public boolean isGoingUp() {
        return goingUp;
    }

    public void setGoingUp(boolean goingUp) {
        this.goingUp = goingUp;
    }

    public boolean isGoingDown() {
        return goingDown;
    }

    public void setGoingDown(boolean goingDown) {
        this.goingDown = goingDown;
    }
}
