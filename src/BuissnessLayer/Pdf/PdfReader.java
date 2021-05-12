package BuissnessLayer.Pdf;

import BuissnessLayer.MapQuest.MapQuest;
import BuissnessLayer.Handler.PathHandler;
import DataAccessLayer.Database_Tours;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PdfReader {

    public void pdfToTours() {
        try {
            com.itextpdf.text.pdf.PdfReader reader = new com.itextpdf.text.pdf.PdfReader(new PathHandler().inputPath());
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
