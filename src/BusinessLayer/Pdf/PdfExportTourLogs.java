package BusinessLayer.Pdf;

import BusinessLayer.Logging.LoggingHandler;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfExportTourLogs {

    private Document doc = new Document();
    private LoggingHandler log = new LoggingHandler();

    public void tourLogsToPdfTable(){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("TourLogsFromDatabase-TableView-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();

            PdfPTable table = new PdfPTable(13); // 5 columns w/o image
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,};
            table.setWidths(columnWidths);

            table.addCell(new Paragraph("Log Id", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Tour Name", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Creation Date", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Creation Time", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Distance", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Total Time", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Rating", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Weather", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Seasonal Closure", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Transportation", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Traffic Jam", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Fuel Used", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.addCell(new Paragraph("Average Speed", FontFactory.getFont(FontFactory.HELVETICA, 10)));
            table.completeRow();

            doc.add(new Database_Logs().toFileTable(table));
            doc.close();
            log.logInfo("Exported Tour Logs to Table View successfully -PdfGenerator-");
        } catch (Exception e) {
            log.logError("Problem generating/exporting Tour Logs to Table View -PdfGenerator-");
        }
    }

    public void tourLogsToPdf(){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("TourLogsFromDatabase-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();
            doc.add(new Paragraph(new Database_Logs().toFile()));
            doc.close();
            log.logInfo("Exported Tour Logs successfully to Pdf -PdfGenerator-");
        } catch (Exception e) {
            log.logError("Problem generating/exporting Tour Logs to Pdf -PdfGenerator-");
        }
    }
}
