package BuissnessLayer;

import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;

public class EditTourInspector {

    private Tour old, edit;
    private Database_Tours dbt = new Database_Tours();

    public EditTourInspector (Tour o, Tour e){
        old = o;
        edit = e;
    }

    public int noChanges(){
        if (old.tourName.equals(edit.tourName) && old.tourDescription.equals(edit.tourDescription) &&
                old.tourSart.equals(edit.tourSart) && old.tourEnd.equals(edit.tourEnd)) {
            new Allerts().nothingEdited();
            return 1;
        }
        return 0;
    }

    public int nameChanged() {
        if(old.tourName.equals(edit.tourName))
            return 0;
        Tour test = dbt.specificTour(edit.tourName);
        if(test.tourName != null){
            new Allerts().duplicatedName();
            return 1;
        }
        return 0;
    }

    public int changedRoute() {
        if(old.tourSart.equals(edit.tourSart) && old.tourEnd.equals(edit.tourEnd))
            return 0;
        return 1;
    }
}
