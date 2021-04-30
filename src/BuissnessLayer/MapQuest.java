package BuissnessLayer;

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

    private String mapQuestKey = "AnCMu0aBcasIZjPMl75ZbWdIZmRC2u4c";
    private String mapQuestData = "";
    private String session, boundingBox_ul_lat, boundingBox_ul_lng, boundingBox_lr_lat, boundingBox_lr_lng, boundingBox;
    public double distance;
    private Scanner sc;
    private BufferedImage map;

    public BufferedImage getDirections(String startPoint, String destination){

        String buildURL = "http://www.mapquestapi.com/directions/v2/route?key=" + mapQuestKey + "&from=" + startPoint + "&to=" + destination;
        try {
            URL url = new URL(buildURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

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
            //System.out.println(jsonNode);
            session = jsonNode.get("route").get("sessionId").asText();
            boundingBox_ul_lat = jsonNode.get("route").get("boundingBox").get("ul").get("lat").asText();
            boundingBox_ul_lng = jsonNode.get("route").get("boundingBox").get("ul").get("lng").asText();
            boundingBox_lr_lat = jsonNode.get("route").get("boundingBox").get("lr").get("lat").asText();
            boundingBox_lr_lng = jsonNode.get("route").get("boundingBox").get("lr").get("lng").asText();
            distance = jsonNode.get("route").get("distance").asDouble() * 1.609;

            boundingBox = boundingBox_ul_lat + "," + boundingBox_ul_lng + "," +
                    boundingBox_lr_lat + "," + boundingBox_lr_lng;

            map = getStaticMap(session, boundingBox);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

    public BufferedImage getStaticMap(String sessionId, String boundBox) throws IOException {

        String buildURL = "http://www.mapquestapi.com/staticmap/v5/map?key=" + mapQuestKey +
                "&size=640,480&defaultMarker=none&rand=737758036&session=" + sessionId + "&boundingBoze=" + boundBox;
        try {
            URL url = new URL(buildURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            //to check if done correctily
            int responsecode = con.getResponseCode();
            if(responsecode != 200)
               throw new RuntimeException("HttpResponseCode: " + responsecode);

            return ImageIO.read(con.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ImageIO.read(new File("image_not_found.jpg"));
    }

}
