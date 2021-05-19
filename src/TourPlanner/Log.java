package TourPlanner;

public class Log {
    public String logName;
    public String totalTime, weather, transportation, seasonalClosure, trafficJam;
    public double distance, rating, fuelUsed, averageSpeed;

    public Log() {}

    public Log(String name){
        logName = name;
    }

    public Log(String logName, String totalTime, String weather, String transportation, Boolean seasonalClosure, Boolean trafficJam, double distance, double rating, double fuelUsed, double averageSpeed){}

    public Log(String logName, String totalTime, String weather, String transportation, String seasonalClosure, String trafficJam, double distance, double rating, double fuelUsed, double averageSpeed) {
        this.logName = logName;
        this.totalTime = totalTime;
        this.weather = weather;
        this.transportation = transportation;
        this.seasonalClosure = seasonalClosure;
        this.trafficJam = trafficJam;
        this.distance = distance;
        this.rating = rating;
        this.fuelUsed = fuelUsed;
        this.averageSpeed = averageSpeed;
    }
}
