package Obj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Circle extends Shape {
    private double radius;

    public Circle(double xpos, double ypos, double radius, Paint paint) {
        super(xpos, ypos, paint);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gc, boolean stroke) {

    }

    @Override
    public boolean intersects(double x, double y) {
        return false;
    }
}
