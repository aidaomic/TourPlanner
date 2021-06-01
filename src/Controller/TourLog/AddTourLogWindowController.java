package Controller.TourLog;

import BusinessLayer.Logging.LoggingHandler;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Models.AddLogViewModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTourLogWindowController implements Initializable {

    private AddLogViewModel addModel = new AddLogViewModel();
    private LoggingHandler log = new LoggingHandler();

    public TextField tourName, tourDistance, totalTime, weather, transportation, fuelUsed, averageSpeed;
    public Slider rating;
    public RadioButton seasClosYes, seasClosNo, trafJamYes, trafJamNo;
    public Boolean seasClos, trafJam;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tourName.textProperty().bindBidirectional(addModel.tourNameProperty());
        tourDistance.textProperty().bindBidirectional(addModel.distanceProperty());
        totalTime.textProperty().bindBidirectional(addModel.totalTimeProperty());
        rating.valueProperty().bindBidirectional(addModel.ratingProperty());
        weather.textProperty().bindBidirectional(addModel.weatherProperty());
        seasClosYes.selectedProperty().bindBidirectional(addModel.seasonalClosYesProperty());
        seasClosNo.selectedProperty().bindBidirectional(addModel.seasonalClosNoProperty());
        transportation.textProperty().bindBidirectional(addModel.transportationProperty());
        trafJamYes.selectedProperty().bindBidirectional(addModel.trafficJamYesProperty());
        trafJamNo.selectedProperty().bindBidirectional(addModel.trafficJamNoProperty());
        fuelUsed.textProperty().bindBidirectional(addModel.fuelUsedProperty());
        averageSpeed.textProperty().bindBidirectional(addModel.averageSpeedProperty());
        log.logInfo("Controller -AddTourLogWindowController- created");
    }

    public void createTourLog(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        if (seasClosYes.isSelected())
            seasClos = true;
        else seasClos = false;
        if (trafJamYes.isSelected())
            trafJam = true;
        else trafJam = false;

        addModel.addTourLog(stage, tourName.getText(), totalTime.getText(), weather.getText(), transportation.getText(), seasClos, trafJam, Double.valueOf(tourDistance.getText()), rating.getValue(), Double.valueOf(fuelUsed.getText()), Double.valueOf(averageSpeed.getText()));
        log.logDebug("Creating Tour log finished -AddTourLogWindowController-");
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        addModel.changeSceneToMain(stage);
        log.logDebug("Main Stage loaded -AddTourLogWindowController-");
    }
}
