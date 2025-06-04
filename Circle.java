import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Circle describes a circle located somewhere in space with a given diameter.
 */
public abstract class Circle {
    protected final Point2D.Double position; // Location of the circle's center

    private final double diameter; // Diameter of the circle

    /**
     * Creates a circle with the specified position and diameter.
     * @param position location of the center of the circle
     * @param diameter diameter of the circle
     */
    public Circle(Point2D.Double position, double diameter) {
        this.position = position;
        this.diameter = diameter;
    }

    /**
     * Generates a Path2D.Double giving the shape of the circle in space. This path can
     * be used with any other Path2D.Double to see if they intersect. It can also be used
     * to determine whether the circle contains a point.
     * @return shape of the circle packaged in a way compatible with existing Java libraries
     */
    public Shape getShape() {
        return new Ellipse2D.Double(position.x - diameter/2.0, position.y - diameter/2.0, diameter, diameter);
    }

    public Point2D.Double getPosition() {
        return (Point2D.Double) position.clone();
    }

    /**
     * Determines whether the circle intersects with another circle.
     * This code is easy to extend to checking for intersections with
     * any other shape that uses a Path2D.Double.
     *
     * @param other circle to check against for intersection
     * @return true if the two circles intersect
     */
    public boolean intersects(Polygon other) {
        Area area = new Area(getShape());
        Area otherArea = new Area(other.getShape());
        area.intersect(otherArea);
        return !area.isEmpty();
    }

    /**
     * Draw outline of shape in a given color.
     *
     * @param brush object used for drawing
     * @param color color for outline
     */
    protected final void outline(Graphics brush, Color color) {
        Graphics2D graphics2D = (Graphics2D) brush;
        graphics2D.setColor(color);
        graphics2D.draw(getShape());
    }

    /**
     * Fill in the shape with the given color.
     *
     * @param brush object used for drawing
     * @param color color to fill with
     */
    protected final void fill(Graphics brush, Color color) {
        Graphics2D graphics2D = (Graphics2D) brush;
        graphics2D.setColor(color);
        graphics2D.fill(getShape());
    }

    /**
     * Override this method to update the object every frame.
     */
    public abstract void update();
    /**
     * Override this method to paint the object every frame.
     */
    public abstract void paint(Graphics brush);
}