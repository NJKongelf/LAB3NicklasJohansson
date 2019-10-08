package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;

public class Controller {
    @FXML
    Button CircleButton;
    @FXML
    Button RectangleButton;
    @FXML
    Button LineButton;
    @FXML
    Button PointButton;
    @FXML
    ChoiceBox Choicebox;
    @FXML
    Canvas canvas;
    @FXML
    ColorPicker ColorPicker;
    GraphicsContext gc;
    Model model = new Model ();

    public void initialize() {
        gc= canvas.getGraphicsContext2D ();
        model = new Model ();
    }
        public void LineButtonAction(ActionEvent actionEvent){


            canvas.setOnMousePressed (e-> {
                gc.setStroke (ColorPicker.getValue ());
                model.line.setStartX (e.getX ());
                model.line.setStartY (e.getY ());
            });

            canvas.setOnMouseReleased (e -> {
                model.line.setEndX (e.getX ());
                model.line.setEndY (e.getY ());
                gc.strokeLine (model.line.getStartX (),model.line.getStartY (),model.line.getEndX (),model.line.getEndY ());
            });
        }

    public void CanvasAction(MouseEvent mouseEvent) {






    }
}
