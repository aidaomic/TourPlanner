package TourPlanner;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Tour {

    public String tourName, tourDescription, tourSart, tourEnd;
    public BufferedImage tourImage;

    public Tour(String name, String desc, String start, String end, BufferedImage img) {
        tourName = name;
        tourDescription = desc;
        tourSart = start;
        tourEnd = end;
        tourImage = img;
    }
}
