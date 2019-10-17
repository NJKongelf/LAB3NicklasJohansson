package Obj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Rect extends Shape {
    private double width;
    private double height;

    public Rect(double xpos, double ypos, double width, double height, Color paint, double size) {
        super(xpos, ypos, paint);
        super.setSize (size);
        this.setWidth (super.getSize () * width);
        this.setHeight (super.getSize () * height);
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

//    @Override
////    public void size(double size) {
////        this.width=this.width*(size/10);
////        this.height=this.height*(size/10);
////    }

    @Override
    public void draw(GraphicsContext gc, boolean stroke) {

        gc.setFill (getPaint ());
        gc.fillRect (getXpos (), getYpos (), getWidth (), getHeight ());
    }

    @Override
    public boolean intersects(double x, double y) {
        return false;
    }
}
