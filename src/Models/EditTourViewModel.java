package Models;

import BuissnessLayer.Handler.ImageHandler;
import BuissnessLayer.MapQuest.MapQuest;
import BuissnessLayer.Notification.Allerts;
import BuissnessLayer.StageSceneViewHelper.EditTourInspector;
import BuissnessLayer.StageSceneViewHelper.StageLoader;
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

    public void changeSceneToMain(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../TourPlanner/mainWindow.fxml"));
        stage.setTitle("Tour Planner");
        stage.setScene(new Scene(root, 500, 500)); //v=breite v1=höhe
        stage.show();
    }

    public void editTourStage(Stage stage, String tourName) throws IOException {
        if (tourName.equals("null"))
            new Allerts().tourIsNull();
        else{
            Database_Tours dbt = new Database_Tours();
            Tour t = dbt.specificTour(tourName);
            new StageLoader(stage, t).changeStageForEdit("Tour/EditTour");
        }
    }

    public void editTour(Tour tour, Tour tourEdited, Stage stage) throws IOException {
        EditTourInspector inspector = new EditTourInspector(tour, tourEdited);

        if(inspector.noChanges() == 1)
            return;
        else if(inspector.nameChanged() == 1)
            return;
        else if(inspector.changedRoute() == 1){
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
    }
}
