package BusinessLayer.Pdf;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.MapQuest.MapQuest;
import BusinessLayer.Handler.PathHandler;
import DataAccessLayer.Database_Logs;
import DataAccessLayer.Database_Tours;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PdfReader {

    private LoggingHandler log = new LoggingHandler();

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
            log.logInfo("Added "+counter+" Tours from Pdf to Database successfully -PdfReader-");
            reader.close();

        } catch (IOException e) {
            log.logError("IOException: Problem reading Tours from Pdf -PdfReader-");
        }
    }

    public void pdfToTourLogs() {
        try {
            com.itextpdf.text.pdf.PdfReader reader = new com.itextpdf.text.pdf.PdfReader(new PathHandler().inputPath());
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

            StringTokenizer token = new StringTokenizer(textFromPage, ";");

            Database_Logs dbt = new Database_Logs();
            ArrayList t = new ArrayList();
            int counter = 0;
            while(token.hasMoreElements()){
                t.clear();
                if(counter != 0)
                    t.add(token.nextToken().substring(2));
                else
                    t.add(token.nextToken());
                for (int i = 1; i < 10; i++){
                    t.add(token.nextToken());
                }
                dbt.save(t);
                counter++;
            }
            log.logInfo("Added "+counter+" Tour Logs from Pdf to Database -PdfReader-");
            reader.close();

        } catch (IOException e) {
            log.logError("IOException: Problem reading Tour Logss from Pdf -PdfReader-");
        }
    }

}
