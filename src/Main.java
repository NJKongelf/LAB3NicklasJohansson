import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader (getClass ().getResource ("sample.fxml"));
        Model      model  = new Model ();
        loader.setControllerFactory (param -> new Controller (model));
        Parent root = loader.load ();

        Controller controller = loader.getController ();
        controller.setStage (primaryStage);

        primaryStage.setTitle("Shapes and Colors");
        primaryStage.setScene (new Scene (root, 990, 470));
        controller.init (primaryStage.getScene ());
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }
}
