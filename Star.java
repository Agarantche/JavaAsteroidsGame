import java.awt.*;
import java.awt.geom.Point2D;

public class Star extends Circle{
    /**
     * Creates a circle with the specified position and diameter.
     *
     * @param position location of the center of the circle
     * @param diameter diameter of the circle
     */
    public Star(Point2D.Double position, double diameter) {
        super(position, diameter);
    }


    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics brush) {
        fill(brush,Color.white);
    }
}
