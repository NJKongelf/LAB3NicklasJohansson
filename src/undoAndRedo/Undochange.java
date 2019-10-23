package undoAndRedo;

import javafx.collections.ObservableList;
import obj.DrawShape;

public class Undochange implements DoITcmd {

    private final ObservableList<DrawShape> undolist;
    private DrawShape shapeForm;

    public Undochange(DrawShape shape, ObservableList<DrawShape> thelist) {
        this.undolist = thelist;
        this.shapeForm = shape;
    }

    @Override
    public DrawShape justdoit() {
        undolist.remove (shapeForm);
        return null;
    }
}
