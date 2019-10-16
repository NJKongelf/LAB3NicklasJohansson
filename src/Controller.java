import Obj.*;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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
    private Shape shape;

    public void initialize() {
        gc= canvas.getGraphicsContext2D ();
        gc.setFill (Color.WHITE);
        model = new Model ();
        ColorPicker.setValue (Color.BLACK);
        droplist.setItems (model.getItems ());
        droplist.setPromptText ("Lager lista");
        droplist.setAccessibleText (model.getItems ().toString ());
        droplist.setOnAction (this::RectangleAction);// TODO sätt metod för updatering av storlek eller färg!!
        model.getItems ().addListener (this::updateCanvasShapes);

    }

    //        public void LineButtonAction(ActionEvent actionEvent){
//            //TODO ändra denna
//            canvas.setOnMousePressed (e-> {
//                line = new Line ();
//                gc.setStroke (ColorPicker.getValue ());
//                line.setStartX (e.getX ());
//               line.setStartY (e.getY ());
//            });
//
//            canvas.setOnMouseReleased (e -> {
//                line.setEndX (e.getX ());
//                line.setEndY (e.getY ());
//                gc.strokeLine (line.getStartX (),line.getStartY (),line.getEndX (),line.getEndY ());
//              //  model.getItems ().add (line);
//            });
//        }
    public void updateCanvasShapes(ListChangeListener.Change<? extends DrawShape> c) {
        //Draw all shapes
        for (DrawShape shape : model.getItems ()) {
            shape.draw (gc, false);
        }
    }


    public void RectangleAction(ActionEvent actionEvent) {

        canvas.setOnMousePressed (e-> {
            double[] pos     = {slider.getValue (), e.getX (), e.getY (), 5, 2.5};
            double   centerX = pos[1] - ((pos[0] * pos[3]) / 2);
            double   centerY = pos[2] - ((pos[0] * pos[4]) / 2);
            rectangle = new Rect (centerX, centerY, pos[0] * pos[3], pos[0] * pos[4], ColorPicker.getValue ());
            rectangle.draw (gc, false);
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
