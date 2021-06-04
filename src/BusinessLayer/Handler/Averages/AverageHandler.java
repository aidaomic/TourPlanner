package BusinessLayer.Handler.Averages;

import BusinessLayer.Handler.FontHandler;
import BusinessLayer.Logging.LoggingHandler;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;

public class AverageHandler {
    private LoggingHandler log = new LoggingHandler();
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
        FontHandler font = new FontHandler();
        AverageCalculator avg = new AverageCalculator(counter);
        table.addCell(new Paragraph(avg.avgDouble(distance), font.helvetica(10)));
        table.addCell(new Paragraph(avg.getAverageTime(time), font.helvetica(10)));
        table.addCell(new Paragraph(avg.avgInt(rating), font.helvetica(10)));
        //table.addCell(new Paragraph("Weather", font.helvetica(10)));
        table.addCell(new Paragraph(new CounterHandler().countTrueFalse(seasClos), font.helvetica(10)));
        //table.addCell(new Paragraph("Transport", font.helvetica(10)));
        table.addCell(new Paragraph(new CounterHandler().countTrueFalse(traf), font.helvetica(10)));
        table.addCell(new Paragraph(avg.avgDouble(fuel), font.helvetica(10)));
        table.addCell(new Paragraph(avg.avgDouble(speed), font.helvetica(10)));
        table.completeRow();
        log.logInfo("PdfTable for Averages created -AverageHandler-");
        return table;
    }

}
