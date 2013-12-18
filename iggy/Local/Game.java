package Local;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame implements Constants {

	public Game() {
		super("Pong");
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		Pong game = new Pong();
		game.setFocusable(true);			
		getContentPane().add(game);
		setVisible(true);
	}

	public static void main(String args[]) {
		@SuppressWarnings("unused")
		Game game = new Game();
	}
}