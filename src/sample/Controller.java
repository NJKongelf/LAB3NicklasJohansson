package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;

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

    public void initialize() {

    }
        public void LineButtonAction(ActionEvent actionEvent){

        //actionEvent.getEventType ().getName ();
       GraphicsContext gc= Canvas.getGraphicsContext2D ();
/*       gc.getFill (ColorPicker.getCustomColors ());
       gc.fill (actionEvent.g);*/

        }

}
