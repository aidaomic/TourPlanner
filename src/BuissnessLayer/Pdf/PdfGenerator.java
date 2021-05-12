package BuissnessLayer.Pdf;

import DataAccessLayer.Database_Tours;
import TourPlanner.Tour;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Line;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

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

    public void logsToPdf(){

    }
}
