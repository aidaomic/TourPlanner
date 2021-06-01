import BusinessLayer.Logging.LoggingHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        new LoggingHandler().logInfo("Application Started");

        // fxml created with SceneBuilder
        Parent root = FXMLLoader.load(getClass().getResource("TourPlanner/mainWindow.fxml"));

        // bootstrap "window" named stage
        primaryStage.setTitle("Tour Planner");

        // set scene into stage in defined size
        primaryStage.setScene(new Scene(root, 500, 500));

        // let's go
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
