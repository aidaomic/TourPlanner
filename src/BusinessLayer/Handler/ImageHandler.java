package BusinessLayer.Handler;

import BusinessLayer.Logging.LoggingHandler;
import com.itextpdf.text.BadElementException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageHandler {

    private LoggingHandler log = new LoggingHandler();

    public Image resize(BufferedImage givenImage, double width, double height){
        java.awt.Image resizeImage = givenImage.getScaledInstance((int)width, (int)height,0);
        BufferedImage resized = new BufferedImage((int) width,(int) height,BufferedImage.TYPE_INT_ARGB);
        resized.getGraphics().drawImage(resizeImage, 0, 0 ,null);
        log.logDebug("Image resized -ImageHandler-");
        return SwingFXUtils.toFXImage(resized, null);
    }

    public void storeOnFS(String name, BufferedImage mapImage) throws IOException {
        if(new File(name+".jpg").exists())
            return;
        else
            ImageIO.write(mapImage, "jpg", new File(name+".jpg"));
        log.logDebug("Image stored on FS -ImageHandler-");
    }

    public void updateImage(String name, BufferedImage mapImage) throws IOException {
        ImageIO.write(mapImage, "jpg", new File(name+".jpg"));
        log.logDebug("Image updated -ImageHandler-");
    }

    public Image getImageFromFS(String name) throws IOException {
        BufferedImage bufImage = ImageIO.read(new File(name.substring(7)+".jpg"));
        log.logDebug("Loaded Image from FS -ImageHandler-");
        return SwingFXUtils.toFXImage(bufImage, null);
    }

    public com.itextpdf.text.Image bufferedImageToItextImage(BufferedImage bufferedImage) throws IOException {
        com.itextpdf.text.Image map = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            map = com.itextpdf.text.Image.getInstance(baos.toByteArray());
        } catch (BadElementException e) {
            log.logError("Could not parse Instance to Image -ImageHandler- ");
        }
        return map;
    }
}
