import java.awt.*;
import java.awt.geom.Point2D;
import java.security.PublicKey;

public class Ship extends Polygon{
    private boolean thrust;
    private Point2D.Double velocity;
    /**
     * Creates a polygon with the specified shape, position, and rotation.
     *
     * @param position location of the center point of the polygon
     * @param rotation orientation of the polygon
     */
    public Ship(Point2D.Double position, double rotation) {
        super(makeShape(), position, rotation);
        thrust = false;
        velocity = new Point2D.Double(0,0);
    }

    private static Point2D.Double[] makeShape(){
        return new Point2D.Double[]{new Point2D.Double(400, 300),new Point2D.Double(390, 310),new Point2D.Double(420, 300),new Point2D.Double(390, 290)};
    }

    public void accelerate (double acceleration) {
        velocity.x += acceleration * Math.cos(Math.toRadians(rotation));
        velocity.y += acceleration * Math.sin(Math.toRadians(rotation));
    }


    @Override
    public void update() {
        if(thrust) {
            accelerate(0.5);
        }
        position.x = velocity.x + position.x;
        position.y = velocity.y + position.y;
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

    //public void shoot{

    //}
    public void paint(Graphics brush) {
        outline(brush,Color.gray);
        fill(brush,Color.blue);
    }

    public void thrust(boolean thrust) {
        this.thrust = thrust;
    }



}


