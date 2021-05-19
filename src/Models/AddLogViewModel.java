package Models;

import BuissnessLayer.Notification.Allerts;
import BuissnessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import TourPlanner.Log;
import TourPlanner.Tour;
import javafx.beans.property.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddLogViewModel {

    private ArrayList logList = new ArrayList();

    public final StringProperty tourName = new SimpleStringProperty("");
    public final StringProperty distance = new SimpleStringProperty("");
    public final StringProperty totalTime = new SimpleStringProperty("");
    public final IntegerProperty rating = new SimpleIntegerProperty();
    public final StringProperty weather = new SimpleStringProperty("");
    public final BooleanProperty seasonalClos = new SimpleBooleanProperty();
    public final StringProperty transportation = new SimpleStringProperty("");
    public final BooleanProperty trafficJam = new SimpleBooleanProperty();
    public final StringProperty fuelUsed = new SimpleStringProperty("");
    public final StringProperty averageSpeed = new SimpleStringProperty("");

    public StringProperty tourNameProperty(){
        return tourName;
    }

    public StringProperty distanceProperty(){
        return distance;
    }

    public StringProperty totalTimeProperty() {
        return totalTime;
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public StringProperty weatherProperty() {
        return weather;
    }

    public Property<Boolean> seasonalClosProperty() {
        return seasonalClos;
    }

    public StringProperty transportationProperty() {
        return transportation;
    }

    public Property<Boolean> trafficJamProperty() {
        return trafficJam;
    }

    public StringProperty fuelUsedProperty() {
        return fuelUsed;
    }

    public StringProperty averageSpeedProperty() {
        return averageSpeed;
    }

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

    public void addTourLog(Stage stage, String logName, String totalTime, String weather, String transportation, Boolean seasonalClosure, Boolean trafficJam, double distance, double rating, double fuelUsed, double averageSpeed) throws IOException {
        Log log = new Log(logName, totalTime, weather, transportation, seasonalClosure, trafficJam, distance, rating, fuelUsed, averageSpeed);

        logList.clear();
        logList.add(logName);
        logList.add(distance);
        logList.add(totalTime);
        logList.add(rating);
        logList.add(weather);
        logList.add(seasonalClosure);
        logList.add(transportation);
        logList.add(trafficJam);
        logList.add(fuelUsed);
        logList.add(averageSpeed);

        new Database_Logs().save(logList);
        changeSceneToMain(stage);
    }

}
