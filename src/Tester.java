import javax.swing.*;
import java.awt.*;

public class Tester extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public Tester() {
        super("PONG TESTER");
        setSize(WIDTH, HEIGHT);

        // getContentPane().add(new BlockTestTwo());

        // uncomment when you are ready to test the Ball
        //getContentPane().add(new BallTestTwo());

        PaddleTestTwo padTest = new PaddleTestTwo();
        padTest.setFocusable(true);
        getContentPane().add(padTest);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @SuppressWarnings("unused")
    public static void main(String args[]) {
        Tester run = new Tester();
    }
}