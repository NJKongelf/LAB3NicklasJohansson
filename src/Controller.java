import Obj.Rect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Controller {
    @FXML
    Canvas canvas;
    @FXML
    ColorPicker ColorPicker;
    @FXML
    Label MouseValue;

    private  GraphicsContext gc;
    private  Model model = new Model ();
    private Line line ;
    private Rect rectangle;

    public void initialize() {
        gc= canvas.getGraphicsContext2D ();
        model = new Model ();
        ColorPicker.setValue (Color.BLACK);


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
              //  model.getItems ().add (line);
            });
        }



    public void RectangleAction(ActionEvent actionEvent) {

        canvas.setOnMousePressed (e-> {
            rectangle = new Rect (e.getX (),e.getY (),40,20,ColorPicker.getValue ());
            gc.setStroke (ColorPicker.getValue ());
//            double x =e.getX();
//            double y =e.getY();



            canvas.setOnMouseReleased (d -> {
                gc.setFill (ColorPicker.getValue ());
                //gc.fillOval (rectangle.getXpos (),rectangle.getYpos (),rectangle.getWidth (),rectangle.getHeight ());
                gc.fillRect (rectangle.getXpos (),rectangle.getYpos (),rectangle.getWidth (),rectangle.getHeight ());

                /*

                double width = Math.abs(d.getX() - x);
                double height = Math.abs(d.getY() - y);

                rectangle.setX (Math.min(d.getX (), x));
                rectangle.setY (Math.min(d.getY (), y));
//
                    rectangle.setWidth (width);
                    rectangle.setHeight (height);

               // gc.fillRect (model.rectangle.getX (), model.rectangle.getY (), model.rectangle.getWidth (), model.rectangle.getHeight ());
               / gc.strokeRect (rectangle);
              //  model.getItems ().add (rectangle);
  */
              //  System.out.println (model.getItems ());
            });
        });
    }

    public void MouseMoveAction(MouseEvent mouseEvent) {
        double x =mouseEvent.getX ();
        double y=mouseEvent.getY ();

        MouseValue.setText ("X:"+x+" Y:"+y);
    }
}
