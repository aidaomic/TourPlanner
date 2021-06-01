package BusinessLayer.Notification;

import BusinessLayer.Logging.LoggingHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Allerts {

    public Allerts() {
    }

    private Alert warning = new Alert(Alert.AlertType.WARNING);
    private LoggingHandler log = new LoggingHandler();

    public void tourIsNull(){
        warning.setTitle("Warning");
        warning.setContentText("Please select a Tour from the List first!");
        warning.showAndWait();
        log.logDebug("Alert => No Tour chosen -Allerts-");
    }

    public void nothingEdited(){
        warning.setTitle("Warning");
        warning.setContentText("There have been no changes to the Tour!");
        warning.showAndWait();
        log.logDebug("Alert => Nothing edited -Allerts-");
    }

    public void duplicatedName() {
        warning.setTitle("Warning");
        warning.setContentText("This Name has already been taken!");
        warning.showAndWait();
        log.logDebug("Alert => Duplicate name!");
    }

    public int allertDelete(String tourToDelete) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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

    public void allertExportSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export");
        alert.setHeaderText("Information regarding tours export");
        alert.setContentText("All Tours have been exported to a file on your File System!");
        alert.showAndWait();
        log.logDebug("Alert => Exported successfully -Allerts-");
    }
}
