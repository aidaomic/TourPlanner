package Models;
import BuissnessLayer.Allerts;
import BuissnessLayer.StageLoader;
import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import java.io.IOException;

public class MainViewModel {
    public final StringProperty input = new SimpleStringProperty("");
    public final StringProperty output = new SimpleStringProperty("");
    public final StringProperty outputTitle = new SimpleStringProperty("Title");
    public final ListProperty tourList = new SimpleListProperty();

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

    public void doEdit(Stage stage, String tourName) throws IOException {
        if (tourName.equals("null"))
            new Allerts().tourToEditIsNull();
        else{
            Database_Tours dbt = new Database_Tours();
            Tour t = dbt.specificTour(tourName);
            new StageLoader(stage, t).changeStageForEdit("EditTour");
        }
    }

    public void addTour(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("AddTour");
    }

    public void deleteTour(Stage stage) throws IOException {
        new StageLoader(stage).changeStage("DeleteTour");
    }

    public void showTour(String tourName) {
        Database_Tours dbt = new Database_Tours();
        Tour t = dbt.specificTour(tourName);
        outputTitle.set("Title: "+t.tourName);
        output.set("Description:\n"+t.tourDescription+"\n\nStart: "+t.tourSart+"\nZiel: "+t.tourEnd);
    }
}