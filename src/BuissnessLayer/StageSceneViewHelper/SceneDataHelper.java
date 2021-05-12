package BuissnessLayer.StageSceneViewHelper;

import TourPlanner.Tour;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SceneDataHelper {

    public Tour tour;
    private Parent root;

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
    }

    public Tour getTour() {
        return tour;
    }

    public void setImage(Image img) {
        ImageView imageView = (ImageView) root.lookup("#image");
        imageView.setImage(img);
    }
}
