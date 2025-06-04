import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Game is an abstract class that provides a painted canvas in its own window, updated 30 times per second.
 * NOTE: You don't need to understand the details here. DO NOT EDIT THIS CLASS!
 */
public abstract class Game extends JPanel {

    // Delay is in milliseconds, 1000/30 is about 30 times a second
    public static final int DELAY = 1000 / 30;
    protected int width, height;
    protected Image buffer;

    /**
     * The constructor for Game initializes the window
     *
     * @param name   is the name that will be displayed in the window title bar
     * @param width  is the width of the window in pixels
     * @param height is the height of the window in pixels
     */
    public Game(String name, int width, int height) {
        this.width = width;
        this.height = height;

        // A JFrame is a window object.
        JFrame frame = new JFrame(name);
        frame.add(this);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Needed to make key listeners work
        setFocusable(true);
        requestFocusInWindow();

        buffer = createImage(width, height);
        Timer timer = new Timer(DELAY, e -> repaint());

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timer.stop();
                frame.dispose();
            }
        });

        timer.start();
    }

    /**
     * A child class's paintComponent will be called 30 times per second
     * to update and redraw the game.
     */
    @Override
    abstract public void paintComponent(Graphics brush);
}