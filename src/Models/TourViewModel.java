package Models;

import BuissnessLayer.EditTourInspector;
import BuissnessLayer.MapQuest;
import DataAccessLayer.Database_EditTours;
import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class TourViewModel {
    private Database_EditTours dbt = new Database_EditTours();
    ArrayList list = new ArrayList();

    private final StringProperty inputStart = new SimpleStringProperty("");
    private final StringProperty inputEnd = new SimpleStringProperty("");
    private final StringProperty inputName = new SimpleStringProperty("");
    private final StringProperty inputDescription = new SimpleStringProperty("");

    MapQuest mq = new MapQuest();

    public StringProperty inputPropertyStart() {
        return inputStart;
    }

    public StringProperty inputPropertyEnd() {
        return inputEnd;
    }

    public StringProperty inputPropertyName() {
        return inputName;
    }

    public StringProperty inputPropertyDescription() {
        return inputDescription;
    }


    public void changeSceneToMain(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../TourPlanner/mainWindow.fxml"));
        stage.setTitle("Tour Planner");
        stage.setScene(new Scene(root, 500, 500)); //v=breite v1=höhe
        stage.show();
    }

    public void addTour(String tourName, String tourDescription, String tourStart, String tourEnd){
        ArrayList list = new ArrayList();
        list.add(tourName);
        list.add(tourDescription);
        list.add(tourStart);
        list.add(tourEnd);
        list.add(mq.getDirections(tourStart, tourEnd));
        new Database_Tours().save(list);
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
            list.add(mq.getDirections(tourEdited.tourSart, tourEdited.tourEnd));
            list.add(tour.tourName);
            dbt.editNewRoute(list);
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
