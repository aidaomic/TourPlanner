package Models;

import BuissnessLayer.Notification.Allerts;
import BuissnessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EditLogViewModel {

    public void changeSceneToMain(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../TourPlanner/mainWindow.fxml"));
        stage.setTitle("Tour Planner");
        stage.setScene(new Scene(root, 500, 500)); //v=breite v1=höhe
        stage.show();
    }

    public void editTourLogStage(Stage stage, String tourName) throws IOException {
        if(tourName.equals("null"))
            new Allerts().tourIsNull();
        else {
            Database_Tours dbt = new Database_Tours();
            Tour t = dbt.specificTour(tourName);
            new StageLoader(stage, t).changeStageForLog("TourLog/EditTourLog");
        }
    }

}
