package BusinessLayer.Handler;

import BusinessLayer.Logging.LoggingHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class JsonHandler {

    private LoggingHandler log = new LoggingHandler();

    public String getJsonValue(ArrayList t){

        try {
            ObjectMapper om = new ObjectMapper();
            String json = ".";
            System.out.println(t.get(0));
            json = om.writeValueAsString(t.get(0));
            log.logDebug("JsonHandler finished");
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            return "ERROR";
        }
    }

}
