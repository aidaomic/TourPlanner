package BusinessLayer.StageSceneViewHelper;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.Notification.Allerts;
import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;

public class EditTourInspector {

    private Tour old, edit;
    private Database_Tours dbt = new Database_Tours();
    private LoggingHandler log = new LoggingHandler();

    public EditTourInspector (Tour o, Tour e){
        old = o;
        edit = e;
    }

    public int noChanges(){
        if (old.tourName.equals(edit.tourName) && old.tourDescription.equals(edit.tourDescription) &&
                old.tourSart.equals(edit.tourSart) && old.tourEnd.equals(edit.tourEnd)) {
            new Allerts().nothingEdited();
            log.logDebug("No changes found => editing Tour interrupted -EditTourInspector-");
            return 1;
        }
        log.logDebug("Changes found editing Tour -EditTourInspector-");
        return 0;
    }

    public int nameChanged() {
        if(old.tourName.equals(edit.tourName))
            return 0;
        Tour test = dbt.specificTour(edit.tourName);
        if(test.tourName != null){
            new Allerts().duplicatedName();
            log.logError("Duplicating Name, while editing Tour -EditTourInspector-");
            return 1;
        }
        log.logDebug("Tour Name changes detected (OK) -EditTourInspector-");
        return 0;
    }

    public int changedRoute() {
        if(old.tourSart.equals(edit.tourSart) && old.tourEnd.equals(edit.tourEnd))
            return 0;
        log.logDebug("Tour Rote changed -EditTourInspector-");
        return 1;
    }
}
