package BuisnessLayer.Handler;

import BusinessLayer.Handler.PropertyHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PropertyHandlerTest {

    private PropertyHandler ph = new PropertyHandler();

    @Test
    public void readSpecificTour_getSQLQueryFromFile() throws IOException {
        String sqlProperty = ph.getSqlQuery("specificTour");
        Assertions.assertEquals("select * from public.tours where tourname = ?;", sqlProperty);
    }

    @Test
    public void createNewTour_getMapQuestPropertyGetDirections() throws IOException {
        String mqDirectionsURL = ph.getMapQuest_directions("Wien", "Graz");
        String expected = "http://www.mapquestapi.com/directions/v2/route?key=AnCMu0aBcasIZjPMl75ZbWdIZmRC2u4c&from=Wien&to=Graz";
        Assertions.assertEquals(expected, mqDirectionsURL);
    }

}
