import Obj.*;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Model {
//    Line line = new Line ();
//    Rectangle rectangle = new Rectangle ();


    private ObservableList<DrawShape> items = FXCollections.observableArrayList();
    public StringProperty selectedItemProperty() {
        return (StringProperty) items;
    }
    public ObservableList<DrawShape> getItems() {
        return items;
    }

}
