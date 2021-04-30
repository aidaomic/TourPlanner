package TourPlanner;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Tour {

    public String tourName, tourDescription, tourSart, tourEnd, tourDistance;
    public Image map;
    public BufferedImage tourImage;

    public Tour(String name, String desc, String start, String end,String dist, BufferedImage img) {
        tourName = name;
        tourDescription = desc;
        tourSart = start;
        tourEnd = end;
        tourDistance = dist;
        tourImage = img;
    }

    public Tour(String name, String desc, String start, String end) {
        tourName = name;
        tourDescription = desc;
        tourSart = start;
        tourEnd = end;
    }

    public Tour(String name, String desc, String start, String end, String dist, Image image) {
        tourName = name;
        tourDescription = desc;
        tourSart = start;
        tourEnd = end;
        tourDistance = dist;
        map = image;
    }
}
