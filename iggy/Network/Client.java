package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.JFrame;



public class Client extends JFrame implements Constants {
	private static Client window;
	private static Pong game;
	private static Socket socket;
	private static BufferedReader in;
	private static PrintWriter out;
	
	public Client() {
		super("Pong");
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		game = new Pong();
		game.setFocusable(true);			
		getContentPane().add(game);
		setVisible(true);
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, NumberFormatException, InterruptedException {
		// Connect to the server
		socket = new Socket("localhost", PORT);
		
		// Write and read from the server through sockets
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		// Create a new instance of games
		window = new Client();
		
		while (true) {
			String line = in.readLine();
			StringTokenizer s = new StringTokenizer(line);
			System.out.println("RECEIVE: " + line);
			String token = "";
			if (s.hasMoreTokens()) {
				token = s.nextToken();
			}
			if (token.equals(WELCOME_GREETING)) {
				game.setID(Integer.parseInt(s.nextToken()));
				if (s.hasMoreTokens()) {
					token = s.nextToken();
				}
			}
			if (token.equals(WAIT_STRING)) {
				game.setStartTime(Long.parseLong(s.nextToken()));
				if (s.hasMoreTokens()) {
					token = s.nextToken();
				}
			}
			if (token.equals(BOUNCE_STRING)) {
				game.bounce();
				if (s.hasMoreTokens()) {
					token = s.nextToken();
				}
			}
			if (token.equals(POINT_STRING)) {
				game.point();
				if (s.hasMoreTokens()) {
					token = s.nextToken();
				}
			}
			if (token.equals(PADDLE_STRING)) {
				game.setPaddle(Integer.parseInt(s.nextToken()));
				System.out.println("Changed paddle.");
			}
			
			String result = "";
			if (game.hasBounce()) {
				result += BOUNCE_STRING + " ";
			}
			if (game.hasPoint()) {
				result += POINT_STRING + " ";
			}
			result += PADDLE_STRING + " " + game.getPaddle();
			out.println(result);
			System.out.println("SEND: " + result);
		}
	}
}
