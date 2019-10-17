package Obj;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Circle extends Shape {
    //private double radius;
    private DoubleProperty radius = new SimpleDoubleProperty ();

    public Circle(double xpos, double ypos, double radius, Color paint) {
        super(xpos, ypos, paint);
        setRadius (radius);
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

//    @Override
//    public void size(double size) {
//
//    }

    @Override
    public void draw(GraphicsContext gc, boolean stroke) {

    }

    @Override
    public boolean intersects(double x, double y) {
        return false;
    }
}
