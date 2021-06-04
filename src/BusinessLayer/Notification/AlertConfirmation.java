package BusinessLayer.Notification;

import BusinessLayer.Logging.LoggingHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertConfirmation implements BusinessLayer.Notification.Alert {

    private Alert alert;
    private LoggingHandler log;

    @Override
    public void setUpAlert() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        log = new LoggingHandler();
    }

    public int alertDelete(String tourToDelete) {
        setUpAlert();
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Please check if you want to delete the Tour");
        alert.setContentText(tourToDelete);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            log.logDebug("Alert => Deleting Tour/Log -Allerts-");
            return 0;
        }else{
            log.logDebug("Alert => Not deleting Tour/Log -Allerts-");
            return 1;
        }
    }


    public int reportTourNull() {
        setUpAlert();
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Report - No Tour Chosen");
        alert.setContentText("No Tour has been chosen for Report, do you want to print a Report over all Tours?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            log.logDebug("Alert => Report over all Tours -Allerts-");
            return 0;
        }else{
            log.logDebug("Alert => Stopping generating Report -Allerts-");
            return 1;
        }
    }
}