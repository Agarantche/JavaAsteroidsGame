import java.awt.*;
import java.awt.geom.Point2D;


public class Bullet extends Circle{
    private Point2D.Double velocity;
    /**
     * Creates a circle with the specified position and diameter.
     *
     * @param position location of the center of the circle
     * @param diameter diameter of the circle
     * @param velocity bullets speed
     */
    private double rotation;
    public Bullet(Point2D.Double position, double diameter,double rotation) {
        super(position, diameter);
        this.rotation = rotation;
        velocity = new Point2D.Double(0,0);
    }

    public void accelerate (double acceleration) {
        velocity.x += acceleration * Math.cos(Math.toRadians(rotation));
        velocity.y += acceleration * Math.sin(Math.toRadians(rotation));
    }

    @Override
    public void update() {
        accelerate(10);
        position.x = velocity.x + position.x;
        position.y = velocity.y + position.y;
    }

    @Override
    public void paint(Graphics brush) {
        outline(brush,Color.yellow);
        fill(brush,Color.red);
    }
}
