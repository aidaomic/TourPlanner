package Models;

import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MainViewModel {
    private final StringProperty input = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");
    private final StringProperty outputTitle = new SimpleStringProperty("Title");
    public Parent root;
    Stage secondaryStage = new Stage();

    public StringProperty inputProperty() {
        return input;
    }

    public StringProperty outputProperty() {
        return output;
    }

    public StringProperty outputPropertyTitle() {
        return outputTitle;
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
        secondaryStage.setTitle("Tour Planner - add Tour");
        secondaryStage.setScene(new Scene(root, 500, 350)); //v=breite v1=höhe
        secondaryStage.show();
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