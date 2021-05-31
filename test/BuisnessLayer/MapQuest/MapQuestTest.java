package BuisnessLayer.MapQuest;

import BusinessLayer.MapQuest.MapQuest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

public class MapQuestTest {

    private MapQuest mq = new MapQuest();

    @Test
    public void newRouteFromGrazToWien_getDirectionsFromMQ(){
        mq.getDirections("Graz", "Wien");
        Assertions.assertEquals(192.24332, mq.distance);
    }

    @Test
    public void newRouteFromSalzburgToGraz_getDirectionsFromMQ(){
        mq.getDirections("Salzburg", "Graz");
        Assertions.assertEquals(281.613616, mq.distance);
    }

    @Test
    public void newTour_getMapImage(){
        BufferedImage map = mq.getDirections("Graz", "Wien");
        Assertions.assertNotNull(map);
    }
}
