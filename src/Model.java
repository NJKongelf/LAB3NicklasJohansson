import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import obj.Circle;
import obj.DrawShape;
import obj.Rect;
import undoAndRedo.DoITcmd;
import undoAndRedo.UndoSizeColor;
import undoAndRedo.Undochange;

import java.util.Stack;
public class Model {
    Rect rect;
    Circle circle;
    private Stack<DoITcmd> undolist = new Stack<> ();
    private ObservableList<DrawShape> items = FXCollections.observableArrayList();

    public Model() {
        //https://coderanch.com/t/666722/java/Notify-ObservableList-Listeners-Change-Elements
        //Listeners registered for changes on observable list will also
        //be notified about changes on the xpos and ypos on shapes.
        items = FXCollections.observableArrayList (
                param -> {
                    if (param instanceof Circle) {
                        return new Observable[]{
                                ((Circle) param).radiusProperty (),
                                param.sizeProperty (),
                                param.xposProperty (),
                                param.yposProperty (),
                                param.paintProperty ()
                        };
                    } else {
                        return new Observable[]{
                                param.sizeProperty (),
                                param.xposProperty (),
                                param.yposProperty (),
                                param.paintProperty ()
                        };
                    }
                }
        );
    }
    public ObservableList<DrawShape> getItems() {
        return items;
    }

    //<editor-fold desc="Create shapes">
    public Circle creationOfCircle(double xpos, double ypos, double radius, Color paint, double size) {
        return new Circle (xpos, ypos, radius, paint, size);
    }

    public Rect creationOfRectangle(double xpos, double ypos, double width, double height, Color paint, double size) {
        return new Rect (xpos, ypos, width, height, paint, size);
    }

    //</editor-fold>
    //<editor-fold desc="Undo methods">
    public void undoPushChange() {
        undolist.push (new Undochange (items.get (items.size () - 1), items));
    }

    public void undoPushChangeSizeColor(DrawShape shape) {
        undolist.push (new UndoSizeColor (shape, shape.getSize (), shape.getPaint ()));
    }

    public void undoPop() {
        if (!(undolist.empty ())) {
            undolist.pop ().justdoit ();
        }
    }
    //</editor-fold>
}
