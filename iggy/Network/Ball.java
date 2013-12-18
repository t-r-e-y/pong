package Network;

import java.awt.Color;

public class Ball extends Block {
	
	private int xSpeed;
	private int ySpeed;

	public Ball() {
		this(200, 200);
	}
	
	public Ball(int xPos, int yPos) {
		this(xPos, yPos, 10, 10);
	}
	
	public Ball(int x, int y, int width, int height) {
		this(x, y, width, height, Color.BLACK);
	}
	
	public Ball(int x, int y, int width, int height, Color color) {
		this(x, y, width, height, color, 3, 1);
	}
	
	public Ball(int x, int y, int width, int height, Color color, int xSpeed, int ySpeed) {
		super(x, y, width, height, color);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
	}
	
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}
	
	public int getXSpeed() {
		return xSpeed;
	}
	
	public int getYSpeed() {
		return ySpeed;
	}
	
	public void move() {
		setX(getX() + xSpeed);
		setY(getY() + ySpeed);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ball)) return false;
		return (super.equals(obj)) && (this.xSpeed == ((Ball) obj).xSpeed) && (this.ySpeed == ((Ball)obj).ySpeed);
	}   
	
	@Override
	public String toString() {
		return super.toString() + " " + xSpeed + " " + ySpeed;
	}
}