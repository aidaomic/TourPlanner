package Models;

import BusinessLayer.Handler.ImageHandler;
import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.MapQuest.MapQuest;
import BusinessLayer.Notification.Allerts;
import BusinessLayer.StageSceneViewHelper.EditTourInspector;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_EditTours;
import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class EditTourViewModel {

    private Database_EditTours dbt = new Database_EditTours();
    private ArrayList list = new ArrayList();
    private BufferedImage map;
    public MapQuest mq = new MapQuest();
    private LoggingHandler log = new LoggingHandler();

    public void changeSceneToMain(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../TourPlanner/mainWindow.fxml"));
        stage.setTitle("Tour Planner");
        stage.setScene(new Scene(root, 500, 500)); //v=breite v1=höhe
        stage.show();
        log.logDebug("Main View loaded -EditTourViewModel-");
    }

    public void editTourStage(Stage stage, String tourName) throws IOException {
        if (tourName.equals("null")) {
            new Allerts().tourIsNull();
            log.logDebug("No Tour edited, because no Tour was selected -EditTourViewModel-");
        }else{
            Database_Tours dbt = new Database_Tours();
            Tour t = dbt.specificTour(tourName);
            new StageLoader(stage, t).changeStageForEdit("Tour/EditTour");
            log.logDebug("Stage for editing Tour loaded -EditTourViewModel-");
        }
    }

    public void editTour(Tour tour, Tour tourEdited, Stage stage) throws IOException {
        EditTourInspector inspector = new EditTourInspector(tour, tourEdited);

        if(inspector.noChanges() == 1) {
            log.logDebug("Editing Tour did not make any changes -EditTourViewModel-");
            return;
        }else if(inspector.nameChanged() == 1) {
            log.logDebug("Name changed while editing Tour -EditTourViewModel-");
            return;
        }else if(inspector.changedRoute() == 1){
            list.clear();
            list.add(tourEdited.tourName);
            list.add(tourEdited.tourDescription);
            list.add(tourEdited.tourSart);
            list.add(tourEdited.tourEnd);
            map = mq.getDirections(tourEdited.tourSart, tourEdited.tourEnd);
            list.add(mq.distance);
            list.add(map);
            list.add(tour.tourName);
            dbt.editNewRoute(list);
            new ImageHandler().updateImage((String) list.get(0), (BufferedImage)list.get(5));
            changeSceneToMain(stage);
            log.logDebug("Route changed during editing -EditTourViewModel-");
            return;
        }
        list.clear();
        list.clear();
        list.add(tourEdited.tourName);
        list.add(tourEdited.tourDescription);
        list.add(tourEdited.tourSart);
        list.add(tourEdited.tourEnd);
        list.add(tour.tourName);
        dbt.edit(list);
        changeSceneToMain(stage);
        log.logDebug("Editing Tour '"+tour.tourName+"' finished");
    }
}
