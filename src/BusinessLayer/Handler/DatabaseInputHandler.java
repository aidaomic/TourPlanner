package BusinessLayer.Handler;

import BusinessLayer.Handler.ImageHandler;
import BusinessLayer.MapQuest.MapQuest;
import BusinessLayer.StageSceneViewHelper.StageLoader;
import DataAccessLayer.Database_EditTours;
import DataAccessLayer.Database_Tours;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class DatabaseInputHandler {
    ArrayList list = new ArrayList();
    BufferedImage map;
    MapQuest mq = new MapQuest();

    public void saveTour(String tourName, String tourDescription, String tourStart, String tourEnd){
        list.clear();
        list.add(tourName);
        list.add(tourDescription);
        list.add(tourStart);
        list.add(tourEnd);
        map = mq.getDirections(tourStart, tourEnd);
        if (map == null)
            return;
        list.add(mq.distance);
        list.add(map);
        new Database_Tours().save(list);
    }

    public void editTour(String tourName, String tourDescription, String tourSart, String tourEnd, String tourNameOld) throws IOException {
        list.clear();
        list.add(tourName);
        list.add(tourDescription);
        list.add(tourSart);
        list.add(tourEnd);
        map = mq.getDirections(tourSart, tourEnd);
        if(map == null)
            return;
        list.add(mq.distance);
        list.add(map);
        list.add(tourNameOld);
        new Database_EditTours().editNewRoute(list);
        new ImageHandler().updateImage((String) list.get(0), (BufferedImage)list.get(5));
    }
}
