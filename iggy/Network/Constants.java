package Network;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;


public interface Constants {
	// Delay before start of game
	public static final int START_DELAY = 5000;
	
	// Use a port number bigger than 1024
	public static final int PORT = 3000;
	
	public static int WINDOW_WIDTH = 782;
	public static int WINDOW_HEIGHT = 545;
	
	public static int APPLICATION_WIDTH = 800;
	public static int APPLICATION_HEIGHT = 600;
	
	public static final int FRAME_DELAY = 40;
	public static final int POINT_DELAY = 300;
	
	// W, S for left paddle
	// I, K for right paddle
	// UP, DOWN for both paddles
	public static int[] KEYS = {KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_UP, KeyEvent.VK_DOWN};

	public static final int LEFT_PADDLE_WIDTH = 13;
	public static final int LEFT_PADDLE_HEIGHT = 100;
	public static final int LEFT_PADDLE_X = 50;
	public static final Color LEFT_PADDLE_COLOR = Color.RED;
	public static final int LEFT_PADDLE_SPEED = 3;
	
	public static final int RIGHT_PADDLE_WIDTH = 13;
	public static final int RIGHT_PADDLE_HEIGHT = 100;
	public static final int RIGHT_PADDLE_X = WINDOW_WIDTH - 50;
	public static final Color RIGHT_PADDLE_COLOR = Color.RED;
	public static final int RIGHT_PADDLE_SPEED = 3;
	
	public static final Color BALL_COLOR = Color.GREEN;
	public static final int BALL_XSPEED = 3;
	public static final int BALL_YSPEED = 2;
	public static final int BALL_SIDE = 13;
	
	public static final int TEXT_WIDTH = 37;
	public static final int MARGIN = 5;
	public static final Font FONT = new Font(Font.DIALOG, Font.PLAIN, 24);
	
	// Encoded messages cannot have any spaces because of StringTokenizer
	public static final String WELCOME_GREETING = "WELCOME";
	public static final String WAIT_STRING = "WAIT";
	public static final String POINT_STRING = "POINT";
	public static final String BOUNCE_STRING = "BOUNCE";
	public static final String PADDLE_STRING = "PADDLE";
}
