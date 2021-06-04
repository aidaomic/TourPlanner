package BuisnessLayer.Handler;

import BusinessLayer.Handler.Properties.PropertyHandlerDatabase;
import BusinessLayer.Handler.Properties.PropertyHandlerMQ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PropertyHandlerTest {

    private PropertyHandlerDatabase phd = new PropertyHandlerDatabase();
    private PropertyHandlerMQ phmq = new PropertyHandlerMQ();


    @Test
    public void readSpecificTour_getSQLQueryFromFile() throws IOException {
        String sqlProperty = phd.getSqlQuery("specificTour");
        Assertions.assertEquals("select * from public.tours where tourname = ?;", sqlProperty);
    }

    @Test
    public void createNewTour_getMapQuestPropertyGetDirections() throws IOException {
        String mqDirectionsURL = phmq.getMapQuest_directions("Wien", "Graz");
        String expected = "http://www.mapquestapi.com/directions/v2/route?key=AnCMu0aBcasIZjPMl75ZbWdIZmRC2u4c&from=Wien&to=Graz";
        Assertions.assertEquals(expected, mqDirectionsURL);
    }

}
