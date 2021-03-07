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

    public void getHelp() {
        System.out.println("VM: get Help");
        this.output.set("Here should be some helpful stuff");
        this.input.set("");
    }

    public void doEdit() {
        System.out.println("VM: do Edit");
        this.output.set("You klicked the button for editing");
        this.input.set("");
    }

    public void getOptions() {
        System.out.println("VM: get Options");
        this.output.set("Here should be some options displayed");
        this.input.set("");
    }

    public void getFile() {
        System.out.println("VM: get File");
        this.output.set("You klicked on the getFile-Button");
        this.input.set("");
    }
}