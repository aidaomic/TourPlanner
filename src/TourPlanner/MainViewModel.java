package TourPlanner;

import BusinessLayer.Handler.ImageHandler;
import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.Notification.Allerts;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainViewModel {
    public final StringProperty input = new SimpleStringProperty("");
    public final StringProperty informationOutput = new SimpleStringProperty("");
    public final StringProperty outputTitle = new SimpleStringProperty("Title:");
    public final ObjectProperty imageOutput = new SimpleObjectProperty();
    public final ListProperty tourList = new SimpleListProperty();

    private LoggingHandler log = new LoggingHandler();

    //Methoden
    public void deleteTour(String tourToDelete) throws IOException, SQLException {
        if(new Allerts().allertDelete(tourToDelete)==1){
            log.logDebug("Tour not deleted, because of user interaction -MainViewModel-");
            return;
        }
        new Database_Tours().delete(tourToDelete);
        new Database_Logs().deleteAllLogs(tourToDelete);
        ObservableList obList = FXCollections.observableList(new Database_Tours().getTourNames());
        tourList.setValue(obList);
        log.logDebug("Tour '"+tourToDelete+"' deleted -MainViewModel-");
    }

    public void copyTour(String tourName) {
        if (tourName.equals("null")) {
            log.logDebug("Tour not copied because no Tour was selected -MainViewModel-");
            new Allerts().tourIsNull();
        }else{
            Database_Tours dbt = new Database_Tours();
            Tour tour = dbt.specificTour(tourName);
            dbt.copy(tour);
            log.logDebug("Tour '"+tourName+"' copied -MainViewModel-");
        }
    }

    public void showTour(String tourName) {
        if (tourName.equals("null")) {
            log.logDebug("No Tour shown, because no Tour was selected -MainViewModel-");
            new Allerts().tourIsNull();
        }else {
            Database_Tours dbt = new Database_Tours();
            Tour t = dbt.specificTour(tourName);
            outputTitle.set("Title: " + t.tourName);
            informationOutput.set("Description:\n" + t.tourDescription + "\n\nStart: " + t.tourSart + "\nZiel: " + t.tourEnd + "\nEntfermung: " + t.tourDistance);
            imageOutput.set(new ImageHandler().resize(t.tourImage, t.tourImage.getWidth() * 0.53, t.tourImage.getHeight() * 0.3));
            log.logInfo("Tour '"+tourName+"' displayed -MainViewModel-");
        }
    }

    public void zoomPicture(Stage stage, String name) throws IOException {
        Image img = new ImageHandler().getImageFromFS(name);
        new StageLoader(stage, img).changeStage("imageWindow");
        log.logDebug("Loaded Stage for zoomed in map -MainViewModel-");
    }

    public void deleteTourLog(Stage stage, int id) throws IOException {
        new Database_Logs().delete(String.valueOf(id));
        new StageLoader(stage).changeStage("mainWindow");
        log.logDebug("Tour Log deleted -MainViewModel-");
    }

    //Properties
    public StringProperty inputProperty() {
        return input;
    }

    public StringProperty informationProperty() {
        return informationOutput;
    }

    public StringProperty outputPropertyTitle() {
        return outputTitle;
    }

    public ObjectProperty tourImageProperty(){
        return imageOutput;
    }

    public ListProperty tourListProperty(){
        return tourList;
    }

}