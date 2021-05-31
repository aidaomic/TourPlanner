package BusinessLayer.Pdf;

import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfGenerator {

    private Document doc = new Document();

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toursToPdf(){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("ToursFromDatabase-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();
            doc.add(new Paragraph(new Database_Tours().toFile()));
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tourLogsToPdfTable(){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("TourLogssFromDatabase-TableView-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();

            PdfPTable table = new PdfPTable(13); // 5 columns w/o image
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,};
            table.setWidths(columnWidths);

            table.addCell("Log Id");
            table.addCell("Tour Name");
            table.addCell("Creation Date");
            table.addCell("Creation Time");
            table.addCell("Distance");
            table.addCell("Total Time");
            table.addCell("Rating");
            table.addCell("Weather");
            table.addCell("Seasonal Closure");
            table.addCell("Transportation");
            table.addCell("Traffic Jam");
            table.addCell("Fuel Used");
            table.addCell("Average Speed");
            table.completeRow();

            doc.add(new Database_Logs().toFileTable(table));
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logsToPdf(){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("TourLogsFromDatabase-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();
            doc.add(new Paragraph(new Database_Logs().toFile()));
            doc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
