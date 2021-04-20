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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel {
    public final StringProperty input = new SimpleStringProperty("");
    public final StringProperty output = new SimpleStringProperty("");
    public final StringProperty outputTitle = new SimpleStringProperty("Title");
    public final ListProperty tourList = new SimpleListProperty();
    public Parent root;

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

    public void doEdit(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/EditTourWindow.fxml"));
        stage.setTitle("Tour Planner - delete Tour");
        stage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        stage.show();
    }

    public void addTour(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/AddTourWindow.fxml"));
        stage.setTitle("Tour Planner - add Tour");
        stage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        stage.show();
    }

    public void deleteTour(Stage stage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../Views/DeleteTourWindow.fxml"));
        stage.setTitle("Tour Planner - delete Tour");
        stage.setScene(new Scene(root, 200, 100)); //v=breite v1=höhe
        stage.show();
    }

    public void showTour(String tourName) {
        Database_Tours dbt = new Database_Tours();
        Tour t = dbt.specificTour(tourName);
        outputTitle.set("Title: "+t.tourName);
        output.set("Description:\n"+t.tourDescription+"\n\nStart: "+t.tourSart+"\nZiel: "+t.tourEnd);
    }
}