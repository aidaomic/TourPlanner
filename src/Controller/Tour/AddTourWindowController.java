package Controller.Tour;

import Models.AddTourViewModel;
import TourPlanner.MainViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTourWindowController implements Initializable {

    private AddTourViewModel addModel = new AddTourViewModel();

    // add fx:id and use intelliJ to create field in controller
    public TextField tourStart, tourEnd, tourName, tourDescription;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourStart.textProperty().bindBidirectional(addModel.inputPropertyStart());
        tourEnd.textProperty().bindBidirectional(addModel.inputPropertyEnd());
        tourName.textProperty().bindBidirectional(addModel.inputPropertyName());
        tourDescription.textProperty().bindBidirectional(addModel.inputPropertyDescription());
    }

    public void createTour(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        addModel.addTour(tourName.getText(), tourDescription.getText(), tourStart.getText(), tourEnd.getText());
        addModel.changeSceneToMain(stage);
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        addModel.changeSceneToMain(stage);
    }
}