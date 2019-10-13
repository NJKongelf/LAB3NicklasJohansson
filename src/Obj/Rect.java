package Obj;

import javafx.scene.paint.Paint;

public class Rect extends Shape {
    double width;
    double height;

    public Rect(double xpos, double ypos, double width, double height, Paint paint) {
        super(xpos, ypos, paint);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
