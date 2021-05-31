import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.core.config.Configurator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main extends Application {

    private Logger log;

    @Override
    public void start(Stage primaryStage) throws Exception{

        log = LogManager.getLogger(Main.class);
        Configurator.initialize(null, "BusinessLayer/Logging/logging.conf.xml");

        log.info("entering application");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("exiting application");



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
