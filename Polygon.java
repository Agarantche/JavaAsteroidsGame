import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 * Polygon is a sequence of points in space defined by the points
 * themselves, an offset, and a rotation. The offset is the
 * distance between the origin and the center of the shape.
 * The rotation is measured in degrees, 0-360.
 * <p>
 * You are intended to instantiate this class with a set of points that
 * forever defines its shape, and then modify it by repositioning and
 * rotating that shape. In defining the shape, the relative positions
 * of the points you provide are used, in other words: {(0,1),(1,1),(1,0)}
 * is the same shape as {(9,10),(10,10),(10,9)}.
 * NOTE: You don't need to worry about the "magic math" details.
 */
public abstract class Polygon {
    private final Point2D.Double[] shape;   // An array of points
    protected final Point2D.Double position;   // The offset mentioned above
    protected double rotation;  // Zero degrees is due east

    /**
     * Creates a polygon with the specified shape, position, and rotation.
     * @param shape array of points specifying the shape
     * @param position location of the center point of the polygon
     * @param rotation orientation of the polygon
     */
    public Polygon(Point2D.Double[] shape, Point2D.Double position, double rotation) {
        this.shape = shape;
        this.position = position;
        this.rotation = rotation;

        // First, we find the shape's top-most left-most boundary, its origin.
        double left = shape[0].getX();
        double top = shape[0].getY();
        for (Point2D.Double p : shape) {
            if (p.getX() < left) {
                left = p.getX();
            }
            if (p.getY() < top) {
                top = p.getY();
            }
        }
        Point2D.Double origin = new Point2D.Double(left, top);

        // Then, we orient all of its points relative to the real origin.
        for (Point2D.Double p : shape) {
            p.setLocation(p.getX() - origin.getX(), p.getY() - origin.getY());
        }
    }

    /**
     * Gets the points of the polygon with the rotation and offset applied.
     *
     * @return array of points representing the current rotation and offset of the polygon
     */
    public final Point2D.Double[] getPoints() {
        int i = 0;
        Point2D.Double center = findCenter();
        Point2D.Double[] points = new Point2D.Double[shape.length];
        for (Point2D.Double p : shape) {
            double x = ((p.getX() - center.getX()) * Math.cos(Math.toRadians(rotation)))
                    - ((p.getY() - center.getY()) * Math.sin(Math.toRadians(rotation)))
                    + center.getX() / 2 + position.getX();
            double y = ((p.getX() - center.getX()) * Math.sin(Math.toRadians(rotation)))
                    + ((p.getY() - center.getY()) * Math.cos(Math.toRadians(rotation)))
                    + center.getY() / 2 + position.getY();
            points[i++] = new Point2D.Double(x, y);
        }
        return points;
    }

    /**
     * Determines if a point is inside the polygon.
     *
     * @return true if the point is inside
     */
    public final boolean contains(Point2D.Double point) {
        return getShape().contains(point);
    }

    /**
     * Rotates the polygon.
     *
     * @param degrees is the amount the polygon should be rotated
     */
    public void rotate(int degrees) {
        rotation = (rotation + degrees + 360) % 360;
    }

    public double getRotation() { return rotation; }

    public Point2D.Double getPosition() { return (Point2D.Double) position.clone(); }
   
   /*
   The following methods are private access restricted because, as this access
   level always implies, they are intended for use only as helpers of the
   methods in this class that are not private. They can't be used anywhere else.
   */

    /*
     * Magic math that finds the area of the polygon. Used to determine the center.
     *
     * @return area of the polygon
     */
    private double findArea() {
        double sum = 0;
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            sum += shape[i].getX() * shape[j].getY() - shape[j].getX() * shape[i].getY();
        }
        return Math.abs(sum / 2);
    }

    /*
     * Finds the center of the polygon.
     *
     * @return center point
     */
    public Point2D.Double findCenter() {
        Point2D.Double sum = new Point2D.Double(0, 0);
        for (int i = 0, j = 1; i < shape.length; i++, j = (j + 1) % shape.length) {
            sum.setLocation(sum.getX() + (shape[i].getX() + shape[j].getX())
                            * (shape[i].getX() * shape[j].getY() - shape[j].getX() * shape[i].getY()),
                    sum.getY() + (shape[i].getY() + shape[j].getY())
                            * (shape[i].getX() * shape[j].getY() - shape[j].getX() * shape[i].getY()));
        }
        double area = findArea();
        return new Point2D.Double(Math.abs(sum.getX() / (6 * area)), Math.abs(sum.getY() / (6 * area)));
    }

    /**
     * Generates a Path2D.Double giving the current points of the polygon. This path can
     * be used with any other Path2D.Double to see if they intersect. It can also be used
     * to determine whether the polygon contains a point.
     * @return shape of the polygon packaged in a way compatible with existing Java libraries
     */
    public final Path2D.Double getShape() {
        Path2D.Double path = new Path2D.Double();
        Point2D.Double[] points = getPoints();
        path.moveTo(points[0].x, points[0].y);
        for (int i = 1; i < points.length; i++)
            path.lineTo(points[i].x, points[i].y);
        path.closePath();
        return path;
    }

    /**
     * Determines whether the polygon intersects with another polygon.
     * This code is easy to extend to checking for intersections with
     * any other shape that uses a Path2D.Double.
     *
     * @param other polygon to check against for intersection
     * @return true if the two polygons intersect
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
