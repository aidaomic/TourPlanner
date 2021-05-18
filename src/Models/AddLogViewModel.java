package Models;

import BuissnessLayer.Notification.Allerts;
import BuissnessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import TourPlanner.Log;
import TourPlanner.Tour;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddLogViewModel {

    public void changeSceneToMain(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../TourPlanner/mainWindow.fxml"));
        stage.setTitle("Tour Planner");
        stage.setScene(new Scene(root, 500, 500)); //v=breite v1=höhe
        stage.show();
    }

    public void addTourLogStage(Stage stage, String tourName) throws IOException {
        if(tourName.equals("null"))
            new Allerts().tourIsNull();
        else {
            Database_Tours dbt = new Database_Tours();
            Tour t = dbt.specificTour(tourName);
            new StageLoader(stage, t).changeStageForLog("TourLog/AddTourLog");
        }
    }

    public void addTourLog(Stage stage, String logName, String totalTime, String weather, String transportation, String seasonalClosure, String trafficJam, double distance, double rating, double fuelUsed, double averageSpeed) throws IOException {
        Log log = new Log(logName, totalTime, weather, transportation, seasonalClosure, trafficJam, distance, rating, fuelUsed, averageSpeed);
        new Database_Logs().save(log);
        changeSceneToMain(stage);
    }

}
