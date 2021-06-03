package BusinessLayer.Handler;

import BusinessLayer.Logging.LoggingHandler;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class AverageCalculator {
    private int counter;
    private LoggingHandler log = new LoggingHandler();

    public AverageCalculator(int counter) {
        this.counter = counter;
    }

    public String avgDouble(double value){
        log.logDebug("Retrieving average value from double -AverageCalculator-");
        return String.valueOf(value/counter);
    }

    public String avgInt(int value){
        log.logDebug("Retrieving average value from int -AverageCalculator-");
        return String.valueOf(value/counter);
    }

    public String getAverageTime(ArrayList time){
        int seconds = 0;
        for(int i = 0; i < time.size(); i++){
            StringTokenizer token = new StringTokenizer(time.get(i).toString(),":");
            if (token.countTokens()==3) {
                int hours = Integer.parseInt(token.nextToken())*60*60;
                seconds += hours;
            }
            int minutes = Integer.parseInt(token.nextToken())*60;
            seconds += minutes + Integer.parseInt(token.nextToken());
        }
        seconds = seconds/counter;
        int minute = 0;
        while(seconds>60){
            minute += seconds/60;
            seconds = seconds%60;
        }
        int hours = 0;
        while(minute>60){
            hours += minute/60;
            minute = minute%60;
        }
        log.logDebug("Retrieving average value from time ('00:00:00') -AverageCalculator-");
        return hours +":"+minute+":"+seconds;
    }
}
