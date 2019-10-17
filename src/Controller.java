import Obj.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Controller {
    @FXML
    Canvas canvas;
    @FXML
    ColorPicker colorPicker;
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
    private DrawShape shape;

    public void initialize() {
        gc= canvas.getGraphicsContext2D ();
        gc.setFill (Color.WHITE);
        model = new Model ();
        colorPicker.setValue (Color.BLACK);
        droplist.setItems (model.getItems ());
        droplist.setPromptText ("Lager lista");
        model.getItems ().addListener (this::updateCanvasShapes);
        droplist.setAccessibleText (model.getItems ().toString ());
        droplist.getSelectionModel ().selectedItemProperty ().addListener (new ChangeListener<DrawShape> () {
            @Override
            public void changed(ObservableValue<? extends DrawShape> observable, DrawShape oldValue, DrawShape newValue) {
                if (oldValue != null) {
                    (oldValue).sizeProperty ().unbindBidirectional (slider.valueProperty ());
                    (oldValue).paintProperty ().unbindBidirectional (colorPicker.valueProperty ());
                }
                (newValue).sizeProperty ().bindBidirectional (slider.valueProperty ());
                (newValue).paintProperty ().bindBidirectional (colorPicker.valueProperty ());
                shape = (newValue);
            }
        });
    }

    public void updateCanvasShapes(ListChangeListener.Change<? extends DrawShape> c) {

        System.out.println ("IamHERE!");

        if (shape instanceof Rect) {
            double h = 2.5 * (((Rect) shape).getSize ());
            double w = 5 * (((Rect) shape).getSize ());
            ((Rect) shape).setHeight (h);
            ((Rect) shape).setWidth (w);
        }

        //Draw all shapes
        drawShapes ();

    }

    public void drawShapes() {
        //Draw all shapes
        gc.clearRect (0, 0, canvas.getWidth (), canvas.getHeight ());
        for (DrawShape shapes : model.getItems ()) {
            shapes.draw (gc, false);
        }
        System.out.println (model.getItems ());
    }

    public void RectangleAction(ActionEvent actionEvent) {

        canvas.setOnMousePressed (e-> {
            rectangle = new Rect (e.getX (), e.getY (), 5, 2.5, colorPicker.getValue (), slider.getValue ());
            rectangle.draw (gc, false);
            model.getItems ().add (rectangle);
            droplist.setValue (rectangle);
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
