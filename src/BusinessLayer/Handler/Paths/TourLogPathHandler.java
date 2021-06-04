package BusinessLayer.Handler.Paths;

import BusinessLayer.Logging.LoggingHandler;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class TourLogPathHandler implements PathHandler{

    public int x = 0, y = 0;
    public String title = "";
    private LoggingHandler log = new LoggingHandler();

    @Override
    public String inputPath() {
        TextInputDialog dialog = new TextInputDialog("path to file");
        dialog.setTitle("Path Input Dialog");
        dialog.setContentText("Please enter the path to the file you want to import Tour Logs from: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            log.logDebug("Path to File retrieved -TourLogPathHandler-");
            return result.get();
        }
        log.logDebug("Could not retrieve Path -TourLogPathHandler-");
        return "no path found";
    }

    @Override
    public String viewPath(String methode) {
        log.logDebug("Retrieving Path -TourLogPathHandler-");
        switch (methode){
            case "addLog":
                title = "Tour Planner - Add Tour Log";
                x = 490;
                y = 420;
                return "../../Views/TourLog/AddTourLogWindow.fxml";
            case "editLog":
                title = "Tour Planner - Edit Tour Log";
                x = 500;
                y = 450;
                return "../../Views/TourLog/EditTourLogWindow.fxml";
        }
        new LoggingHandler().logError("Problem with Path -TourLogPathHandler-");
        return "";
    }
}
