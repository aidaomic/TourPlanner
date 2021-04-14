package Controller;

import BuissnessLayer.MapQuest;
import Models.TourViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TourWindowController implements Initializable {

    public TourViewModel tourModel = new TourViewModel();
    MapQuest mq = new MapQuest();

    // add fx:id and use intelliJ to create field in controller
    public TextField tourStart, tourEnd, tourName;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourStart.textProperty().bindBidirectional(tourModel.inputProperty());
        tourName.textProperty().bindBidirectional(tourModel.inputProperty());
        tourEnd.textProperty().bindBidirectional(tourModel.inputProperty());
    }

    public void createTour(ActionEvent actionEvent) {
        mq.getDirections(tourStart.getText(), tourEnd.getText());
    }

    public void deleteTour(ActionEvent actionEvent) {
        //Datenbank ansprechen
    }

    public void editTour(ActionEvent actionEvent) {
        //Datenbank ansprechen
    }
}