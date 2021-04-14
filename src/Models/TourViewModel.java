package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class TourViewModel {

    private final StringProperty input = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");

    public StringProperty inputProperty() {
        System.out.println("VM: get input prop");
        return input;
    }

    public StringProperty outputProperty() {
        System.out.println("VM: get output prop");
        return output;
    }
}
