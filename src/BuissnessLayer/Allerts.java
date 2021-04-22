package BuissnessLayer;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;

public class Allerts {

    private Alert warning = new Alert(Alert.AlertType.WARNING);

    public void tourToEditIsNull(){
        warning.setTitle("Warning");
        //alert.setHeaderText("Look, a Warning Dialog");
        warning.setContentText("Please select a Tour from the List first!");
        warning.showAndWait();
    }

    public void nothingEdited(){
        warning.setTitle("Warning");
        warning.setContentText("There have been no changes to the Tour!");
        warning.showAndWait();
    }

    public void duplicatedName() {
        warning.setTitle("Warning");
        warning.setContentText("This Name has already been taken!");
        warning.showAndWait();
    }

    public int allertDelete(String tourToDelete) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Please check if you want to delete the Tour");
        alert.setContentText(tourToDelete);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return 0;
        }else
            return 1;

    }
}
