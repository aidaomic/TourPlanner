package TourPlanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainViewModel {
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

    public void calculateOutputString() {
        System.out.println("VM: calculate Output");
        this.output.set("You searched for ".concat(this.input.get()).concat("!"));
        this.input.set("");
    }
}