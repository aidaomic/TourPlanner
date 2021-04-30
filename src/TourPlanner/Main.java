package TourPlanner;

import BuissnessLayer.PdfGenerator;
import DataAccessLayer.Database_Logs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.crypto.Data;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        new PdfGenerator().toursToPdf();

        // fxml created with SceneBuilder
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));

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
