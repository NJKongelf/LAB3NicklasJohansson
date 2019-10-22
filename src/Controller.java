import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import obj.Circle;
import obj.DrawShape;
import obj.Rect;
import undoAndRedo.DoITcmd;
import undoAndRedo.UndoSizeColor;
import undoAndRedo.Undochange;

import java.io.File;
import java.util.Stack;

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

    private Stack<DoITcmd> undolist = new Stack<> ();

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
                } else if (newValue != null) {
                    slider.valueProperty ().bindBidirectional ((newValue).sizeProperty ());
                    colorPicker.valueProperty ().bindBidirectional ((newValue).paintProperty ());
                    shape = (newValue);
                }
            }
        });

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

        //       System.out.println ("IamHERE!");
        while (c.next ())
            if (c.wasUpdated ()) {
                if (shape instanceof Rect) {
                    System.out.println ("Changedlist" + c.getList ());

                    //undolist.push (new UndoSizeColor (shape, ((Rect) shape).getSize (), shape.getPaint ()));
                    double h = 2.5 * (((Rect) shape).getSize ()); //todo ändra här så den fångar rätt typ av ändring, storlek och färg
                    double w = 5 * (((Rect) shape).getSize ());
                    ((Rect) shape).setHeight (h);
                    ((Rect) shape).setWidth (w);
                    // shape.setPaint (colorPicker.getValue ());

                } else if (shape instanceof Circle) {
                    // undolist.push (new UndoSizeColor (shape, ((Circle) shape).getSize (), shape.getPaint ()));
                    double R = 3 * (((Circle) shape).getSize ());

                    if (R < 10) R = 10;
                    ((Circle) shape).setRadius (R);
                    // shape.setPaint (colorPicker.getValue ());

                }

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
                undolist.push (new Undochange (model.getItems ().get (model.getItems ().size () - 1), model.getItems ()));
                afterCreationOfShape ();
            });
        }

    }

    public void CircleAction(ActionEvent actionEvent) {
        if (creationOkOrNot ()) {
            canvas.setOnMouseClicked (e -> {
                double r = 3 * slider.getValue ();
                model.getItems ().add (model.creationOfCircle (e.getX (), e.getY (), r, colorPicker.getValue (), slider.getValue ()));
                undolist.push (new Undochange (model.getItems ().get (model.getItems ().size () - 1), model.getItems ()));
                afterCreationOfShape ();
            });
        }
    }
    //</editor-fold>

    //<editor-fold desc="Update methods">
    private void afterCreationOfShape() {
        droplist.setValue (model.getItems ().get (model.getItems ().size () - 1));
        //drawShapes ();
        creationOkOrNot ();
    }

    public boolean creationOkOrNot() {
        if (toggle.isSelected ()) {
            toggle.setText ("Skapa");
            shapeChoiceMenu.setDisable (false);
            return true;
        } else {
            toggle.setText ("Markera");
            canvas.setOnMouseClicked (this::mouseClickedOnCanvas);
            shapeChoiceMenu.setDisable (true);
            return false;
        }
    }
    private void drawShapes() {
        //Draw all shapes
        gc.clearRect (0, 0, canvas.getWidth (), canvas.getHeight ());
        if (!model.getItems ().isEmpty ()) {
            for (DrawShape shapes : model.getItems ()) {
                shapes.draw (gc, false);
            }
            System.out.println (model.getItems ());
        }
    }
    //</editor-fold>

    public void saveFileDialog(ActionEvent actionEvent) {
        //Show a file dialog that returns a selected file for opening or null if no file was selected.
        FileChooser fileChooser = new FileChooser ();
        fileChooser.setTitle ("Spara fil");
        fileChooser.setInitialDirectory (new File (System.getProperty ("user.dir")));
        fileChooser.getExtensionFilters ().addAll (
                new FileChooser.ExtensionFilter ("SVG", "*.svg"));
        Filehandler filehandler = new Filehandler ();
        path = fileChooser.showSaveDialog (stage);


        //Path can be null if abort was selected
        if (path != null) {
            //We have a valid File object. Use with FileReader or FileWriter
            System.out.println (path.getAbsolutePath ());
            filehandler.saveFileSVG (model, path, (int) canvas.getWidth (), (int) canvas.getHeight ());
        } else {
            //No file selected
            System.out.println ("no file");
        }
    }

    public void init(Scene scene) {
        //Capture Ctrl-Z for undo
        scene.addEventFilter (KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent> () {
                    final KeyCombination ctrlZ = new KeyCodeCombination (KeyCode.Z,
                            KeyCombination.CONTROL_DOWN);
                    public void handle(KeyEvent ke) {
                        if (ctrlZ.match (ke)) {
                            if (!(undolist.empty ())) {

                                undolist.pop ().justdoit ();

                            }
                            ke.consume (); // <-- stops passing the event to next node
                            // drawShapes ();
                            System.out.println ("Undo");

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

    public void SizeChanged(MouseEvent dragEvent) {
        colorChanged (dragEvent);
    }

    public void colorChanged(Event event) {
        undolist.push (new UndoSizeColor (shape, shape.getSize (), colorPicker.getValue ()));
    }

    //TODO  UNDO/REDO Lista


}
