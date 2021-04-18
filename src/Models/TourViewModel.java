package Models;

import BuissnessLayer.MapQuest;
import DataAccessLayer.Database_Tours;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;


public class TourViewModel {

    private final StringProperty inputStart = new SimpleStringProperty("");
    private final StringProperty inputEnd = new SimpleStringProperty("");
    private final StringProperty inputName = new SimpleStringProperty("");
    private final StringProperty inputDescription = new SimpleStringProperty("");
    private final StringProperty output = new SimpleStringProperty("");

    MapQuest mq = new MapQuest();

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

    public void addTour(String tourName, String tourDescription, String tourStart, String tourEnd) {
        ArrayList list = new ArrayList();
        list.add(tourName);
        list.add(tourDescription);
        list.add(tourStart);
        list.add(tourEnd);
        list.add(mq.getDirections(tourStart, tourEnd));
        new Database_Tours().save(list);
    }
}
