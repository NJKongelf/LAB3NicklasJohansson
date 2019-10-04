package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Line;

import java.awt.event.MouseEvent;

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
    Canvas Canvas;
    @FXML
    ColorPicker ColorPicker;

    Model model = new Model ();
    Line line = new Line();
    GraphicsContext gc =Canvas.getGraphicsContext2D ();


    public void initialize() {

    }
        public void LineButtonAction(MouseEvent mouseEvent){




        }

}
