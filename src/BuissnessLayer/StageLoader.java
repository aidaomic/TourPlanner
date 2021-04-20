package BuissnessLayer;

import TourPlanner.Tour;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class StageLoader {

    private Stage stage;
    private Tour tour;

    public StageLoader(Stage s) {
        stage = s;
    }

    public StageLoader(Stage s, Tour tn) {
        stage = s;
        tour = tn;
    }

    public void changeStage(String methode) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/"+methode+"Window.fxml"));
        stage.setTitle("Tour Planner - " + methode);
        stage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        stage.show();
    }

    public void changeStageForEdit(String methode) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/"+methode+"Window.fxml"));
        stage.setTitle("Tour Planner - " + methode);
        stage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        new SceneDataHelper(root, tour).setDataForTourEdit();
        stage.show();
    }
}
