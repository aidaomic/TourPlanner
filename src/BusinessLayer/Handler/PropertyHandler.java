package BusinessLayer.Handler;

import BusinessLayer.Logging.LoggingHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {

    private Properties prop = new Properties();
    private LoggingHandler log = new LoggingHandler();
    private InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

    public Properties getConnectionProperty() throws IOException {
        prop.load(input);
        log.logDebug("Retrieved Connection Property -PropertyHandler-");
        return prop;
    }

    public String getSqlQuery(String name) throws IOException {
        input = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);
        log.logDebug("Retrieved SQL Query -PropertyHandler-");
        return prop.getProperty(name);
    }

    public String getMapQuest_directions( String startPoint, String destination) throws IOException {
        input = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);
        log.logDebug("Retrieved Query for MapQuest (Directions) -PropertyHandler-");
        return prop.getProperty("mqDirectionsUrlFrom")+startPoint+prop.getProperty("mqDirectionsTo")+destination;
    }


    public String getMapQuest_image(String sessionId, String boundBox) throws IOException {
        input = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);
        String url = prop.getProperty("mqMapURLMarker")+"none"+prop.getProperty("mqMapSession")+sessionId
                +prop.getProperty("mqMapBox")+boundBox;
        log.logDebug("Retrieved Data for MapQuest (Map Image) -PropertyHandler-");
        return url;
    }
}
