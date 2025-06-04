import java.awt.*;
import java.awt.geom.Point2D;

public class Asteroid extends Polygon{

    /**
     * Creates a polygon with the specified shape, position, and rotation.
     *
     * @param position location of the center point of the polygon
     * @param rotation orientation of the polygon
     */
    public Asteroid(Point2D.Double[] shape,Point2D.Double position, double rotation) {
        super(shape, position, rotation);
    }

    public void accelerate (double acceleration) {
        position.x += acceleration * Math.cos(Math.toRadians(rotation));
        position.y += acceleration * Math.sin(Math.toRadians(rotation));
    }

    @Override
    public void update() {
        accelerate((Math.random()*2));
        if(position.x > 800){
            position.x = 0;
        } else if (position.x < 0) {
            position.x = 800;
        }
        if (position.y > 600){
            position.y = 0;
        } else if (position.y < 0) {
            position.y = 600;
        }
    }

    @Override
    public void paint(Graphics brush) {
        outline(brush,Color.white);
        fill(brush,Color.black);
    }
}

