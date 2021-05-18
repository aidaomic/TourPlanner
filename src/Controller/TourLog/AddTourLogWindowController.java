package Controller.TourLog;

import Models.AddTourViewModel;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTourLogWindowController {

    private AddTourViewModel addModel = new AddTourViewModel();

    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void createTourLog(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        //addModel.addTour();
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        addModel.changeSceneToMain(stage);
    }
}
