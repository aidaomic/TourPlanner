package BusinessLayer.Handler.Paths;

import BusinessLayer.Logging.LoggingHandler;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class TourPathHandler implements PathHandler{
    public int x = 0, y = 0;
    public String title = "";
    private LoggingHandler log = new LoggingHandler();

    @Override
    public String inputPath() {
        TextInputDialog dialog = new TextInputDialog("path to file");
        dialog.setTitle("Path Input Dialog");
        dialog.setContentText("Please enter the path to the file you want to import Tours from: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            log.logDebug("Path to File retrieved -TourPathHandler-");
            return result.get();
        }
        log.logDebug("Could not retrieve Path -TourPathHandler-");
        return "no path found";
    }

    @Override
    public String viewPath(String methode) {
        log.logDebug("Retrieving Path -TourPathHandler-");
        switch (methode){
            case "addTour":
                title = "Tour Planner - Add Tour";
                x = 500;
                y = 350;
                return "../../Views/Tour/AddTourWindow.fxml";
            case "editTour":
                title = "Tour Planner - Edit Tour";
                x = 500;
                y = 350;
                return "../../Views/Tour/EditTourWindow.fxml";
        }
        new LoggingHandler().logError("Problem with Path -TourPathHandler-");
        return "";
    }
}
