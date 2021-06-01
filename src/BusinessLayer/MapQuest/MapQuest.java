package BusinessLayer.MapQuest;

import BusinessLayer.Handler.PropertyHandler;
import BusinessLayer.Logging.LoggingHandler;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class MapQuest {

    private String mapQuestData = "";
    private String session, boundingBox_ul_lat, boundingBox_ul_lng, boundingBox_lr_lat, boundingBox_lr_lng, boundingBox;
    public double distance;
    private Scanner sc;
    private BufferedImage map;
    private LoggingHandler log = new LoggingHandler();

    public BufferedImage getDirections(String startPoint, String destination){

        try {
            URL url = new URL(new PropertyHandler().getMapQuest_directions(startPoint,destination));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            log.logDebug("MapQuest Request (Drections) sent -MapQuest-");
            //to check if done correctily
            int responsecode = con.getResponseCode();
            if(responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " + responsecode);

            sc = new Scanner(url.openStream());
            while (sc.hasNext()){
                mapQuestData = mapQuestData + sc.nextLine();
            }

            //JSON PARSING
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(mapQuestData);
            session = jsonNode.get("route").get("sessionId").asText();
            boundingBox_ul_lat = jsonNode.get("route").get("boundingBox").get("ul").get("lat").asText();
            boundingBox_ul_lng = jsonNode.get("route").get("boundingBox").get("ul").get("lng").asText();
            boundingBox_lr_lat = jsonNode.get("route").get("boundingBox").get("lr").get("lat").asText();
            boundingBox_lr_lng = jsonNode.get("route").get("boundingBox").get("lr").get("lng").asText();
            distance = jsonNode.get("route").get("distance").asDouble() * 1.609;

            boundingBox = boundingBox_ul_lat + "," + boundingBox_ul_lng + "," +
                    boundingBox_lr_lat + "," + boundingBox_lr_lng;

            log.logDebug("Retrieved directions succesfully -MapQuest-");

            map = getStaticMap(session, boundingBox);
        } catch (MalformedURLException e) {
            log.logError("Data could not be retrieved from MapQuest because of malformed URL -MapQuest-");
        } catch (JsonMappingException e) {
            log.logError("Problem with translating JSON, request has been closed -MapQuest-");
        } catch (IOException e) {
            log.logError("IOException while retrieving data from MapQuest -MapQuest-");
        }
        log.logInfo("Retrieved Data from MapQuest successfully -MapQuest-");
        return map;
    }

    public BufferedImage getStaticMap(String sessionId, String boundBox) throws IOException {
        try {
            URL url = new URL(new PropertyHandler().getMapQuest_image(sessionId, boundBox));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            log.logDebug("MapQuest Request (Drections) sent -MapQuest-");

            //to check if done correctily
            int responsecode = con.getResponseCode();
            if(responsecode != 200)
               throw new RuntimeException("HttpResponseCode: " + responsecode);

            log.logDebug("Retrieved map image from MapQuest successfully -MapQuest-");
            return ImageIO.read(con.getInputStream());

        } catch (MalformedURLException e) {
            log.logError("Data could not be retrieved from MapQuest because of malformed URL -MapQuest-");
        } catch (IOException e) {
            log.logError("IOException while retrieving data from MapQuest -MapQuest-");
        }
        log.logError("No Image found -MapQuest-");
        return ImageIO.read(new File("image_not_found.jpg"));
    }

}
