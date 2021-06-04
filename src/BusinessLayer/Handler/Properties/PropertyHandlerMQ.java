package BusinessLayer.Handler.Properties;

import BusinessLayer.Logging.LoggingHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandlerMQ implements PropertyHandler {

    private LoggingHandler log = new LoggingHandler();

    private Properties prop;
    private InputStream input = null;

    public void setUpPropertyHandler(){
        prop = new Properties();
        input = getClass().getClassLoader().getResourceAsStream("config.properties");
    }

    public String getMapQuest_directions( String startPoint, String destination) throws IOException {
        setUpPropertyHandler();
        input = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);
        log.logDebug("Retrieved Query for MapQuest (Directions) -PropertyHandlerMQ-");
        return prop.getProperty("mqDirectionsUrlFrom")+startPoint+prop.getProperty("mqDirectionsTo")+destination;
    }

    public String getMapQuest_image(String sessionId, String boundBox) throws IOException {
        setUpPropertyHandler();
        input = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);
        String url = prop.getProperty("mqMapURLMarker")+"none"+prop.getProperty("mqMapSession")+sessionId
                +prop.getProperty("mqMapBox")+boundBox;
        log.logDebug("Retrieved Data for MapQuest (Map Image) -PropertyHandlerMQ-");
        return url;
    }
}
