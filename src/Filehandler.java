import obj.DrawShape;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Filehandler {

    public void saveFileSVG(Model m, File file, int w, int h) {

        try {
            FileWriter out = new FileWriter (file);
            out.write ("<?xml version=\"1.0\"?>\n" +
                    "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n" +
                    "  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" +
                    "<svg xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "width=\"" + w + "\" height=\"" + h + "\">\n");
            for (DrawShape Shape : m.getItems ()) {
                out.write (Shape.toSVG ());
            }
            out.write ("</svg>");
            out.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
