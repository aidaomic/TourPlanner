package BusinessLayer.Handler.Paths;

import BusinessLayer.Logging.LoggingHandler;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class AllroundPathHandler implements PathHandler {

    public int x = 0, y = 0;
    public String title = "";
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

    public String viewPath(String methode){
        log.logDebug("Retrieving Path -PathHandler-");
        switch (methode){
            case "mainWindow":
                title = "Tour Planner";
                x = 500;
                y = 500;
                return "../../TourPlanner/mainWindow.fxml";
            case "imageWindow":
                title = "Tour Planner - Image";
                x = 640;
                y = 480;
                return "../../Views/ImageWindow.fxml";
            case "logLevel":
                title = "Tour Planner - Change Log Level";
                x = 400;
                y = 200;
                return "../../Views/Logging/LoggingLevelSelector.fxml";
            case "help":
                title = "Tour Planner - Help";
                x = 400;
                y = 400;
                return "../../Views/HelpWindow.fxml";
        }

        new LoggingHandler().logError("Problem with Path -PathHandler-");
        return "";
    }

}
