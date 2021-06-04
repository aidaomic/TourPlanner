package BuisnessLayer.Handler;

import BusinessLayer.Handler.Paths.AllroundPathHandler;
import BusinessLayer.Handler.Paths.TourLogPathHandler;
import BusinessLayer.Handler.Paths.TourPathHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathHandlerTest {

    private AllroundPathHandler ph = new AllroundPathHandler();
    private TourPathHandler pht = new TourPathHandler();
    private TourLogPathHandler phtl = new TourLogPathHandler();
    private String methode = "";

    @Test
    public void viewPath_main(){
        methode = "mainWindow";
        Assertions.assertEquals("../../TourPlanner/mainWindow.fxml", ph.viewPath(methode));
        Assertions.assertEquals(500, ph.x);
        Assertions.assertEquals(500, ph.y);
        Assertions.assertEquals("Tour Planner", ph.title);
    }

    @Test
    public void viewPath_image(){
        methode = "imageWindow";
        Assertions.assertEquals("../../Views/ImageWindow.fxml", ph.viewPath(methode));
        Assertions.assertEquals(640, ph.x);
        Assertions.assertEquals(480, ph.y);
        Assertions.assertEquals("Tour Planner - Image", ph.title);
    }

    @Test
    public void viewPath_editlog(){
        methode = "editLog";
        Assertions.assertEquals("../../Views/TourLog/EditTourLogWindow.fxml", phtl.viewPath(methode));
        Assertions.assertEquals(500, phtl.x);
        Assertions.assertEquals(450, phtl.y);
        Assertions.assertEquals("Tour Planner - Edit Tour Log", phtl.title);
    }

    @Test
    public void viewPath_addLog(){
        methode = "addLog";
        Assertions.assertEquals("../../Views/TourLog/AddTourLogWindow.fxml", phtl.viewPath(methode));
        Assertions.assertEquals(490, phtl.x);
        Assertions.assertEquals(420, phtl.y);
        Assertions.assertEquals("Tour Planner - Add Tour Log", phtl.title);
    }

    @Test
    public void viewPath_editTour(){
        methode = "editTour";
        Assertions.assertEquals("../../Views/Tour/EditTourWindow.fxml", pht.viewPath(methode));
        Assertions.assertEquals(500, pht.x);
        Assertions.assertEquals(350, pht.y);
        Assertions.assertEquals("Tour Planner - Edit Tour", pht.title);
    }

    @Test
    public void viewPath_addTour(){
        methode = "addTour";
        Assertions.assertEquals("../../Views/Tour/AddTourWindow.fxml", pht.viewPath(methode));
        Assertions.assertEquals(500, pht.x);
        Assertions.assertEquals(350, pht.y);
        Assertions.assertEquals("Tour Planner - Add Tour", pht.title);
    }

    @Test
    public void viewPath_logLevel(){
        methode = "logLevel";
        Assertions.assertEquals("../../Views/Logging/LoggingLevelSelector.fxml", ph.viewPath(methode));
        Assertions.assertEquals(400, ph.x);
        Assertions.assertEquals(200, ph.y);
        Assertions.assertEquals("Tour Planner - Change Log Level", ph.title);
    }
}
