package TourPlanner;

public class Log {
    public String logName, creationDate, creationTime;
    public String totalTime, weather, transportation, seasonalClosure, trafficJam;
    public double distance, rating, fuelUsed, averageSpeed;

    public Log(){}

    public Log(String logName, String creationDate, String creationTime, String totalTime, String weather, String transportation, String seasonalClosure, String trafficJam, double distance, double rating, double fuelUsed, double averageSpeed) {
        this.logName = logName;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
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
