package Local;

import java.awt.Color;
import java.awt.Graphics;

public class Block implements Locatable {
	
	private int width;
	private int height;

	private int xPos;
	private int yPos;

	private Color color;

	public Block() {
		this(100, 150);
	}
	
	public Block(int xPos, int yPos, int width, int height) {
		this(xPos, yPos, width, height, Color.BLACK);
	}
	
	public Block(int xPos, int yPos) {
		this(xPos, yPos, 10, 10);
	}
	
	public Block(int xPos, int yPos, int width, int height, Color color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public void setColor(Color color) {
			this.color = color;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Block)) return false;
		Block block = (Block) obj;
		if ((block.xPos == this.xPos) && (block.yPos == this.yPos) && (block.width == this.width) && (block.height == this.height)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return xPos + " " + yPos + " " + width + " " + height + " " + color;
	}
	
	public void draw(Graphics window) {
		window.setColor(color);
		window.fillRect(getX(), getY(), getWidth(), getHeight());
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
	
	public Color getColor() {
		return color;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
}