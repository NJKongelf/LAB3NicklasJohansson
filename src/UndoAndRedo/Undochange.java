package UndoAndRedo;
import Obj.DrawShape;
import javafx.collections.ObservableList;

public class Undochange implements DoITcmd {

    private final ObservableList<DrawShape> undolist;
    private DrawShape shapeForm;

    public Undochange(DrawShape shape, ObservableList<DrawShape> thelist) {
        this.undolist = thelist;
        this.shapeForm = shape;
    }

    @Override
    public void justdoit() {
        undolist.remove (shapeForm);
    }
}
