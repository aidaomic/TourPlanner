package TourPlanner;

public class LogTable {

    public String logName, creationDateTime;
    public String totalTime, weather, transportation, seasonalClosure, trafficJam;
    public double distance, rating, fuelUsed, averageSpeed;

    public LogTable(String logName, String creationDateTime, double distance, String totalTime, double rating, String weather, String seasonalClosure, String transportation, String trafficJam, double fuelUsed, double averageSpeed) {
        this.logName = logName;
        this.creationDateTime = creationDateTime;
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
