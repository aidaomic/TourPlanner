package BusinessLayer.Pdf;

import BusinessLayer.Logging.LoggingHandler;
import DataAccessLayer.Database_Tours;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfExportTours {

    private Document doc = new Document();
    private LoggingHandler log = new LoggingHandler();

    public void toursToPdfTable(){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("ToursFromDatabase-TableView-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();

            PdfPTable table = new PdfPTable(5); // 5 columns w/o image
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);

            table.addCell("Tour Name");
            table.addCell("Description");
            table.addCell("Start");
            table.addCell("End");
            table.addCell("Distance");
            table.completeRow();

            doc.add(new Database_Tours().toFileTable(table));
            doc.close();
            log.logInfo("Tours exported to Table View successfully -PdfGenerator-");
        } catch (Exception e) {
            log.logError("Proplem generating/exporting Tour Table View -PfdGenerator-");
        }
    }

    public void toursToPdf(){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("ToursFromDatabase-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();
            doc.add(new Paragraph(new Database_Tours().toFile()));
            doc.close();
            log.logInfo("Tours exported successfully to Pdf -PdfGenerator-");
        } catch (Exception e) {
            log.logError("Proplem generating/exporting Tours to Pdf -PdfGenerator-");
        }
    }
}
