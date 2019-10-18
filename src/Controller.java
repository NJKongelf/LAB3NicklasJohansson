import Obj.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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
    @FXML
    ToggleButton toggle;
    @FXML
    Menu shapeChoiceMenu;

    private  GraphicsContext gc;
    private Model model;
    private DrawShape shape;
    private Stage stage;
    private File path;

    public Controller(Model model) {
        this.model = model;
    }

    public void initialize() {
        gc= canvas.getGraphicsContext2D ();
        gc.setFill (Color.WHITE);
        //     toggle.setSelected (false);
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
        shapeChoiceMenu.setDisable (true);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
        if (creationOkOrNot ()) {
            canvas.setOnMouseClicked (e -> {
                double w = slider.getValue () * 5;
                double h = slider.getValue () * 2.5;
                model.getItems ().add (model.creationOfRectangle (e.getX () - (w / 2), e.getY () - (h / 2), w, h, colorPicker.getValue (), slider.getValue ()));
                afterCreationOfShape ();
            });
        }

    }

    public void CircleAction(ActionEvent actionEvent) {
        if (creationOkOrNot ()) {
            canvas.setOnMouseClicked (e -> {
                double r = 3 * slider.getValue ();
                model.getItems ().add (model.creationOfCircle (e.getX (), e.getY (), r, colorPicker.getValue (), slider.getValue ()));
                afterCreationOfShape ();
            });
        }
    }
    //</editor-fold>

    //<editor-fold desc="Update methods">
    private void afterCreationOfShape() {
        droplist.setValue (model.getItems ().get (model.getItems ().size () - 1));
        drawShapes ();
        creationOkOrNot ();
    }

    public boolean creationOkOrNot() {
        if (!(toggle.isSelected ())) {
            toggle.setText ("Markera");
            canvas.setOnMouseClicked (this::mouseClickedOnCanvas);
            shapeChoiceMenu.setDisable (true);
            return false;
        } else {
            toggle.setText ("Skapa");
            shapeChoiceMenu.setDisable (false);
            return true;
        }
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

    public void openFileDialog(ActionEvent actionEvent) {
        //Show a file dialog that returns a selected file for opening or null if no file was selected.
        FileChooser fileChooser = new FileChooser ();
        fileChooser.setTitle ("Öppna fil");
        fileChooser.getExtensionFilters ().addAll (
                //new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter ("SVG", "*.svg"));

        path = fileChooser.showOpenDialog (stage);

        //Path can be null if abort was selected
        if (path != null) {
            //We have a valid File object. Use with FileReader or FileWriter
            System.out.println (path.getAbsolutePath ());
        } else {
            //No file selected
            System.out.println ("no file");
        }
    }

    public void init(Scene scene) {
        //Capture Ctrl-Z for undo    TODO KIKA på denna för att fånga knapptryck
        scene.addEventFilter (KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent> () {
                    final KeyCombination ctrlZ = new KeyCodeCombination (KeyCode.Z,
                            KeyCombination.CONTROL_DOWN);
                    final KeyCombination ctrlShiftZ = new KeyCodeCombination (KeyCode.Z,
                            KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);

                    public void handle(KeyEvent ke) {
                        if (ctrlZ.match (ke)) {
                            //Undo
                            System.out.println ("Undo");
                            ke.consume (); // <-- stops passing the event to next node
                        } else if (ctrlShiftZ.match (ke)) {
                            //Redo
                            System.out.println ("Redo");
                            ke.consume ();
                        }
                    }
                });


    }

    public void ExitChoice() {
        Platform.exit ();
    }

    public void mouseClickedOnCanvas(MouseEvent event) {
        double x = event.getX ();
        double y = event.getY ();

        for (DrawShape item : model.getItems ()) {
            if (item.intersects (x, y)) {
                droplist.setValue (item);
            }
        }

    }

    //TODO  Skapa SVG spara funktion

    //TODO  UNDO/REDO Lista

    //TODO VG:SERVER/CHATT  läsning av SVG format ifrån server

}
