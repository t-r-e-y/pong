
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PaddleTestTwo extends JPanel {

    private final Ball ball;
    private final Paddle leftPaddle, rightPaddle;
    private boolean upKey, downKey;

    class Listener implements KeyListener {
        /*
         * (non-Javadoc)
         * 
         * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
         */

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        /*
         * (non-Javadoc)
         * 
         * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
         */
        @Override
        public void keyPressed(KeyEvent e) {
            // When up arrow key pressed, start moving paddle up
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upKey = true;
            }
            // When down arrow key pressed, start moving paddle down
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downKey = true;
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
         */
        @Override
        public void keyReleased(KeyEvent e) {
            // When up arrow key no longer pressed, stop moving paddle up
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upKey = false;
            }
            // When down arrow key no longer pressed, stop moving paddle down
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downKey = false;
            }
        }
    }

    public PaddleTestTwo() {
        // set up all game variables

        // instantiate a Ball
        ball = new Ball();

        // instantiate a left Paddle
        leftPaddle = new Paddle(0, 0, 10, 100, 10);

        // instantiate a right Paddle
        rightPaddle = new Paddle(550, 0, 10, 50, 10);

        Timer timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        timer.start();

        // set up the Canvas
        setBackground(Color.WHITE);
        setVisible(true);

        Listener l = new Listener();
        addKeyListener(l);
    }

    int predictCollision() {
        return ((int) ((ball.getX() - 10) / (double) -ball.getXSpeed() * ball.getYSpeed() + ball.getY())) % 440;
    }

    @Override
    public void update(Graphics window) {
        paint(window);
    }

    @Override
    public void paint(Graphics window) {
        super.paint(window);
        if (ball.getXSpeed() < 0 && Math.abs(leftPaddle.getY() + 50 - predictCollision()) > 10)
            if (leftPaddle.getY() > predictCollision() - 50) {
                leftPaddle.moveUp();
            } else if (leftPaddle.getY() < predictCollision() + 50) {
                leftPaddle.moveDown();
            }
        window.setColor(leftPaddle.getColor());
        window.fillRect(leftPaddle.getX(), leftPaddle.getY(),
                leftPaddle.getWidth(), leftPaddle.getHeight());

        if (upKey && rightPaddle.getY() > 0) {
            rightPaddle.moveUp();
        } else if (downKey && rightPaddle.getY() < 550) {
            rightPaddle.moveDown();
        }
        window.setColor(rightPaddle.getColor());
        window.fillRect(rightPaddle.getX(), rightPaddle.getY(),
                rightPaddle.getWidth(), rightPaddle.getHeight());

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
