package BusinessLayer.StageSceneViewHelper;

import BusinessLayer.Logging.LoggingHandler;
import TourPlanner.Log;
import TourPlanner.LogTable;
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
    private LogTable logTable;
    private LoggingHandler log = new LoggingHandler();

    public StageLoader(Stage s) {
        stage = s;
    }

    public StageLoader(Stage s, Tour tn) {
        stage = s;
        tour = tn;
    }

    public StageLoader(Stage s, LogTable l) {
        stage = s;
        logTable = l;
    }

    public StageLoader() {

    }

    public void changeStage(String methode) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/"+methode+"Window.fxml"));
        stage.setTitle("Tour Planner - Add Tour");
        stage.setScene(new Scene(root, 500, 500)); //v=breite v1=höhe
        stage.show();
        log.logInfo("Stage AddTour loaded -StageLoader-");
    }

    public void changeStageForEdit(String methode) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/"+methode+"Window.fxml"));
        stage.setTitle("Tour Planner - Edit Tour");
        stage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        new SceneDataHelper(root, tour).setDataForTourEdit();
        stage.show();
        log.logInfo("Stage EditTour loaded -StageLoader-");
    }

    public void changeStageForLog(String methode) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/"+methode+"Window.fxml"));
        stage.setTitle("Tour Planner - Tour Log");
        stage.setScene(new Scene(root, 490, 450)); //v=breite v1=höhe
        if(methode.equals("TourLog/AddTourLog")) {
            new SceneDataHelper(root, tour).setDataForAddLog();
            log.logInfo("Stage AddLog loaded -StageLoader-");
        }else if(methode.equals("TourLog/EditTourLog")){
            new SceneDataHelper(root, logTable).setDataForEditLog();
            log.logInfo("Stage EditLog loaded -StageLoader-");
        }
        stage.show();
    }

    public void changeMainStage() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../../TourPlanner/mainWindow.fxml"));
        stage.setTitle("Tour Planner - Tour Log");
        stage.setScene(new Scene(root, 500, 500)); //v=breite v1=höhe
        stage.show();
        log.logInfo("Main stage loaded -StageLoader-");
    }

    public void changeImageStage(String name, Image img) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Views/ImageWindow.fxml"));
        Stage tourStage = new Stage();
        tourStage.setTitle("Tour Planner - " + name.substring(7)+".jpg");
        tourStage.setScene(new Scene(root, 640, 480)); //v=breite v1=höhe
        new SceneDataHelper(root).setImage(img);
        tourStage.show();
        log.logInfo("Stage Image loaded -StageLoader-");
    }
}
