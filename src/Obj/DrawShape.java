package Obj;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public interface DrawShape {
    void setXpos(double xpos);

    void setYpos(double ypos);

    double getXpos();

    DoubleProperty sizeProperty();

    //void size(double size);


    DoubleProperty xposProperty();

    double getYpos();

    DoubleProperty yposProperty();

    void draw(GraphicsContext gc, boolean stroke);

    Color getPaint();

    Property<Color> paintProperty();

    void setPaint(Color paint);

    boolean intersects(double x, double y);

    String toSVG();
}
