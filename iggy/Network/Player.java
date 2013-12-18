package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Player extends Thread implements Runnable, Constants {
	private int ID;
	private Player opponent;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private long time;
	private boolean connected = true;
	
	public boolean connected() {
		return connected;
	}
	
	public Player(Socket clientSocket, int ID) throws IOException {
		this.ID = ID;
		this.socket = clientSocket;
		out = new PrintWriter(socket.getOutputStream(), true);
		out.println(WELCOME_GREETING + " " + ID);
	}
	
	
	public Socket getSocket() {
		return socket;
	}
	
	public void setOpponent(Player opponent) throws IOException {
		this.opponent = opponent;
		in = new BufferedReader(new InputStreamReader(opponent.getSocket().getInputStream()));
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public void run() {
		try {
			out.println(WAIT_STRING + time);
			while (true) {
				String inputLine = in.readLine();
				System.out.println("RECEIVE: " + inputLine);
				out.println(inputLine);
				System.out.println("SEND: " + inputLine);
			}
		} catch (IOException e) {
			connected = false;
		}
	}
}