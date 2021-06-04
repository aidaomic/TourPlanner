package BusinessLayer.Pdf;

import BusinessLayer.Handler.Paths.TourPathHandler;
import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.MapQuest.MapQuest;
import DataAccessLayer.Database_Tours;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PdfImportTours implements PdfImporter{

    private LoggingHandler log = new LoggingHandler();

    public void importFromPdf() {
        try {
            com.itextpdf.text.pdf.PdfReader reader = new com.itextpdf.text.pdf.PdfReader(new TourPathHandler().inputPath());
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
            log.logInfo("Added "+counter+" Tours from Pdf to Database successfully -PdfReader-");
            reader.close();

        } catch (IOException e) {
            log.logError("IOException: Problem reading Tours from Pdf -PdfReader-");
        }
    }
}
