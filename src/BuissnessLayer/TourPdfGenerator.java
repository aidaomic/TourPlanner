package BuissnessLayer;

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

public class TourPdfGenerator {

    private Document doc = new Document();

    public void toursToPdf(){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("ToursFromDatabase-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
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

            doc.add(new Database_Tours().toFile(table));
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pdfToTours() {
        try {
            PdfReader reader = new PdfReader(new PathHandler().inputPath());
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

            StringTokenizer token = new StringTokenizer(textFromPage, ";");

            Database_Tours dbt = new Database_Tours();
            ArrayList t = new ArrayList();
            int counter = 0;
            while(token.hasMoreElements()){
                t.clear();
                if(counter != 0)
                    t.add(token.nextToken().substring(2));
                else
                    t.add(token.nextToken());
                t.add(token.nextToken());
                String start = token.nextToken();
                t.add(start);
                String end = token.nextToken();
                t.add(end);
                MapQuest mq = new MapQuest();
                BufferedImage img = mq.getDirections(start, end);
                t.add(mq.distance);
                t.add(img);
                dbt.save(t);
                counter++;
            }
            System.out.println("Added "+counter+" Tours!");
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
