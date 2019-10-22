package obj;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public interface DrawShape {
    void setXpos(double xpos);

    void setYpos(double ypos);

    double getXpos();

    DoubleProperty sizeProperty();

    void setSize(double size);

    double getSize();

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
