package Controller.Logging;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import Models.LoggingLevelViewModel;
import Models.MenuBarViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggingLevelSelectorController implements Initializable {

    private MenuBarViewModel menuModel = new MenuBarViewModel();
    private LoggingLevelViewModel logModel = new LoggingLevelViewModel();
    private LoggingHandler log = new LoggingHandler();

    @FXML
    private RadioButton all, debug, info, error;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        all.selectedProperty().bindBidirectional(logModel.allProperty());
        debug.selectedProperty().bindBidirectional(logModel.debugProperty());
        info.selectedProperty().bindBidirectional(logModel.infoProperty());
        error.selectedProperty().bindBidirectional(logModel.errorProperty());
        log.logInfo("Controller -LoggingLevelSelectorController- created");
    }

    public void changeSceneToMain(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../TourPlanner/mainWindow.fxml"));
        stage.setTitle("Tour Planner");
        stage.setScene(new Scene(root, 500, 500)); //v=breite v1=höhe
        stage.show();
        log.logDebug("Main View loaded -AddTourViewModel-");
    }

    public void setLogLevel(ActionEvent actionEvent) throws IOException {
        String level = "";
        if (all.isSelected())
            level = "TRUCE";
        else if (debug.isSelected())
            level = "DEBUG";
        else if (info.isSelected())
            level = "INFO";
        else if(error.isSelected())
            level = "ERROR";
        logModel.changeLogLevel(level);
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        changeSceneToMain(stage);
        log.logDebug("changed Log level -LoggingLevelSelectorController-");
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        menuModel.changeSceneToMain(stage);
        log.logDebug("Main Stage loaded -LoggingLevelSelectorController-");
    }
}
