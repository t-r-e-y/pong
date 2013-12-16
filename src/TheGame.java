import javax.swing.*;

public class TheGame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public TheGame() {
        super("PONG");
        setSize(WIDTH, HEIGHT);

        Pong game = new Pong();

        game.setFocusable(true);
        getContentPane().add(game);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @SuppressWarnings("unused")
    public static void main(String args[]) {
        TheGame run = new TheGame();
    }
}