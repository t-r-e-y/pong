package Local;

import java.awt.Color;

public class Paddle extends Block {
	
	private int speed;


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
	
	public void moveUp() {
		setY(getY() - speed);
	}
	
	public void moveDown() {
		setY(getY() + speed);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}

	@Override
	public String toString() {
		return super.toString() + " " + speed;
	}
}