package BuissnessLayer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class ImageHandler {

    public Image resize(BufferedImage givenImage, double width, double height){
        java.awt.Image resizeImage = givenImage.getScaledInstance((int)width, (int)height,0);
        BufferedImage resized = new BufferedImage((int) width,(int) height,BufferedImage.TYPE_INT_ARGB);
        resized.getGraphics().drawImage(resizeImage, 0, 0 ,null);
        return SwingFXUtils.toFXImage(resized, null);
    }
}
