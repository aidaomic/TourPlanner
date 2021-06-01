package Controller.TourLog;

import BusinessLayer.Logging.LoggingHandler;
import Models.EditLogViewModel;
import TourPlanner.Log;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditTourLogWindowController implements Initializable {

    private EditLogViewModel editModel = new EditLogViewModel();
    private LoggingHandler log = new LoggingHandler();

    public TextField tourName, tourDistance, totalTime, weather, transportation, fuelUsed, averageSpeed;
    public Slider rating;
    public RadioButton seasClosYes, seasClosNo, trafJamYes, trafJamNo;
    public Boolean seasClos, trafJam;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourName.textProperty().bindBidirectional(editModel.tourNameProperty());
        tourDistance.textProperty().bindBidirectional(editModel.distanceProperty());
        totalTime.textProperty().bindBidirectional(editModel.totalTimeProperty());
        rating.valueProperty().bindBidirectional(editModel.ratingProperty());
        weather.textProperty().bindBidirectional(editModel.weatherProperty());
        seasClosYes.selectedProperty().bindBidirectional(editModel.seasonalClosYesProperty());
        seasClosNo.selectedProperty().bindBidirectional(editModel.seasonalClosNoProperty());
        transportation.textProperty().bindBidirectional(editModel.transportationProperty());
        trafJamYes.selectedProperty().bindBidirectional(editModel.trafficJamYesProperty());
        trafJamNo.selectedProperty().bindBidirectional(editModel.trafficJamNoProperty());
        fuelUsed.textProperty().bindBidirectional(editModel.fuelUsedProperty());
        averageSpeed.textProperty().bindBidirectional(editModel.averageSpeedProperty());
        log.logInfo("Controller -EditTourLogWindowController- created");
    }

    public void editTourLog(ActionEvent actionEvent) throws IOException {
        if(seasClosYes.isSelected())
            seasClos = true;
        else seasClos = false;
        if(trafJamYes.isSelected())
            trafJam = true;
        else trafJam = false;

        Log tourLog = new Log(tourName.getText(), totalTime.getText(), rating.getValue(), weather.getText(), seasClos,
                transportation.getText(), trafJam, fuelUsed.getText(), averageSpeed.getText());
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        editModel.editTour(stage, tourLog);
        log.logDebug("Editing Tour Log finished -EditTourLogController-");
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        editModel.changeSceneToMain(stage);
        log.logInfo("Main Stage loaded -EditTourLogController-");
    }
}
