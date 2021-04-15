package BuissnessLayer;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Scanner;


public class MapQuest {

    private String mapQuestKey = "AnCMu0aBcasIZjPMl75ZbWdIZmRC2u4c";
    private String mapQuestData = "";
    private String session, boundingBox_ul_lat, boundingBox_ul_lng, boundingBox_lr_lat, boundingBox_lr_lng, boundingBox;
    private Scanner sc;

    public void getDirections(String startPoint, String destination){

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

            boundingBox = boundingBox_ul_lat + "," + boundingBox_ul_lng + "," +
                    boundingBox_lr_lat + "," + boundingBox_ul_lng;

            getStaticMap(session, boundingBox);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getStaticMap(String sessionId, String boundBox){

        String buildURL = "http://www.mapquestapi.com/staticmap/v5/map?key=" + mapQuestKey +
                "&size=640,480&defaultMarker=none&zoom=11&rand=737758036&session=" + sessionId + "&boundingBoze=" + boundBox;
        try {
            //System.out.println("URL: "+buildURL);
            URL url = new URL("http://www.mapquestapi.com/staticmap/v5/map?key=AnCMu0aBcasIZjPMl75ZbWdIZmRC2u4c&size=640,480&defaultMarker=none&zoom=11&rand=737758036&session=6077f1b6-03c7-4ee4-02b4-350e-0eb7c3a7e853&boundingBoze=48.203083,13.013587,47.797558,16.373583");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            //to check if done correctily
            int responsecode = con.getResponseCode();
            if(responsecode != 200)
               throw new RuntimeException("HttpResponseCode: " + responsecode);

            InputStream is = con.getInputStream();
            OutputStream os = new FileOutputStream("TryOut.jpg");

            byte[] buffer = new byte[1024];
            int byteReaded = is.read(buffer);
            while(byteReaded != -1) {
                os.write(buffer, 0, byteReaded);
                byteReaded = is.read(buffer);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
