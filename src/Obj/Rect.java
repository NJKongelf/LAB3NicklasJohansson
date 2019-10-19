package Obj;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class Rect extends Shape {
    private double width;
    private double height;

    public Rect(double xpos, double ypos, double width, double height, Color paint, double size) {
        super(xpos, ypos, paint);
        super.setSize (size);
        this.setWidth (width);
        this.setHeight (height);
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

    @Override
    public void draw(GraphicsContext gc, boolean stroke) {

        gc.setFill (getPaint ());
        gc.fillRect (getXpos (), getYpos (), getWidth (), getHeight ());
    }

    @Override
    public boolean intersects(double x, double y) {
        return x > getXpos () && y > getYpos ()
                && x < getXpos () + width && y < getYpos () + height;
    }

    @Override
    public String toSVG() {
        return "<rect x=\"" + (int) getXpos () + "\" y=\"" + (int) getYpos () + "\" width=\"" + (int) width + "\" height=\""
                + (int) height + "\" fill=\"#" + Integer.toHexString (getPaint ().hashCode ()) + "\" />\n";
    }
}
