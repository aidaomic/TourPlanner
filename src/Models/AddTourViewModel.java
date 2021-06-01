package Models;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.MapQuest.MapQuest;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_EditTours;
import DataAccessLayer.Database_Tours;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class AddTourViewModel {
    private Database_EditTours dbt = new Database_EditTours();
    private BufferedImage map;
    private LoggingHandler log = new LoggingHandler();

    private final StringProperty inputStart = new SimpleStringProperty("");
    private final StringProperty inputEnd = new SimpleStringProperty("");
    private final StringProperty inputName = new SimpleStringProperty("");
    private final StringProperty inputDescription = new SimpleStringProperty("");

    MapQuest mq = new MapQuest();

    //Methods
    public void changeSceneToMain(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../TourPlanner/mainWindow.fxml"));
        stage.setTitle("Tour Planner");
        stage.setScene(new Scene(root, 500, 500)); //v=breite v1=höhe
        stage.show();
        log.logDebug("Main View loaded -AddTourViewModel-");
    }

    public void addTourStage(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("Tour/AddTour");
        log.logDebug("Loaded Stage for adding new Tour -AddTourViewModel-");
    }

    public void addTour(String tourName, String tourDescription, String tourStart, String tourEnd){
        ArrayList list = new ArrayList();
        list.add(tourName);
        list.add(tourDescription);
        list.add(tourStart);
        list.add(tourEnd);
        map = mq.getDirections(tourStart, tourEnd);
        list.add(mq.distance);
        list.add(map);
        new Database_Tours().save(list);
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