package Models;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.Notification.AlertWarning;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;
import javafx.beans.property.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddLogViewModel {

    private ArrayList logList = new ArrayList();
    private LoggingHandler log = new LoggingHandler();

    public final StringProperty tourName = new SimpleStringProperty("");
    public final StringProperty distance = new SimpleStringProperty("");
    public final StringProperty totalTime = new SimpleStringProperty("");
    public final IntegerProperty rating = new SimpleIntegerProperty();
    public final StringProperty weather = new SimpleStringProperty("");
    public final BooleanProperty seasonalClosYes = new SimpleBooleanProperty();
    public final BooleanProperty seasonalClosNo = new SimpleBooleanProperty();
    public final StringProperty transportation = new SimpleStringProperty("");
    public final BooleanProperty trafficJamYes = new SimpleBooleanProperty();
    public final BooleanProperty trafficJamNo = new SimpleBooleanProperty();
    public final StringProperty fuelUsed = new SimpleStringProperty("");
    public final StringProperty averageSpeed = new SimpleStringProperty("");

    public void changeSceneToMain(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("mainWindow");
    }

    public void addTourLogStage(Stage stage, String tourName) throws IOException {
        if(tourName.equals("null")) {
            log.logDebug("No Tour selected to add Log to -AddLogViewModel-");
            new AlertWarning().tourIsNull();
        }else {
            Database_Tours dbt = new Database_Tours();
            Tour t = dbt.specificTour(tourName);
            new StageLoader(stage, t).changeStage("addLog");
            log.logDebug("Loaded Stage to add Tour Log -AddLogViewModel-");
        }
    }

    public void addTourLog(Stage stage, String logName, String totalTime, String weather, String transportation, Boolean seasonalClosure, Boolean trafficJam, double distance, double rating, double fuelUsed, double averageSpeed) throws IOException {
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
        new StageLoader(stage).changeStage("mainWindow");
        log.logDebug("Tour Log added -AddLogViewModel-");
    }

    //Getter
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

    public BooleanProperty seasonalClosYesProperty() {
        return seasonalClosYes;
    }

    public BooleanProperty seasonalClosNoProperty() {
        return seasonalClosNo;
    }

    public StringProperty transportationProperty() {
        return transportation;
    }

    public BooleanProperty trafficJamYesProperty() {
        return trafficJamYes;
    }

    public BooleanProperty trafficJamNoProperty() {
        return trafficJamNo;
    }

    public StringProperty fuelUsedProperty() {
        return fuelUsed;
    }

    public StringProperty averageSpeedProperty() {
        return averageSpeed;
    }
}
