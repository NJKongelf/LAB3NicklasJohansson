import Obj.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Model {
//    Line line = new Line ();
//    Rectangle rectangle = new Rectangle ();


    private ObservableList<DrawShape> items = FXCollections.observableArrayList();
    public ObservableList<DrawShape> getItems() {
        return items;
    }

}