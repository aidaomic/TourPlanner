package BusinessLayer.Pdf;

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
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfExportSingleTourReport {

    private Document doc = new Document(PageSize.A4);
    private LoggingHandler log = new LoggingHandler();
    Database_Tours dbt = new Database_Tours();

    public void singleTourReport(String name){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(name+"-Report-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();

            Paragraph title = new Paragraph(new Phrase(20f, "Tour Report: "+name+"\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 20f)));
            doc.add(title);

            Tour tour = dbt.specificTour(name);

            doc.add(new Paragraph("\n\nDescription: "+tour.tourDescription+"\n\n"));
            doc.add(new Paragraph("Start: "+tour.tourSart));
            doc.add(new Paragraph("Destination: "+tour.tourEnd));
            doc.add(new Paragraph("Distance: "+tour.tourDistance+"\n\n"));

            Image map = new ImageHandler().bufferedImageToItextImage(tour.tourImage);
            map.scaleAbsolute(250, 150);
            map.setAbsolutePosition(300,660);
            doc.add(map);

            PdfPTable table = new PdfTableHandler().createTableHeader_TourLogs();

            doc.add(new Database_Report().toFileTable(table, name));


            doc.close();
            log.logInfo("SingleTour Report for Tour '"+name+"' created successfully -PdfExportSingleTourReport-");
        } catch (DocumentException e) {
            log.logError("Problem creating Document for Single Tour Report -PdfExportSingleTourReport-");
        } catch (FileNotFoundException e) {
            log.logError("Problem creating File for Report -PdfExportSingleTourReport-");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
