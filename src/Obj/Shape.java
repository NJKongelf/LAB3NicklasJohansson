package Obj;

import javafx.beans.property.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

abstract class Shape implements DrawShape {

    private DoubleProperty xpos = new SimpleDoubleProperty ();
    private DoubleProperty ypos = new SimpleDoubleProperty();
    private ObjectProperty<Color> paint = new SimpleObjectProperty<> ();
    private DoubleProperty size = new SimpleDoubleProperty ();

    public Shape(double xpos, double ypos, Color paint) {
        setXpos(xpos);
        setYpos(ypos);
        this.paint.setValue(paint);
        // setSize (size);
    }

    public double getSize() {
        return size.get ();
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(double size) {
        this.size.set (size);
    }

    public double getXpos() {
        return xpos.get ();
    }

    public DoubleProperty xposProperty() {
        return xpos;
    }

    public void setXpos(double xpos) {
        this.xpos.set (xpos);
    }

    public double getYpos() {
        return ypos.get ();
    }

    public DoubleProperty yposProperty() {
        return ypos;
    }

    public void setYpos(double ypos) {
        this.ypos.set (ypos);
    }

    public Color getPaint() {
        return paint.get ();
    }

    public Property<Color> paintProperty() {
        return paint;
    }

    public void setPaint(Color paint) {
        this.paint.set (paint);
    }


    @Override
    public String toString() {
        return getClass ().getSimpleName () + " X:" + (int) xpos.get () + " Y:" + (int) ypos.get ();
    }
}
