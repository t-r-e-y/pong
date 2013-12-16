import java.awt.*;

/**
 * @author Weihang Fan
 */

public class Block implements Locatable, Collidable {
    private int width;
    private int height;

    private int xPos;
    private int yPos;

    private Color color;

    public Block() {
        xPos = 100;
        yPos = 150;
        width = 10;
        height = 10;
        color = Color.BLACK;
    }

    public Block(int x, int y) {
        xPos = x;
        yPos = y;
        width = 10;
        height = 10;
        color = Color.BLACK;
    }

    public Block(int x, int y, int w, int h) {
        xPos = x;
        yPos = y;
        width = w;
        height = h;
        color = Color.BLACK;
    }

    public Block(int x, int y, int w, int h, Color col) {
        xPos = x;
        yPos = y;
        setWidth(w);
        setHeight(h);
        color = col;
    }

    //two Blocks are equal when x,y,width and height match
    public boolean equals(Object obj) {
        // If it's not an instance of a Block, then we are clearly not equal.
        if (!(obj instanceof Block)) {
            return false;
        }
        // Otherwise, it is a Block, so we can cast it to a Block.
        Block other = (Block) obj;

        return xPos == other.getX() && yPos == other.getY() && width == other.getWidth() && height == other.getHeight();
    }

    public String toString() {
        return Integer.toString(xPos) + ' ' + Integer.toString(yPos) + ' ' + Integer.toString(width) + ' ' + Integer.toString(height) + ' ' + color.toString();
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }

    @Override
    public void setX(int x) {
        xPos = x;
    }

    @Override
    public void setY(int y) {
        yPos = y;
    }

    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /* (non-Javadoc)
     * @see Collidable#didCollideLeft(java.lang.Object)
     */
    @Override
    public boolean didCollideLeft(Object obj) {
        if (obj instanceof Block){
            Block block = (Block) obj;
            if (xPos <= block.getX() + block.getWidth() && xPos >= block.getX())
                return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see Collidable#didCollideRight(java.lang.Object)
     */
    @Override
    public boolean didCollideRight(Object obj) {
        if (obj instanceof Block) {
            Block block = (Block) obj;
            if (xPos + width >= block.getX() && xPos <= block.getX())
                return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see Collidable#didCollideTop(java.lang.Object)
     */
    @Override
    public boolean didCollideTop(Object obj) {
        if (obj instanceof Block) {
            Block block = (Block) obj;
            if (yPos <= block.getY() + block.getHeight() && yPos >= block.getY())
                return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see Collidable#didCollideBottom(java.lang.Object)
     */
    @Override
    public boolean didCollideBottom(Object obj) {
        if (obj instanceof Block) {
            Block block = (Block) obj;
            if (yPos + height >= block.getY() && yPos <= block.getY())
                return true;
        }
        return false;
    }
}