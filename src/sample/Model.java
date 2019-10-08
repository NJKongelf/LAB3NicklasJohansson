package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.net.DatagramSocket;

public class Model {
//    Line line = new Line ();
//    Rectangle rectangle = new Rectangle ();


    private ObservableList<Shape> items = FXCollections.observableArrayList();
    public ObservableList<Shape> getItems() {
        return items;
    }

}
