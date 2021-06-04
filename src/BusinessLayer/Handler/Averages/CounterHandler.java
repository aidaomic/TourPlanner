package BusinessLayer.Handler.Averages;

import BusinessLayer.Logging.LoggingHandler;

import java.util.ArrayList;

public class CounterHandler {
    private int yes, no;
    private LoggingHandler log = new LoggingHandler();

    public String countTrueFalse(ArrayList data){
        log.logDebug("Counting data from ArrayList -CounterHandler-");
        yes = 0;
        no = 0;
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).equals("t"))
                yes++;
            else
                no++;
        }
        if (yes>no)
            return "t";
        if (yes==no)
            return "t/f";
        return "f";
    }

}
