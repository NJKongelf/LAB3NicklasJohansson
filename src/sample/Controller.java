package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;

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
        GraphicsContext gc= Canvas.getGraphicsContext2D ();
    }
        public void LineButtonAction(ActionEvent actionEvent){
            GraphicsContext gc= Canvas.getGraphicsContext2D ();
        gc.setLineWidth (1);
        gc.setFill (ColorPicker.getValue ());

        //actionEvent.getEventType ().getName ();
      //
/*       gc.getFill (ColorPicker.getCustomColors ());
       gc.fill (actionEvent.g);*/

        }

    public void CanvasAction(MouseEvent mouseEvent) {
        GraphicsContext gc= Canvas.getGraphicsContext2D ();
        Canvas.setOnMouseClicked (e-> {
            Canvas.setOnMouseDragged (f -> {
                Double x = f.getX ();
                Double y = f.getY ();
                //gc.strokeLine (x,y,x,y);
                gc.strokeLine (x, y, x, y);
            });

        });
    }
}
