package BuissnessLayer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {

    private Properties prop = new Properties();
    private InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

    public Properties getConnectionProperty() throws IOException {
        prop.load(input);
        return prop;
    }

    public String getSqlQuery(String name) throws IOException {
        input = getClass().getClassLoader().getResourceAsStream("config.properties");
        prop.load(input);
        return prop.getProperty(name);
    }

}
