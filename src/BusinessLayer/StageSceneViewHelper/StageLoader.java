package BusinessLayer.StageSceneViewHelper;

import BusinessLayer.Handler.Paths.AllroundPathHandler;
import BusinessLayer.Handler.Paths.TourLogPathHandler;
import BusinessLayer.Handler.Paths.TourPathHandler;
import BusinessLayer.Logging.LoggingHandler;
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
    private Image img;
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

    public StageLoader(Stage s, Image i) {
        stage = s;
        this.img = i;
    }

    public StageLoader(Stage s, LogTable l) {
        stage = s;
        logTable = l;
    }

    public void changeStage(String methode) throws IOException {
        AllroundPathHandler ph = new AllroundPathHandler();
        TourLogPathHandler phtl = new TourLogPathHandler();
        TourPathHandler pht = new TourPathHandler();
        Parent root = null;
        if (methode.contains("Log")) {
            root = FXMLLoader.load(getClass().getResource(phtl.viewPath(methode)));
            stage.setTitle(phtl.title);
            stage.setScene(new Scene(root, phtl.x, phtl.y));
        }else if (methode.contains("Tour")) {
            root = FXMLLoader.load(getClass().getResource(pht.viewPath(methode)));
            stage.setTitle(pht.title);
            stage.setScene(new Scene(root, pht.x, pht.y));
        }else {
            root = FXMLLoader.load(getClass().getResource(ph.viewPath(methode)));
            stage.setTitle(ph.title);
            stage.setScene(new Scene(root, ph.x, ph.y));
        }
         //v=breite v1=höhe
        switch (methode){
            case "editTour":
                new SceneDataHelper(root, tour).setDataForTourEdit();
                break;
            case "addLog":
                new SceneDataHelper(root, tour).setDataForAddLog();
                break;
            case "editLog":
                new SceneDataHelper(root, logTable).setDataForEditLog();
                break;
            case "imageWindow":
                new SceneDataHelper(root).setImage(img);
                break;
            case "help":
                break;
        }
        stage.show();
        log.logInfo("Stage "+methode+" loaded -StageLoader-");
    }
}
