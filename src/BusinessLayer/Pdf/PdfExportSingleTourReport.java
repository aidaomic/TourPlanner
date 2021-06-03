package BusinessLayer.Pdf;

import BusinessLayer.Handler.FontHandler;
import BusinessLayer.Handler.ImageHandler;
import BusinessLayer.Handler.PdfTableHandler;
import BusinessLayer.Logging.LoggingHandler;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Report;
import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PdfExportSingleTourReport {

    private LoggingHandler log = new LoggingHandler();
    Database_Tours dbt = new Database_Tours();

    public void singleTourReport(Document doc, ArrayList names){
        try {
            for(int i = 0; i<names.size();i++) {

                Paragraph title = new Paragraph(new Phrase(20f, "Tour Report: " + names.get(i) + "\n", new FontHandler().courierBold(20f)));
                doc.add(title);

                Tour tour = dbt.specificTour(String.valueOf(names.get(i)));

                doc.add(new Paragraph("\n\nDescription: " + tour.tourDescription + "\n\n"));
                doc.add(new Paragraph("Start: " + tour.tourSart));
                doc.add(new Paragraph("Destination: " + tour.tourEnd));
                doc.add(new Paragraph("Distance: " + tour.tourDistance + "\n\n"));

                Image map = new ImageHandler().bufferedImageToItextImage(tour.tourImage);
                map.scaleAbsolute(250, 150);
                map.setAbsolutePosition(300, 660);
                doc.add(map);

                PdfPTable table = new PdfTableHandler().createTableHeader_TourLogs();
                doc.add(new Database_Report().toFileTable(table, String.valueOf(names.get(i))));

                doc.add(new Paragraph("Average Tour Log: \n"));
                PdfPTable tableAverage = new PdfTableHandler().createTableHeader_AverageLog();
                doc.add(new Database_Report().getAverage(tableAverage, String.valueOf(names.get(i))));

                doc.newPage();

                log.logInfo("SingleTour Report for Tour '" + names.get(i) + "' created successfully -PdfExportSingleTourReport-");
            }
        } catch (DocumentException e) {
            log.logError("Problem creating Document for Single Tour Report -PdfExportSingleTourReport-");
        } catch (FileNotFoundException e) {
            log.logError("Problem creating File for Report -PdfExportSingleTourReport-");
        } catch (IOException e) {
            log.logError("IOException -PdfExportSingleTourReport-");
        }

    }
}
