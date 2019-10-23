import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;
import obj.Circle;
import obj.DrawShape;
import obj.Rect;
import undoAndRedo.DoITcmd;
import undoAndRedo.UndoSizeColor;
import undoAndRedo.Undochange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Model {
    Socket socket;
    private Stack<DoITcmd> undolist = new Stack<> ();
    private ObservableList<DrawShape> items = FXCollections.observableArrayList();
    PrintWriter writer;
    BufferedReader reader;
    ExecutorService threadPool;
    StringProperty message = new SimpleStringProperty ("");
    private ObservableList<String> chatMessages = FXCollections.observableArrayList ();
    private SimpleBooleanProperty connected = new SimpleBooleanProperty (false);

    public ObservableList<DrawShape> getItems() {
        return items;
    }

    //<editor-fold desc="Create shapes">
    public Circle creationOfCircle(double xpos, double ypos, double radius, Color paint, double size) {
        return new Circle (xpos, ypos, radius, paint, size);
    }

    public Rect creationOfRectangle(double xpos, double ypos, double width, double height, Color paint, double size) {
        return new Rect (xpos, ypos, width, height, paint, size);
    }

    //</editor-fold>
    //<editor-fold desc="Undo methods">
    public void undoPushChange() {
        undolist.push (new Undochange (items.get (items.size () - 1), items));
    }

    public void undoPushChangeSizeColor(DrawShape shape) {
        undolist.push (new UndoSizeColor (shape, shape.getSize (), shape.getPaint ()));
    }

    public DrawShape undoPop() {
        DrawShape shape = null;
        if (!(undolist.empty ())) {
            shape = undolist.pop ().justdoit ();
        }
        return shape;
    }
    //</editor-fold>

    public Model() {
        //https://coderanch.com/t/666722/java/Notify-ObservableList-Listeners-Change-Elements
        //Listeners registered for changes on observable list will also
        //be notified about changes on the xpos and ypos on shapes.
        threadPool = Executors.newFixedThreadPool (2);
        items = FXCollections.observableArrayList (
                param -> {
                    if (param instanceof Circle) {
                        return new Observable[]{
                                ((Circle) param).radiusProperty (),
                                param.sizeProperty (),
                                param.xposProperty (),
                                param.yposProperty (),
                                param.paintProperty ()
                        };
                    } else {
                        return new Observable[]{
                                param.sizeProperty (),
                                param.xposProperty (),
                                param.yposProperty (),
                                param.paintProperty ()
                        };
                    }
                }
        );
    }

    public ObservableList<String> getChatMessages() {
        return chatMessages;
    }

    public boolean isConnected() {
        return connected.get ();
    }

    public void setConnected(boolean connected) {
        this.connected.set (connected);
    }

    public SimpleBooleanProperty connectedProperty() {
        return connected;
    }

    public String getMessage() {
        return message.get ();
    }

    public void setMessage(String message) {
        this.message.set (message);
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void connect(String host, Integer port) {

        Task<Socket> task = new Task<Socket> () {
            @Override
            protected Socket call() throws Exception {
                return new Socket (host, port);
            }
        };
        task.setOnRunning (event -> chatMessages.add ("Connecting..."));
        task.setOnFailed (event -> chatMessages.add ("Error connecting"));
        task.setOnSucceeded (event -> chatMessages.add ("Connected"));

        task.valueProperty ().addListener ((observable, oldValue, newValue) -> {
            try {
                socket = newValue;
                writer = new PrintWriter (socket.getOutputStream (), true);
                reader = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
                setConnected (true);
                threadPool.submit (this::receiveMessages);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });
        threadPool.submit (task);
    }

    public void sendMessage() {
        if (message.get ().length () > 0) {
            //Send message to server
            final String mess = message.get ();
            threadPool.submit (() -> writer.println (mess));
            message.setValue ("");
        }
    }

    private void receiveMessages() {
        while (true) {
            try {
                String message = reader.readLine ();
                Platform.runLater (() ->
                        chatMessages.add (message)
                );
            } catch (IOException e) {
                e.printStackTrace ();
                Platform.runLater (() ->
                        setConnected (false)
                );
                return;
            }
        }
    }
}
