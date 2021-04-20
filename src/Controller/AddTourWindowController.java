package Controller;

import Models.TourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTourWindowController implements Initializable {

    public TourViewModel tourModel = new TourViewModel();

    // add fx:id and use intelliJ to create field in controller
    public TextField tourStart, tourEnd, tourName, tourDescription;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourStart.textProperty().bindBidirectional(tourModel.inputPropertyStart());
        tourEnd.textProperty().bindBidirectional(tourModel.inputPropertyEnd());
        tourName.textProperty().bindBidirectional(tourModel.inputPropertyName());
        tourDescription.textProperty().bindBidirectional(tourModel.inputPropertyDescription());
    }

    public void createTour(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        tourModel.addTour(tourName.getText(), tourDescription.getText(), tourStart.getText(), tourEnd.getText());
        tourModel.changeSceneToMain(stage);
    }

}