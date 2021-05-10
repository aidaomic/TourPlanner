package BuissnessLayer;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class PathHandler {

    public String inputPath(){
        TextInputDialog dialog = new TextInputDialog("path to file");
        dialog.setTitle("Path Input Dialog");
        dialog.setContentText("Please enter the path to the file you want to import data from: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
            return result.get();
        return "no path found";
    }

}
