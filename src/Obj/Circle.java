package Obj;

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
}
