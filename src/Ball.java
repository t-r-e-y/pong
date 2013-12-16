import java.awt.*;

/**
 * @author Weihang Fan
 */
public class Ball extends Block {

    private int xSpeed;
    private int ySpeed;

    public Ball() {
        super(200, 200, 10, 10);
        xSpeed = 3;
        ySpeed = 1;
    }

    public Ball(int x, int y) {
        super(x, y, 10, 10);
        xSpeed = 3;
        ySpeed = 1;
    }

    public Ball(int x, int y, int w, int h) {
        super(x, y, w, h);
        xSpeed = 3;
        ySpeed = 1;
    }

    public Ball(int x, int y, int w, int h, Color col) {
        super(x, y, w, h, col);
        xSpeed = 3;
        ySpeed = 1;
    }

    public Ball(int x, int y, int w, int h, Color col, int xs, int ys) {
        super(x, y, w, h, col);
        xSpeed = xs;
        ySpeed = ys;
    }

    // set the x and y positions based on speeds
    public void move() {
        setX(getX() + xSpeed);
        setY(getY() + ySpeed);
    }

    // two Ball objects are equal when x,y,width,height,xSpeed,ySpeed match
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ball)) {
            return false;
        }
        Ball other = (Ball) obj;
        return getX() == other.getX() && getY() == other.getY()
                && getWidth() == other.getWidth()
                && getHeight() == other.getHeight()
                && xSpeed == other.getXSpeed() && ySpeed == other.getYSpeed();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.xSpeed;
        hash = 97 * hash + this.ySpeed;
        return hash;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public String toString() {
        return Integer.toString(getX()) + ' ' + getY() + ' ' + getWidth() + ' '
                + getHeight() + ' ' + getColor().toString() + ' ' + xSpeed
                + ' ' + ySpeed;
    }
}
