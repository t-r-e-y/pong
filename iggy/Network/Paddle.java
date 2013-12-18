package Network;

import java.awt.Color;

public class Paddle extends Block {
	
	private int speed;
	
	// If not controllable, paddle is locked
	private boolean controllable = true;

	public Paddle() {
		this(10, 10);
	}
	
	public Paddle(int xPos, int yPos) {
		this(xPos, yPos, 5);
	}
	
	public Paddle(int xPos, int yPos, int speed) {
		this(xPos, yPos, 10, 10, speed);
	}
	
	public Paddle(int xPos, int yPos, int width, int height, int speed) {
		this(xPos, yPos, width, height, Color.BLACK, speed);
	}
	
	public Paddle(int xPos, int yPos, int width, int height, Color color, int speed) {
		super(xPos, yPos, width, height, color);
		this.speed = speed;
	}
	
	@Override
	public void setPos(int x, int y) {
		if (controllable) super.setPos(x, y);
	}
	
	@Override
	public void setY(int y) {
		if (controllable) super.setY(y);
	}
	
	public void moveUp() {
		if (controllable) setY(getY() - speed);
	}
	
	public void moveDown() {
		if (controllable) setY(getY() + speed);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setControllable(boolean controllable) {
		this.controllable = controllable;
	}
	
	public boolean getControllable() {
		return controllable;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + speed;
	}
}