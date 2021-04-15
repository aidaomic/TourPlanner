package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class TourViewModel {

    private final StringProperty inputStart = new SimpleStringProperty("");
    private final StringProperty inputEnd = new SimpleStringProperty("");
    private final StringProperty inputName = new SimpleStringProperty("");
    private final StringProperty inputDescription = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");

    public StringProperty inputPropertyStart() {
        return inputStart;
    }

    public StringProperty inputPropertyEnd() {
        return inputEnd;
    }

    public StringProperty inputPropertyName() {
        return inputName;
    }

    public StringProperty inputPropertyDescription() {
        return inputDescription;
    }

    public StringProperty outputProperty() {
        return output;
    }
}
