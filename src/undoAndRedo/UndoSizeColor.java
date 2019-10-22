package undoAndRedo;

import javafx.scene.paint.Color;
import obj.DrawShape;

public class UndoSizeColor implements DoITcmd {
    private DrawShape shape;
    private double size;
    private Color paint;

    public UndoSizeColor(DrawShape shape, double size, Color paint) {
        this.shape = shape;
        this.size = size;
        this.paint = paint;
    }

    @Override
    public void justdoit() {
        shape.setSize (size);
        shape.setPaint (paint);
    }
}
