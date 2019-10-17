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
                    slider.valueProperty ().unbindBidirectional ((oldValue).sizeProperty ());
                    colorPicker.valueProperty ().unbindBidirectional ((oldValue).paintProperty ());
                }
                slider.valueProperty ().bindBidirectional ((newValue).sizeProperty ());
                colorPicker.valueProperty ().bindBidirectional ((newValue).paintProperty ());
                shape = (newValue);
            }
        });
    }

    //<editor-fold desc="Action methods">
    public void MouseMoveAction(MouseEvent mouseEvent) {
        int x = (int) mouseEvent.getX ();
        int y = (int) mouseEvent.getY ();
        MouseValue.setText ("X:" + x + " Y:" + y);
    }
    public void updateCanvasShapes(ListChangeListener.Change<? extends DrawShape> c) {

        //System.out.println ("IamHERE!");

        if (shape instanceof Rect) {
            double h = 2.5 * (((Rect) shape).getSize ());
            double w = 5 * (((Rect) shape).getSize ());
            ((Rect) shape).setHeight (h);
            ((Rect) shape).setWidth (w);
        } else if (shape instanceof Circle) {
            double R = 3 * (((Circle) shape).getSize ());
            if (R < 10) R = 10;
            ((Circle) shape).setRadius (R);
        }

        //Draw all shapes
        drawShapes ();

    }
    //</editor-fold>


    //<editor-fold desc="Shape Creation methods">
    public void RectangleAction(ActionEvent actionEvent) {

        canvas.setOnMouseClicked (e -> {
            double w = slider.getValue () * 5;
            double h = slider.getValue () * 2.5;
            model.getItems ().add (model.creationOfRectangle (e.getX () - (w / 2), e.getY () - (h / 2), w, h, colorPicker.getValue (), slider.getValue ()));
            afterCreationOfShape ();
        });

    }

    public void CircleAction(ActionEvent actionEvent) {

        canvas.setOnMouseClicked (e -> {
            double r = 3 * slider.getValue ();
            model.getItems ().add (model.creationOfCircle (e.getX (), e.getY (), r, colorPicker.getValue (), slider.getValue ()));
            afterCreationOfShape ();
        });
    }

    //</editor-fold>

    //<editor-fold desc="Update methods">
    private void afterCreationOfShape() {
        droplist.setValue (model.getItems ().get (model.getItems ().size () - 1));
        drawShapes ();
    }

    private void drawShapes() {
        //Draw all shapes
        gc.clearRect (0, 0, canvas.getWidth (), canvas.getHeight ());
        for (DrawShape shapes : model.getItems ()) {
            shapes.draw (gc, false);
        }
        System.out.println (model.getItems ());
    }
    //</editor-fold>



    public void ExitChoice() {
        Platform.exit ();
    }


}
