package BuissnessLayer.StageSceneViewHelper;

import TourPlanner.Tour;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

    public StageLoader() {

    }

    public void changeStage(String methode) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/"+methode+"Window.fxml"));
        stage.setTitle("Tour Planner - Add Tour");
        stage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        stage.show();
    }

    public void changeStageForEdit(String methode) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/"+methode+"Window.fxml"));
        stage.setTitle("Tour Planner - Edit Tour");
        stage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        new SceneDataHelper(root, tour).setDataForTourEdit();
        stage.show();
    }

    public void changeStageForLog(String methode) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/"+methode+"Window.fxml"));
        stage.setTitle("Tour Planner - Add Log");
        stage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        new SceneDataHelper(root, tour).setDataForAddLog();
        stage.show();
    }

    public void chnageImageStage(String name, Image img) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/ImageWindow.fxml"));
        Stage tourStage = new Stage();
        tourStage.setTitle("Tour Planner - " + name.substring(7)+".jpg");
        tourStage.setScene(new Scene(root, 640, 480)); //v=breite v1=höhe
        new SceneDataHelper(root).setImage(img);
        tourStage.show();
    }
}
