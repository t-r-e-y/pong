
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BallTestTwo extends JPanel {

    private final Ball ball;

    public BallTestTwo() {
        setBackground(Color.WHITE);
        setVisible(true);

        // instantiate a new Ball
        // test each constructor
        ball = new Ball();

        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void update(Graphics window) {
        paint(window);
    }

    @Override
    public void paint(Graphics window) {
        super.paint(window);
        ball.move();
        window.setColor(ball.getColor());
        window.fillRect(ball.getX(), ball.getY(), ball.getWidth(),
                ball.getHeight());

        if (!(ball.getX() >= 10 && ball.getX() <= 550)) {
            ball.setXSpeed(-ball.getXSpeed());
        }

        if (!(ball.getY() >= 10 && ball.getY() <= 450)) {
            ball.setYSpeed(-ball.getYSpeed());
        }
    }
}
