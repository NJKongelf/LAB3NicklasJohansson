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
    Canvas canvas;
    @FXML
    ColorPicker ColorPicker;
    GraphicsContext gc;
    Model model = new Model ();

    public void initialize() {
        gc= canvas.getGraphicsContext2D ();

    }
        public void LineButtonAction(ActionEvent actionEvent){

            gc.setLineWidth (6);

            canvas.setOnMousePressed (e-> {
                gc.setFill (ColorPicker.getValue ());
                gc.beginPath ();
                gc.lineTo (e.getX (),e.getY ());
                gc.setStroke (ColorPicker.getValue ());
                gc.stroke ();
            });

            canvas.setOnMouseDragged (e -> {
                gc.setFill (ColorPicker.getValue ());
                gc.lineTo (e.getX (), e.getY ());
                gc.setStroke (ColorPicker.getValue ());
                gc.stroke ();
            });


        //actionEvent.getEventType ().getName ();
      //
/*       gc.getFill (ColorPicker.getCustomColors ());
       gc.fill (actionEvent.g);*/

        }

    public void CanvasAction(MouseEvent mouseEvent) {


//                gc.setLineWidth (6);
//                gc.setFill (ColorPicker.getValue ());
//                Double x = f.getX ();
//                Double y = f.getY ();
//                //gc.strokeLine (x,y,x,y);
//                gc.strokeLine (x, y, x, y);



    }
}
