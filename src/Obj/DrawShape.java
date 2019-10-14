package Obj;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;


public interface DrawShape {
    void setXpos(double xpos);

    void setYpos(double ypos);

    double getXpos();

    DoubleProperty xposProperty();

    double getYpos();

    DoubleProperty yposProperty();

    void draw(GraphicsContext gc, boolean stroke);

    Paint getPaint();

    ObjectProperty<Paint> paintProperty();

    void setPaint(Paint paint);

    boolean intersects(double x, double y);
}
