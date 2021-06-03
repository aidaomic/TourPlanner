package BusinessLayer.Handler;

import BusinessLayer.Logging.LoggingHandler;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class FontHandler {

    private LoggingHandler log = new LoggingHandler();

    public Font helvetica(float size){
        log.logDebug("Retrieving Helvetica -FontHandler-");
        return FontFactory.getFont(FontFactory.HELVETICA, size);
    }

    public Font courierBold(float size){
        log.logDebug("Retrieving Courier_Bold -FontHandler-");
        return FontFactory.getFont(FontFactory.COURIER_BOLD, size);
    }
}
