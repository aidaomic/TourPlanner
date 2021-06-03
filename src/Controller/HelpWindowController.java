package Controller;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpWindowController {
    public void exit(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        new StageLoader(stage).changeStage("mainWindow");
        new LoggingHandler().logDebug("Exiting HelpWindow -HelpWindowController-");
    }
}
