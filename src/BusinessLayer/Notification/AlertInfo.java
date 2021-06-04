package BusinessLayer.Notification;

import BusinessLayer.Logging.LoggingHandler;
import javafx.scene.control.Alert;

public class AlertInfo implements BusinessLayer.Notification.Alert {
    Alert alert;
    private LoggingHandler log;

    public void alertExportSuccess() {
        setUpAlert();
        alert.setTitle("Export");
        alert.setHeaderText("Information regarding tours export");
        alert.setContentText("All Tours have been exported to a file on your File System!");
        alert.showAndWait();
        log.logDebug("Alert => Exported successfully -AlertInfo-");
    }

    @Override
    public void setUpAlert() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        log = new LoggingHandler();
    }
}