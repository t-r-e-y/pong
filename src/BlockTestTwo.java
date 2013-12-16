import javax.swing.*;
import java.awt.*;

public class BlockTestTwo extends JPanel {
    public BlockTestTwo() {
        setBackground(Color.WHITE);
    }

    public void paint(Graphics window) {
        Block one = new Block();
        window.setColor(one.getColor());
        window.fillRect(one.getX(), one.getY(), one.getWidth(), one.getHeight());

        Block two = new Block(50, 50, 30, 30);
        window.setColor(two.getColor());
        window.fillRect(two.getX(), two.getY(), two.getWidth(), two.getHeight());

        Block three = new Block(350, 350, 15, 15, Color.red);
        window.setColor(three.getColor());
        window.fillRect(three.getX(), three.getY(), three.getWidth(), three.getHeight());

        Block four = new Block(450, 50, 20, 60, Color.green);
        window.setColor(four.getColor());
        window.fillRect(four.getX(), four.getY(), four.getWidth(), four.getHeight());
    }
}