import java.awt.*;

public class Paddle extends Block {
    // instance variables
    private int speed;

    public Paddle() {
        super(10, 10, 10, 10);
        setSpeed(5);
    }

    public Paddle(int x, int y) {
        super(x, y);
        setSpeed(5);
    }

    public Paddle(int x, int y, int s) {
        super(x, y);
        setSpeed(s);
    }

    public Paddle(int x, int y, int w, int h, int s) {
        super(x, y, w, h);
        speed = s;
    }

    public Paddle(int x, int y, int w, int h, Color col, int s) {
        super(x, y, w, h, col);
        speed = s;
    }

    // changes paddle y position based on speed
    public void moveUp() {
        setY(getY() - speed);
    }

    // changes paddle y position based on speed
    public void moveDown() {
        setY(getY() + speed);
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String toString() {
        return Integer.toString(getX()) + ' ' + Integer.toString(getY()) + ' '
                + Integer.toString(getWidth()) + ' '
                + Integer.toString(getHeight()) + ' ' + getColor().toString()
                + ' ' + Integer.toString(speed);
    }
}