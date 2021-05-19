package TourPlanner;

public class LogTable {

    public String tourName, dateAndTime;
    public String totalTime, weather, transportation, seasClos, traffic;
    public double distance, rating, fuelUsed, speed;
    public int logId;

    public LogTable(int id, String logName, String creationDateTime, double distance, String totalTime, double rating, String weather, String seasonalClosure, String transportation, String trafficJam, double fuelUsed, double averageSpeed) {
        this.logId = id;
        this.tourName = logName;
        this.dateAndTime = creationDateTime;
        this.totalTime = totalTime;
        this.weather = weather;
        this.transportation = transportation;
        this.seasClos = seasonalClosure;
        this.traffic = trafficJam;
        this.distance = distance;
        this.rating = rating;
        this.fuelUsed = fuelUsed;
        this.speed = averageSpeed;
    }

    public int getLogId() {
        return logId;
    }

    public String getTourName() {
        return tourName;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getWeather() {
        return weather;
    }

    public String getTransportation() {
        return transportation;
    }

    public String getSeasClos() {
        return seasClos;
    }

    public String getTraffic() {
        return traffic;
    }

    public double getDistance() {
        return distance;
    }

    public double getRating() {
        return rating;
    }

    public double getFuelUsed() {
        return fuelUsed;
    }

    public double getSpeed() {
        return speed;
    }
}
