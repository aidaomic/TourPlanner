package Models;
import BuissnessLayer.Allerts;
import BuissnessLayer.ImageHandler;
import BuissnessLayer.StageLoader;
import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class MainViewModel {
    public final StringProperty input = new SimpleStringProperty("");
    public final StringProperty informationOutput = new SimpleStringProperty("");
    public final StringProperty outputTitle = new SimpleStringProperty("Title:");
    public final ObjectProperty imageOutput = new SimpleObjectProperty();

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

    public void searchForTour() {

    }

    public void getHelp() {

    }

    public void doEdit(Stage stage, String tourName) throws IOException {
        if (tourName.equals("null"))
            new Allerts().tourToEditIsNull();
        else{
            Database_Tours dbt = new Database_Tours();
            Tour t = dbt.specificTour(tourName);
            new StageLoader(stage, t).changeStageForEdit("EditTour");
        }
    }

    public void addTour(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("AddTour");
    }

    public void deleteTour(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("DeleteTour");
    }

    public void showTour(String tourName) {
        Database_Tours dbt = new Database_Tours();
        Tour t = dbt.specificTour(tourName);
        outputTitle.set("Title: "+t.tourName);
        informationOutput.set("Description:\n"+t.tourDescription+"\n\nStart: "+t.tourSart+"\nZiel: "+t.tourEnd);
        imageOutput.set(new ImageHandler().resize(t.tourImage,t.tourImage.getWidth()*0.53,t.tourImage.getHeight()*0.3));
    }

    public void zoomPicture(String name) throws IOException {

        Image img = new ImageHandler().getImageFromFS(name);
        new StageLoader().chnageImageStage(name, img);

    }
}