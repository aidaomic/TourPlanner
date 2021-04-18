package Models;

import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.io.IOException;
import java.util.ArrayList;

public class MainViewModel {
    public final StringProperty input = new SimpleStringProperty("");
    public final StringProperty output = new SimpleStringProperty("");
    public final StringProperty outputTitle = new SimpleStringProperty("Title");
    public final ListProperty tourList = new SimpleListProperty();
    public Parent root;
    public Stage secondaryStage = new Stage();

    public StringProperty inputProperty() {
        return input;
    }

    public StringProperty outputProperty() {
        return output;
    }

    public StringProperty outputPropertyTitle() {
        return outputTitle;
    }

    public ListProperty outputList(){
        return tourList;
    }

    public void searchForTour() {
        this.output.set("You searched for ".concat(this.input.get()).concat("!"));
        this.input.set("");
    }

    public void getHelp() {
        System.out.println("VM: get Help");
        this.output.set("Here should be some helpful stuff");
    }

    public void doEdit() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/EditTourWindow.fxml"));
        secondaryStage.setTitle("Tour Planner - delete Tour");
        secondaryStage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        secondaryStage.show();
    }

    public void addTour() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/AddTourWindow.fxml"));
        secondaryStage.close();
        secondaryStage.setTitle("Tour Planner - add Tour");
        secondaryStage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        secondaryStage.show();
        secondaryStage.setOnCloseRequest(refreshList());
    }

    public EventHandler refreshList(){
        Database_Tours data = new Database_Tours();
        ArrayList list = data.getTourNames();
        ObservableList obList = FXCollections.observableList(list);
        tourList.setValue(obList);
        System.out.println("Stage closing");
        return null;
    }

    public void deleteTour() throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/DeleteTourWindow.fxml"));
        secondaryStage.setTitle("Tour Planner - delete Tour");
        secondaryStage.setScene(new Scene(root, 200, 100)); //v=breite v1=höhe
        secondaryStage.show();
    }

    public void showTour(String tourName) {
        Database_Tours dbt = new Database_Tours();
        Tour t = dbt.specificTour(tourName);
        outputTitle.set("Title: "+t.tourName);
        output.set("Description:\n"+t.tourDescription+"\n\nStart: "+t.tourSart+"\nZiel: "+t.tourEnd);
    }
}