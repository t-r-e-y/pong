
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Pong extends JPanel {

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private boolean upKey, downKey;
    private BufferedImage back;
    private int scoreA = 0, scoreB = 0;

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

    public Pong() {
        // set up all variables related to the game
        ball = new Ball(280, 230, 10, 10, Color.BLACK, 2, 1);
        leftPaddle = new Paddle(0, 10, 10, 100, 1);
        rightPaddle = new Paddle(550, 10, 10, 100, 5);
        setBackground(Color.WHITE);
        setVisible(true);
        addKeyListener(new Listener());

        Timer timer = new Timer(16, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        timer.start();
    }

    int predictCollision() {
        double raw = (ball.getX() - 10) / (double) -ball.getXSpeed() * ball.getYSpeed() + ball.getY();
        if (raw <= 450 && raw >= 10)
            return (int) raw;
        if (raw > 450)
            if (((int) raw - 10) / 440 % 2 == 0) {
                raw = 10 + (raw - 10) % 440;
            }
            else {
                raw = 10 + (440 - (raw - 10) % 440);
            }
        if (raw < 10)
            if (((int) raw - 10) / 440 % 2 == 0) {
                raw = 10 + (10 - (raw - 10) % 440);
            }
            else if (((int) raw - 10) / 440 % 2 == -1) {
                raw = 440 + (10 + (raw - 10) % 440);
            }
        return (int) raw;
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
        // set up the double buffering to make the game animation nice and
        // smooth
        Graphics2D twoDGraph = (Graphics2D) window;

        // take a snap shop of the current screen and same it as an image
        // that is the exact same width and height as the current screen
        if (back == null) {
            back = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        // create a graphics reference to the back ground image
        // we will draw all changes on the background image
        Graphics2D graphToBack = back.createGraphics();
        super.paint(graphToBack);

        // Your code to implement the painting aspect of Pong will go here
        graphToBack.drawRect(10, 10, 540, 450);
        if (ball.getXSpeed() < 0 && Math.abs(leftPaddle.getY() + 50 - predictCollision()) > 25)
            if (leftPaddle.getY() > predictCollision() - 50) {
                leftPaddle.moveUp();
            } else if (leftPaddle.getY() < predictCollision() + 50) {
                leftPaddle.moveDown();
            }
        graphToBack.setColor(leftPaddle.getColor());
        graphToBack.fillRect(leftPaddle.getX(), leftPaddle.getY(),
                leftPaddle.getWidth(), leftPaddle.getHeight());

        if (upKey && rightPaddle.getY() > 0) {
            rightPaddle.moveUp();
        } else if (downKey && rightPaddle.getY() < 450) {
            rightPaddle.moveDown();
        }
        graphToBack.setColor(rightPaddle.getColor());
        graphToBack.fillRect(rightPaddle.getX(), rightPaddle.getY(),
                rightPaddle.getWidth(), rightPaddle.getHeight());

        ball.move();
        graphToBack.setColor(ball.getColor());
        graphToBack.fillRect(ball.getX(), ball.getY(), ball.getWidth(),
                ball.getHeight());

        if (ball.didCollideLeft(leftPaddle) && ball.getXSpeed() < 0)
            if (ball.didCollideBottom(leftPaddle) || ball.didCollideTop(leftPaddle)) {
                ball.setXSpeed(-ball.getXSpeed());
            }

        if (ball.didCollideRight(rightPaddle) && ball.getXSpeed() > 0)
            if (ball.didCollideBottom(rightPaddle) || ball.didCollideTop(rightPaddle)){
                ball.setXSpeed(-ball.getXSpeed());
            }

        if ((ball.getXSpeed() < 0 && ball.getX() < 0) || (ball.getXSpeed() > 0 && ball.getX() > 550)) {
            if (ball.getX() < 0) {
                ball.setXSpeed(3);
                scoreB++;
                ball.setPos(10, leftPaddle.getY() + 50);
            }
            else {
                ball.setXSpeed(-3);
                scoreA++;
                ball.setPos(550, rightPaddle.getY() + 50);
            }
            ball.setYSpeed(1);
        }

        graphToBack.setFont(new Font("Monospace", Font.PLAIN, 20));
        graphToBack.drawString(Integer.toString(scoreA), 20, 500);
        graphToBack.drawString(Integer.toString(scoreB), 560, 500);

        if ((ball.getY() < 10 && ball.getYSpeed() < 0) || (ball.getY() > 450 && ball.getYSpeed() > 0)) {
            ball.setYSpeed(-ball.getYSpeed());
        }
        twoDGraph.drawImage(back, null, 0, 0);
    }
}
