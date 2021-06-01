package BusinessLayer.Logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class LoggingHandler {

    private Logger log;

    public Logger logger(){
        log = LogManager.getLogger();
        Configurator.initialize(null, "BusinessLayer/Logging/logging.conf.xml");
        return log;
    }

    public void logInfo(String infoMsg){
        logger().info(infoMsg);
    }

    public void logDebug(String debugMsg){
        logger().debug(debugMsg);
    }

    public void logError(String errorMsg){
        logger().error(errorMsg);
    }
}
