package Models;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.Notification.AlertWarning;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_Logs;
import TourPlanner.Log;
import TourPlanner.LogTable;
import javafx.beans.property.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EditLogViewModel {

    public LoggingHandler log = new LoggingHandler();

    public final StringProperty logId= new SimpleStringProperty("");
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

    //Methods
    public void changeSceneToMain(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("mainWindow");
    }

    public void editTourLogStage(Stage stage, LogTable logToEdit) throws IOException {
        if(logToEdit == null) {
            new AlertWarning().tourIsNull();
            log.logDebug("Stage for editing TourLog not loaded, because no Tour was selected -EditLogViewModel-");
        }else {
            new StageLoader(stage, logToEdit).changeStage("editLog");
        }
    }

    public void editTour(Stage stage, Log tourLog) throws IOException {
        new Database_Logs().edit(tourLog);
        new StageLoader(stage).changeStage("mainWindow");
        log.logDebug("Stage for editing Tour Log loaded -EditLogViewModel-");
    }

    //Properties
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

    public Property<String> logIdProperty() {
        return logId;
    }
}
