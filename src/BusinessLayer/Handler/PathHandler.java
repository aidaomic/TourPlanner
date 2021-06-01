package BusinessLayer.Handler;

import BusinessLayer.Logging.LoggingHandler;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class PathHandler {

    private LoggingHandler log = new LoggingHandler();

    public String inputPath(){
        TextInputDialog dialog = new TextInputDialog("path to file");
        dialog.setTitle("Path Input Dialog");
        dialog.setContentText("Please enter the path to the file you want to import data from: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            log.logDebug("Path to File retrieved -PathHandler-");
            return result.get();
        }
        log.logDebug("Could not retrieve Path -PathHandler-");
        return "no path found";
    }

}
