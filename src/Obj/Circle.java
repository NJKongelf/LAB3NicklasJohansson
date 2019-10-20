package Obj;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.String.format;


public class Circle extends Shape {
    //private double radius;
    private DoubleProperty radius = new SimpleDoubleProperty ();

    public Circle(double xpos, double ypos, double radius, Color paint, double size) {
        super(xpos, ypos, paint);
        setRadius (radius);
        super.setSize (size);
    }

    public double getRadius() {
        return radius.get ();
    }

    public DoubleProperty radiusProperty() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius.set (radius);
    }



    @Override
    public void draw(GraphicsContext gc, boolean stroke) {
        gc.setFill (getPaint ());
        gc.fillOval (getXpos () - (getRadius () / 2), getYpos () - (getRadius () / 2), getRadius (), getRadius ());
    }

    @Override
    public boolean intersects(double x, double y) {
        double divider = getRadius () / 2;
        return x > getXpos () - divider && x < getXpos () + divider &&
                y > getYpos () - divider && y < getYpos () + divider;
    }

    @Override
    public String toSVG() {
        return "<circle cx=\"" + (int) getXpos () + "\" cy=\"" + (int) getYpos () + "\" r=\"" + (int) getRadius () / 2 +
                "\" fill=\"#" + Integer.toHexString (getPaint ().hashCode ()).toUpperCase () + "\" />\n";
    }
}
