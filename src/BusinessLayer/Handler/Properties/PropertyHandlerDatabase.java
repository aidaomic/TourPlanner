package BusinessLayer.Handler.Properties;

import BusinessLayer.Logging.LoggingHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandlerDatabase implements PropertyHandler{

    private LoggingHandler log = new LoggingHandler();

    private Properties prop;
    private InputStream input = null;

    public void setUpPropertyHandler(){
        prop = new Properties();
        input = getClass().getClassLoader().getResourceAsStream("config.properties");
    }

    public Properties getConnectionProperty() throws IOException {
        setUpPropertyHandler();
        prop.load(input);
        log.logDebug("Retrieved Connection Property -PropertyHandlerDatabase-");
        return prop;
    }

    public String getSqlQuery(String name) throws IOException {
        setUpPropertyHandler();
        input = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);
        log.logDebug("Retrieved SQL Query -PropertyHandlerDatabase-");
        return prop.getProperty(name);
    }

}
