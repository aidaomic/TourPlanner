package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewModel {
    private final StringProperty input = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");
    public Parent root;
    Stage secondaryStage = new Stage();

    public StringProperty inputProperty() {
        System.out.println("VM: get input prop");
        return input;
    }

    public StringProperty outputProperty() {
        System.out.println("VM: get output prop");
        return output;
    }

    public void searchForTour() {
        System.out.println("VM: Search for Tour");
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
}