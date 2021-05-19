package Controller.TourLog;

import Models.EditLogViewModel;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class EditTourLogWindowController {

    private EditLogViewModel editModel = new EditLogViewModel();

    public void editTourLog(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        editModel.changeSceneToMain(stage);
    }
}
