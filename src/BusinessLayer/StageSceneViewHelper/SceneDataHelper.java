package BusinessLayer.StageSceneViewHelper;

import BusinessLayer.Logging.LoggingHandler;
import TourPlanner.LogTable;
import TourPlanner.Tour;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SceneDataHelper {

    public Tour tour;
    public LogTable logTable;
    private Parent root;
    private LoggingHandler log = new LoggingHandler();

    public SceneDataHelper(Parent r, LogTable l) {
        root = r;
        logTable = l;
    }

    public SceneDataHelper(Parent r, Tour t) {
        root = r;
        tour = t;
    }

    public SceneDataHelper(Parent r) {
        root = r;
    }

    public void setDataForTourEdit(){
        TextField txtN = (TextField) root.lookup("#tourEditName");
        txtN.setText(String.valueOf(tour.tourName));
        txtN.setDisable(true);
        TextField txtD = (TextField) root.lookup("#tourEditDescription");
        txtD.setText(String.valueOf(tour.tourDescription));
        txtD.setDisable(true);
        TextField txtS = (TextField) root.lookup("#tourEditStart");
        txtS.setText(String.valueOf(tour.tourSart));
        txtS.setDisable(true);
        TextField txtE = (TextField) root.lookup("#tourEditEnd");
        txtE.setText(String.valueOf(tour.tourEnd));
        txtE.setDisable(true);

        TextField txtNE = (TextField) root.lookup("#editedName");
        txtNE.setText(String.valueOf(tour.tourName));
        TextField txtDE = (TextField) root.lookup("#editedDescription");
        txtDE.setText(String.valueOf(tour.tourDescription));
        TextField txtSE = (TextField) root.lookup("#editedStart");
        txtSE.setText(String.valueOf(tour.tourSart));
        TextField txtEE = (TextField) root.lookup("#editedEnd");
        txtEE.setText(String.valueOf(tour.tourEnd));

        log.logDebug("Data set for editing Tour -SceneDataHelper-");
    }

    public void setImage(Image img) {
        ImageView imageView = (ImageView) root.lookup("#image");
        imageView.setImage(img);
        log.logDebug("Image set -SceneDataHelper-");
    }

    public void setDataForAddLog() {
        TextField txtN = (TextField) root.lookup("#tourName");
        txtN.setText(String.valueOf(tour.tourName));
        txtN.setDisable(true);
        TextField txtD = (TextField) root.lookup("#tourDistance");
        txtD.setText(String.valueOf(tour.tourDistance));
        txtD.setDisable(true);
        log.logDebug("Data set for adding Log -SceneDataHelper-");
    }

    public void setDataForEditLog() {
        TextField txtId = (TextField) root.lookup("#logId");
        txtId.setText(String.valueOf(logTable.logId));
        txtId.setDisable(true);
        TextField txtN = (TextField) root.lookup("#tourName");
        txtN.setText(String.valueOf(logTable.tourName));
        txtN.setDisable(true);
        TextField txtD = (TextField) root.lookup("#tourDistance");
        txtD.setText(String.valueOf(logTable.distance));
        txtD.setDisable(true);
        TextField totalTime = (TextField) root.lookup("#totalTime");
        totalTime.setText(String.valueOf(logTable.totalTime));
        Slider rating = (Slider) root.lookup("#rating");
        rating.setValue(logTable.rating);
        TextField weather = (TextField) root.lookup("#weather");
        weather.setText(logTable.weather);
        if(logTable.seasClos.equals("t")){
            RadioButton seas = (RadioButton) root.lookup("#seasClosYes");
            seas.setSelected(true);
        } else{
            RadioButton seas = (RadioButton) root.lookup("#seasClosNo");
            seas.setSelected(true);
        }
        TextField transport = (TextField) root.lookup("#transportation");
        transport.setText(logTable.transportation);
        if(logTable.traffic.equals("t")){
            RadioButton traf = (RadioButton) root.lookup("#trafJamYes");
            traf.setSelected(true);
        } else{
            RadioButton traf = (RadioButton) root.lookup("#trafJamNo");
            traf.setSelected(true);
        }
        TextField fuel = (TextField) root.lookup("#fuelUsed");
        fuel.setText(String.valueOf(logTable.fuelUsed));
        TextField speed = (TextField) root.lookup("#averageSpeed");
        speed.setText(String.valueOf(logTable.speed));
        log.logDebug("Data set for editing Tour Log -SceneDataHelper-");
    }
}
