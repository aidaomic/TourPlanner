package TourPlanner;

import javafx.scene.control.TextField;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Tour {

    public String tourName, tourDescription, tourSart, tourEnd, tourDist;
    public BufferedImage tourImage;

    public Tour(String name, String desc, String start, String end,String dist, BufferedImage img) {
        tourName = name;
        tourDescription = desc;
        tourSart = start;
        tourEnd = end;
        tourDist = dist;
        tourImage = img;
    }

    public Tour(String name, String desc, String start, String end) {
        tourName = name;
        tourDescription = desc;
        tourSart = start;
        tourEnd = end;
    }

}
