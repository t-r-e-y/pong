package Local;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Pong extends JPanel implements Runnable, Constants, MouseMotionListener, KeyListener {
	
	// Instance variables
	private Ball ball;
	private Paddle leftPaddle;
	private Paddle rightPaddle;
	private boolean[] keys = new boolean[KEYS.length];
	private BufferedImage back;
	private int leftScore = 0;
	private int rightScore = 0;

	public Pong() {
		setDoubleBuffered(true);
		
		// Create ball and paddles using constants
		ball = new Ball(WINDOW_WIDTH / 2 - BALL_SIDE / 2, WINDOW_HEIGHT / 2 - BALL_SIDE / 2 , BALL_SIDE, BALL_SIDE, BALL_COLOR, BALL_XSPEED, BALL_YSPEED);
		leftPaddle = new Paddle(LEFT_PADDLE_X - LEFT_PADDLE_WIDTH / 2, WINDOW_HEIGHT / 2 - LEFT_PADDLE_HEIGHT / 2, LEFT_PADDLE_WIDTH, LEFT_PADDLE_HEIGHT, LEFT_PADDLE_COLOR, LEFT_PADDLE_SPEED);
		rightPaddle = new Paddle(RIGHT_PADDLE_X - RIGHT_PADDLE_WIDTH / 2, WINDOW_HEIGHT / 2 - RIGHT_PADDLE_HEIGHT / 2, RIGHT_PADDLE_WIDTH, RIGHT_PADDLE_HEIGHT, RIGHT_PADDLE_COLOR, RIGHT_PADDLE_SPEED);
		
		// Set up a boolean array that will keep track of whether of not a key to move the paddle is pressed down
		for (int i = 0; i < KEYS.length; i++) {
			keys[i] = false;
		}
		
		// Background color
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);

		new Thread(this).start();
		
		// Set the mouse to listen or the keys to listen
		
		// addMouseMotionListener(this);
		addKeyListener(this);
	}
	
	@Override
	// Updates the window every frame rate
	public void update(Graphics window) {
		// Double buffering
		Graphics off;
	    if (back == null) {
	    	back = (BufferedImage) createImage(getWidth(), getHeight());
	    }
	    
	    off = back.getGraphics();
	    paint(off);
	    window.drawImage(back, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics window) {
	
		super.paint(window);
		
		// Keep score
		window.setFont(FONT);
		window.drawString("L " + leftScore, MARGIN, WINDOW_HEIGHT - MARGIN);
		window.drawString("R " + rightScore, WINDOW_WIDTH - MARGIN - TEXT_WIDTH, WINDOW_HEIGHT - MARGIN);
		
		// The ball draws and moves
		ball.draw(window);
		ball.move();
		
		// The paddles draw
		leftPaddle.draw(window);
		rightPaddle.draw(window);
		
		// Move the paddles accordingly if a button is pressed down
		checkKeys();
		
		// Check for collisions with the ball or paddle
		checkCollisions();
		
		// Check if the ball made it past a paddle, resulting in a point
		checkPoint();
	}
	
	private void checkKeys() {
		if (keys[0]) {
			// Paddle should not move up past the screen
			if (leftPaddle.getY() > 0) {
				leftPaddle.moveUp();
			}
		}
		if (keys[1]) {
			// Paddle should not move down past the screen
			if (leftPaddle.getY() + leftPaddle.getHeight() < WINDOW_HEIGHT) {
				leftPaddle.moveDown();
			}
		}
		if (keys[2]) {
			if (rightPaddle.getY() > 0) {
				rightPaddle.moveUp();
			}
		}
		if (keys[3]) {
			if (rightPaddle.getY() + rightPaddle.getHeight() < WINDOW_HEIGHT) {
				rightPaddle.moveDown();
			}
		}
		
		// The UP and DOWN keys move both the paddles up and down
		if (keys[4]) {
			if (rightPaddle.getY() > 0) {
				rightPaddle.moveUp();
			}
			if (leftPaddle.getY() > 0) {
				leftPaddle.moveUp();
			}
		}
		if (keys[5]) {
			if (rightPaddle.getY() + rightPaddle.getHeight() < WINDOW_HEIGHT) {
				rightPaddle.moveDown();
			}
			if (leftPaddle.getY() + leftPaddle.getHeight() < WINDOW_HEIGHT) {
				leftPaddle.moveDown();
			}
		}
	}
	
	
	// Check for collisions with paddles
	private void checkCollisions() {
		
		// Collide with left paddle
		if (ball.getX() < leftPaddle.getX() + leftPaddle.getWidth() && ball.getX() > leftPaddle.getX() && ball.getY() + ball.getHeight() > leftPaddle.getY() && ball.getY() < leftPaddle.getY() + leftPaddle.getHeight()) {
			if (ball.getXSpeed() < 0) {
				ball.setXSpeed(- ball.getXSpeed());
			}
		}
		
		// Collide with right paddle
		if (ball.getX() + ball.getWidth() > rightPaddle.getX() && ball.getX() < rightPaddle.getX() + rightPaddle.getWidth() && ball.getY() + ball.getHeight() > rightPaddle.getY() && ball.getY() < rightPaddle.getY() + rightPaddle.getHeight()) {
			if (ball.getXSpeed() > 0) {
				ball.setXSpeed(- ball.getXSpeed());
			}
		}
		
		if(!(ball.getY() >= 0 && ball.getY() <= WINDOW_HEIGHT)) {
			ball.setYSpeed(- ball.getYSpeed());
		}
	}
	
	
	// Check for points
	private void checkPoint() {
		// Ball went past left side
		if (ball.getX() < - BALL_SIDE) {
			ball.setPos(leftPaddle.getX() + leftPaddle.getWidth(), leftPaddle.getY() + leftPaddle.getHeight() / 2 - ball.getHeight() / 2);
			ball.setXSpeed(- ball.getXSpeed());
			rightScore++;
			try {
				Thread.currentThread();
				// Pause after each point
				Thread.sleep(POINT_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Ball went past right side
		if (ball.getX() > WINDOW_WIDTH) {
			ball.setPos(rightPaddle.getX() - rightPaddle.getWidth(), rightPaddle.getY() + rightPaddle.getHeight() / 2 - ball.getHeight() / 2);
			ball.setXSpeed(- ball.getXSpeed());
			leftScore++;
			try {
				Thread.currentThread();
				Thread.sleep(POINT_DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void run() {
		try {
			while (true) {
				Thread.currentThread();
				Thread.sleep(FRAME_DELAY);
				repaint();
			}
		} catch(Exception e) {
			
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		leftPaddle.setY(e.getY() - LEFT_PADDLE_HEIGHT / 2);
		rightPaddle.setY(e.getY() - RIGHT_PADDLE_HEIGHT / 2);
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				keys[0] = true;
				break;
			case KeyEvent.VK_S:
				keys[1] = true;
				break;
			case KeyEvent.VK_I:
				keys[2] = true;
				break;
			case KeyEvent.VK_K:
				keys[3] = true;
				break;
			case KeyEvent.VK_UP:
				keys[4] = true;
				break;
			case KeyEvent.VK_DOWN:
				keys[5] = true;
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// The keys are not pressed down anymore
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			keys[0] = false;
			break;
		case KeyEvent.VK_S:
			keys[1] = false;
			break;
		case KeyEvent.VK_I:
			keys[2] = false;
			break;
		case KeyEvent.VK_K:
			keys[3] = false;
			break;
		case KeyEvent.VK_UP:
			keys[4] = false;
			break;
		case KeyEvent.VK_DOWN:
			keys[5] = false;
			break;
		default:
			break;
	}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}	
}