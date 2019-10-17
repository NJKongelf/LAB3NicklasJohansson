import Obj.*;

import javafx.beans.Observable;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Callback;


public class Model {


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

    public StringProperty selectedItemProperty() {
        return (StringProperty) items;
    }
    public ObservableList<DrawShape> getItems() {
        return items;
    }

}
