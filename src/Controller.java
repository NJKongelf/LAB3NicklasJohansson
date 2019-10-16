import Obj.*;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
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
    @FXML
    ComboBox<DrawShape> droplist;
    @FXML
    Slider slider;


    private  GraphicsContext gc;
    private  Model model = new Model ();
    private Line line ;
    private Rect rectangle;

    public void initialize() {
        gc= canvas.getGraphicsContext2D ();
        gc.setFill (Color.WHITE);
        model = new Model ();
        ColorPicker.setValue (Color.BLACK);
        droplist.setItems (model.getItems ());
        // slider.setValue (50);
        droplist.setPromptText ("Lager lista");
        droplist.setAccessibleText (model.getItems ().toString ());

        //model.selectedItemProperty().bind(Canvas.getSelectionModel().selectedItemProperty());
    }
        public void LineButtonAction(ActionEvent actionEvent){
            //TODO ändra denna
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
            double s =slider.getValue ();

            rectangle = new Rect (e.getX (),e.getY (),5*s,2.5*s,ColorPicker.getValue ());

                gc.setFill (ColorPicker.getValue ());
                gc.fillRect (rectangle.getXpos (),rectangle.getYpos (),rectangle.getWidth (),rectangle.getHeight ());

               model.getItems ().add (rectangle);

              //  System.out.println (model.getItems ());
            });

    }

    public void MouseMoveAction(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX ();
        int y = (int) mouseEvent.getY ();
        MouseValue.setText ("X:"+x+" Y:"+y);
    }

    public void ExitChoice() {
        Platform.exit ();
    }
}
