import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Asteroids is a subclass of Game in which all the important work is in the paint method.
 * NOTE: This class is the metaphorical "main method" of your program; it is your control center.
 */
public class Asteroids extends Game implements KeyListener {

   public static int WIDTH = 800;
   public static int HEIGHT = 600;
   private boolean initialized = false;

   private Ship ship;
   private List<Asteroid> asteroids = new ArrayList<Asteroid>();
   private List<Bullet> bullets = new ArrayList<Bullet>();
   private List<Star> stars = new ArrayList<Star>();
   private boolean left;
   private boolean right;

   private boolean fired;

   private int score = 0;
   private boolean gameOver = false;
   // Magic code to make the rendering look better
   private static final RenderingHints HINTS;
   static {
      HINTS = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);
      HINTS.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      HINTS.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
   }

   public Asteroids() {
      super("Asteroids!",WIDTH,HEIGHT);
      addKeyListener(this);


      // Add any necessary initialization

      //Making Ship
      ship = new Ship(new Point2D.Double(400,300),0);

      //Making asteroids
      for (int i = 0; i < 15; i++) {
         Point2D.Double[] shape = new Point2D.Double[]{new Point2D.Double(540, 65),new Point2D.Double(585, 80),new Point2D.Double(600, 40),
                 new Point2D.Double(530, 50),new Point2D.Double(520, 68),new Point2D.Double(540, 65)};
         int ranx = (int) (Math.random() * WIDTH);
         int rany = (int) (Math.random() * height);

         asteroids.add(new Asteroid(shape,new Point2D.Double(ranx, rany),Math.random()*360));
      }

      //This makes stars at random locations
      for (int i = 0; i < 100; i++) {
         int ranx = (int) (Math.random() * WIDTH);
         int rany = (int) (Math.random() * height);
         int randDiam = (int) (Math.random() * 5);
         stars.add(new Star(new Point2D.Double(ranx, rany), randDiam));
      }

      // Make sure this line is last in the constructor
      initialized = true;
      fired = false;
   }

   public static void main (String[] args) {
      new Asteroids();
   }

   @Override
   public void paintComponent(Graphics brush) {
      if (brush instanceof Graphics2D) {
         Graphics2D graphics = (Graphics2D) brush;
         graphics.addRenderingHints(HINTS);
      }

      //Its game over when ship hits an asteroid
      if (!gameOver) {
         // Only draw after initialization
         if (initialized) {
            // Paints background black
            brush.setColor(Color.black);
            brush.fillRect(0, 0, width, height);

            //Displays the Score
            brush.setColor(Color.white);
            brush.drawString("Score " + score, 10, 20);

            //Paints ship on canvas
            ship.paint(brush);

            //updates ship to change position depending on how the ship is being interacted with.
            ship.update();

            //Changes the ships rotation to the left
            if (left) {
               ship.rotate(-5);
            }
            //Changes the ships rotation to the right
            if (right) {
               ship.rotate(5);
            }

            //Bullet maker
            for (int i = 0; i < bullets.size(); i++) {
               Bullet bullet = bullets.get(i);
               bullet.paint(brush);
               bullet.update();
            }

            //This paints the asteroids and checks for intersection between ship and asteroids
            for (int i = 0; i < asteroids.size(); i++) {
               asteroids.get(i).paint(brush);
               asteroids.get(i).update();

               if (asteroids.get(i).intersects(ship)) {
                  gameOver = true;
                  //initialized = false;
               }
            }

            //Checks for collisions between asteroids and bullets
            for (int i = 0; i < bullets.size(); i++) {
               Bullet bullet = bullets.get(i);
               for (int j = 0; j < asteroids.size(); j++) {
                  Asteroid asteroid = asteroids.get(j);
                  if (asteroid.contains(bullet.getPosition())) {
                     bullets.remove(i);
                     asteroids.remove(j);
                     score += 10;
                  }
               }
            }
            //checks if a bullet is fired
            if (fired) {
               bullets.add(new Bullet(new Point2D.Double(ship.getPoints()[2].getX(), ship.getPoints()[2].getY()), 10, ship.rotation));
               fired = false;
            }

            //paints stars
            for (int i = 0; i < stars.size(); i++) {
               stars.get(i).paint(brush);
            }

         }
      }else {
         //Game over screen
         brush.setColor(Color.black);
         brush.fillRect(0, 0, width, height);
         brush.setColor(Color.white);
         brush.drawString("Game Over", width / 2 - 50, height / 2);
         brush.drawString("Final Score: " + score, width / 2 - 50, height / 2 + 20);
      }
   }

   //Keybindings
   @Override
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_W){
         ship.thrust(true);
      }
      if(e.getKeyCode() == KeyEvent.VK_A){
         left = true;
      }
      if(e.getKeyCode() == KeyEvent.VK_D){
         right = true;
      }
      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
         fired = true;
      }


   }

   /* Ignore the methods below. */
   @Override
   public void keyTyped(KeyEvent e) {}
   @Override
   public void keyReleased(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_W){
         ship.thrust(false);
      }
      if(e.getKeyCode() == KeyEvent.VK_A){
         left = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_D){
         right = false;
      }
      if(e.getKeyCode() == KeyEvent.VK_SPACE){
         fired = false;
      }
   }
}