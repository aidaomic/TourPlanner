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

    // add fx:id and use intelliJ to create field in controller
    public TextField tourStart, tourEnd, tourName, tourDescription;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourStart.textProperty().bindBidirectional(tourModel.inputPropertyStart());
        tourEnd.textProperty().bindBidirectional(tourModel.inputPropertyEnd());
        tourName.textProperty().bindBidirectional(tourModel.inputPropertyName());
        tourDescription.textProperty().bindBidirectional(tourModel.inputPropertyDescription());
    }

    public void createTour(ActionEvent actionEvent) {
        tourModel.addTour(tourName.getText(), tourDescription.getText(), tourStart.getText(), tourEnd.getText());
    }

    public void deleteTour(ActionEvent actionEvent) {
        //Datenbank ansprechen
    }

    public void editTour(ActionEvent actionEvent) {
        //Datenbank ansprechen
    }
}