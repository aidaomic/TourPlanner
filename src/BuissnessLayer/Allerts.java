package BuissnessLayer;

import javafx.scene.control.Alert;

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
}
