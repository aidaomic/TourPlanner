package TourPlanner;

import BuissnessLayer.Handler.ImageHandler;
import BuissnessLayer.Notification.Allerts;
import BuissnessLayer.StageSceneViewHelper.StageLoader;
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

    //Methoden
    public void deleteTour(String tourToDelete) throws IOException, SQLException {
        if(new Allerts().allertDelete(tourToDelete)==1)
            return;
        new Database_Tours().delete(tourToDelete);
        ObservableList obList = FXCollections.observableList(new Database_Tours().getTourNames());
        tourList.setValue(obList);
    }

    public void copyTour(String tourName) {
        if (tourName.equals("null"))
            new Allerts().tourIsNull();
        else{
            Database_Tours dbt = new Database_Tours();
            Tour tour = dbt.specificTour(tourName);
            dbt.copy(tour);
        }
    }

    public void showTour(String tourName) {
        if (tourName.equals("null"))
            new Allerts().tourIsNull();
        else {
            Database_Tours dbt = new Database_Tours();
            Tour t = dbt.specificTour(tourName);
            outputTitle.set("Title: " + t.tourName);
            informationOutput.set("Description:\n" + t.tourDescription + "\n\nStart: " + t.tourSart + "\nZiel: " + t.tourEnd + "\nEntfermung: " + t.tourDistance);
            imageOutput.set(new ImageHandler().resize(t.tourImage, t.tourImage.getWidth() * 0.53, t.tourImage.getHeight() * 0.3));
        }
    }

    public void zoomPicture(String name) throws IOException {
        Image img = new ImageHandler().getImageFromFS(name);
        new StageLoader().changeImageStage(name, img);
    }

    public void deleteTourLog(Stage stage, String name, String dateAndTime) throws IOException {
        new Database_Logs().delete(name+";"+dateAndTime);
        new StageLoader(stage).changeMainStage();
    }

}