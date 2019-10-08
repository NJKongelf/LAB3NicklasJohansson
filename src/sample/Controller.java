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
import javafx.scene.shape.Rectangle;

import java.util.Arrays;

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
    private  GraphicsContext gc;
    private  Model model = new Model ();
    private Line line ;
    private Rectangle rectangle;

    public void initialize() {
        gc= canvas.getGraphicsContext2D ();
        model = new Model ();


        //model.selectedItemProperty().bind(Canvas.getSelectionModel().selectedItemProperty());
    }
        public void LineButtonAction(ActionEvent actionEvent){

            canvas.setOnMousePressed (e-> {
                line = new Line ();
                gc.setStroke (ColorPicker.getValue ());
                line.setStartX (e.getX ());
               line.setStartY (e.getY ());
            });

            canvas.setOnMouseReleased (e -> {
                line.setEndX (e.getX ());
                line.setEndY (e.getY ());
                gc.strokeLine (line.getStartX (),line.getStartY (),line.getEndX (),line.getEndY ());
                model.getItems ().add (line);
            });
        }



    public void RectangleAction(ActionEvent actionEvent) {

        canvas.setOnMousePressed (e-> {
            rectangle = new Rectangle ();
            gc.setStroke (ColorPicker.getValue ());
            double x =e.getX();
            double y =e.getY();
//            rectangle.setX (e.getX ());
//            rectangle.setY (e.getY ());


            canvas.setOnMouseReleased (d -> {

                if (x > d.getX()) {
                    if (y > d.getY()) {
                        gc.fillOval(d.getX(), d.getY(), x - d.getX(), y - d.getY());
                    } else if (y < d.getY()) {
                        gc.fillOval(d.getX(), y, x - d.getX(), d.getY() -y);
                    } else
                        gc.fillOval(x, y, d.getX() , d.getY());
                }
                else if (y > d.getY()) {
                    if ( x > d.getX()) {
                        gc.fillOval(d.getX(), d.getY(), x - d.getX(), y - d.getY());
                    } else if (x < d.getX()) {
                        gc.fillOval(x, d.getY(), d.getX() - x , y - d.getY());
                    } else
                        gc.fillOval(x, y, d.getX() - x , d.getY() - x);
                } else
                    gc.fillOval(x, y, d.getX() - x, d.getY() - y);





//                rectangle.setX (kordX[0]);
//                rectangle.setY (KordY[0]);
//
//                    rectangle.setWidth (Math.abs ( (kordX[1] -rectangle.getX ())));
//                    rectangle.setHeight (Math.abs ((KordY[1]- rectangle.getY ())));

               // gc.fillRect (model.rectangle.getX (), model.rectangle.getY (), model.rectangle.getWidth (), model.rectangle.getHeight ());
                gc.strokeRect (rectangle.getX (), rectangle.getY (), rectangle.getWidth (), rectangle.getHeight ());
                model.getItems ().add (rectangle);
                //System.out.println (model.getItems ());
            });
        });
    }
}
