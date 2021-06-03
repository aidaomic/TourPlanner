package BusinessLayer.Handler;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class AverageHandler {
    private double distance, fuel, speed;
    int rating, counter;
    ArrayList time, weather, seasClos, traf, transport;

    public AverageHandler(int counter, double distance, ArrayList time, int rating, ArrayList weather, ArrayList seasClos, ArrayList transport, ArrayList traf, double fuel, double speed) {
        this.counter = counter;
        this.distance = distance;
        this.time = time;
        this.fuel = fuel;
        this.speed = speed;
        this.rating =rating;
        this.weather = weather;
        this.seasClos = seasClos;
        this.transport = transport;
        this.traf = traf;
    }

    public AverageHandler(int counter, double distance, ArrayList time, int rating, ArrayList seasClos, ArrayList traf, double fuel, double speed) {
        this.counter = counter;
        this.distance = distance;
        this.time = time;
        this.fuel = fuel;
        this.speed = speed;
        this.rating =rating;
        this.weather = weather;
        this.seasClos = seasClos;
        this.traf = traf;
    }


    public PdfPTable getAverages(PdfPTable table){
        table.addCell(new Paragraph(avgDistance(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph(getAverageTime(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph(avgRating(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        //table.addCell(new Paragraph("Weather", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph(avgSeasClos(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        //table.addCell(new Paragraph("Transport", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph(avgTrafJam(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph(avgFuel(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph(avgSpeed(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.completeRow();
        return table;
    }

    public String avgDistance(){
        return String.valueOf(distance/counter);
    }

    public String avgRating(){
        return String.valueOf(rating/counter);
    }

    public String avgSeasClos(){
        return new CounterHandler().countTrueFalse(seasClos);
    }

    public String avgTrafJam(){
        return new CounterHandler().countTrueFalse(traf);
    }

    public String avgFuel(){
        return String.valueOf(fuel/counter);
    }

    public String avgSpeed(){
        return String.valueOf(speed/counter);
    }

    public String getAverageTime(){
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
        return hours +":"+minute+":"+seconds;
    }

}
