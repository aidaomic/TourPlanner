package Controller.Tour;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.MapQuest.MapQuest;
import Models.EditTourViewModel;
import Models.AddTourViewModel;
import TourPlanner.Tour;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditTourWindowController implements Initializable {

    private AddTourViewModel tourModel = new AddTourViewModel();
    private EditTourViewModel editTourModel = new EditTourViewModel();
    private MapQuest mq = new MapQuest();
    private LoggingHandler log = new LoggingHandler();

    // add fx:id and use intelliJ to create field in controller
    public TextField tourEditStart, tourEditEnd, tourEditName, tourEditDescription;
    public TextField editedStart, editedEnd, editedName, editedDescription;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        editedStart.textProperty().bindBidirectional(tourModel.inputPropertyStart());
        editedEnd.textProperty().bindBidirectional(tourModel.inputPropertyEnd());
        editedName.textProperty().bindBidirectional(tourModel.inputPropertyName());
        editedDescription.textProperty().bindBidirectional(tourModel.inputPropertyDescription());
        log.logInfo("Controller -EditTourWindowController- created");
    }

    public void editTour(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        editTourModel.editTour((new Tour(tourEditName.getText(), tourEditDescription.getText(), tourEditStart.getText(), tourEditEnd.getText())),(new Tour(editedName.getText(), editedDescription.getText(), editedStart.getText(), editedEnd.getText())), stage);
        log.logDebug("Editing Tour finished -EditTourWindowController-");
    }

    public void exit(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        editTourModel.changeSceneToMain(stage);
        log.logDebug("Exiting Edit Tour -EditTourWindowController-");
    }
}
