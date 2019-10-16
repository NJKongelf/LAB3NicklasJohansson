package Obj;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Paint;

abstract class Shape implements DrawShape {

    private DoubleProperty xpos = new SimpleDoubleProperty ();
    private DoubleProperty ypos = new SimpleDoubleProperty();
    private ObjectProperty<Paint> paint = new SimpleObjectProperty<> ();

    public Shape(double xpos, double ypos, Paint paint) {
        setXpos(xpos);
        setYpos(ypos);
        this.paint.setValue(paint);
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

    public Paint getPaint() {
        return paint.get ();
    }

    public ObjectProperty<Paint> paintProperty() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint.set (paint);
    }


    @Override
    public String toString() {
        return getClass ().getSimpleName () + " X:" + (int) xpos.get () + " Y:" + (int) ypos.get ();
    }
}
