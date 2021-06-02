package TourPlanner;

public class Log {
    public String logName, fuel, speed, id;
    public String totalTime, weather, transportation, seasonalClosure, trafficJam;
    public double distance, rating, fuelUsed, averageSpeed;
    public boolean seas, traf;
    public Log() {}

    public Log(String name){
        logName = name;
    }

    public Log(String n, String time, double r, String w, boolean seas,
               String transp, boolean traf, String fu, String avsp, String id){
        this.logName = n;
        this.totalTime = time;
        this.weather = w;
        this.transportation = transp;
        this.seas = seas;
        this.traf = traf;
        this.rating = r;
        this.fuel = fu;
        this.speed = avsp;
        this.id = id;
    }
}
