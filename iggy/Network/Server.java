package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;


public class Server implements Constants {

	
	public static void main(String[] args) throws IOException {
		// Create a new server socket
		ServerSocket serverSocket = new ServerSocket(PORT);
		
		// Connect players
		Player p1 = new Player(serverSocket.accept(), 1);
		System.out.println("Player 1 connected.");

		Player p2 = new Player(serverSocket.accept(), 2);
		System.out.println("Player 2 connected.");
		
		// Set opponents to establish PrintWriters
		p1.setOpponent(p2);
		p2.setOpponent(p1);
		
		// Start both threads at the same time
		long time = Calendar.getInstance().getTimeInMillis() + START_DELAY;
		p1.setTime(time);
		p2.setTime(time);
		p1.start();
		p2.start();
		System.out.println("Game is starting.");
		while (true) {
			if (!p1.connected() || !p2.connected()) break;
		}
        serverSocket.close();
	}

}



