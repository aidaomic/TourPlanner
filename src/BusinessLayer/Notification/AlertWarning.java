package BusinessLayer.Notification;

import BusinessLayer.Logging.LoggingHandler;
import javafx.scene.control.Alert;

public class AlertWarning implements BusinessLayer.Notification.Alert {
    private Alert warning;
    private LoggingHandler log;

    @Override
    public void setUpAlert() {
        warning = new Alert(Alert.AlertType.WARNING);
        log = new LoggingHandler();
    }

    public void tourIsNull(){
        setUpAlert();
        warning.setTitle("Warning");
        warning.setContentText("Please select a Tour from the List first!");
        warning.showAndWait();
        log.logDebug("Alert => No Tour chosen -AlertWarning-");
    }

    public void nothingEdited(){
        setUpAlert();
        warning.setTitle("Warning");
        warning.setContentText("There have been no changes to the Tour!");
        warning.showAndWait();
        log.logDebug("Alert => Nothing edited -AlertWarning-");
    }

    public void duplicatedName() {
        setUpAlert();
        warning.setTitle("Warning");
        warning.setContentText("This Name has already been taken!");
        warning.showAndWait();
        log.logDebug("Alert => Duplicate name! -AlertWarning-");
    }

    public void checkData() {
        setUpAlert();
        warning.setTitle("Warning");
        warning.setContentText("There was a problem with MapQuest, please check if Start and End Point are given correctly!");
        warning.showAndWait();
        log.logDebug("Alert => Problem with MapQuest -AlertWarning-");
    }
}
