package Models;

import BusinessLayer.Handler.DatabaseInputHandler;
import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_EditTours;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;

import java.io.IOException;

public class AddTourViewModel {
    private Database_EditTours dbt = new Database_EditTours();
    private LoggingHandler log = new LoggingHandler();

    private final StringProperty inputStart = new SimpleStringProperty("");
    private final StringProperty inputEnd = new SimpleStringProperty("");
    private final StringProperty inputName = new SimpleStringProperty("");
    private final StringProperty inputDescription = new SimpleStringProperty("");



    //Methods
    public void changeSceneToMain(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("mainWindow");
    }

    public void addTourStage(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("addTour");
        log.logDebug("Loaded Stage for adding new Tour -AddTourViewModel-");
    }

    public void addTour(String tourName, String tourDescription, String tourStart, String tourEnd){
        new DatabaseInputHandler().saveTour(tourName, tourDescription, tourStart, tourEnd);
        log.logDebug("Added Tour '"+tourName+"' -AddTourViewModel-");
    }

    //Properties
    public StringProperty inputPropertyStart() {
        return inputStart;
    }

    public StringProperty inputPropertyEnd() {
        return inputEnd;
    }

    public StringProperty inputPropertyName() {
        return inputName;
    }

    public StringProperty inputPropertyDescription() {
        return inputDescription;
    }
}