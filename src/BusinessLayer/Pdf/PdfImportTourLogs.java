package BusinessLayer.Pdf;

import BusinessLayer.Handler.Paths.TourLogPathHandler;
import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.Handler.Paths.AllroundPathHandler;
import DataAccessLayer.Database_Logs;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PdfImportTourLogs implements PdfImporter{

    private LoggingHandler log = new LoggingHandler();

    public void importFromPdf() {
        try {
            com.itextpdf.text.pdf.PdfReader reader = new com.itextpdf.text.pdf.PdfReader(new TourLogPathHandler().inputPath());
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);

            StringTokenizer token = new StringTokenizer(textFromPage, ";");

            Database_Logs dbtl = new Database_Logs();
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
                dbtl.save(t);
                counter++;
            }
            log.logInfo("Added "+counter+" Tour Logs from Pdf to Database -PdfReader-");
            reader.close();

        } catch (IOException e) {
            log.logError("IOException: Problem reading Tour Logss from Pdf -PdfReader-");
        }
    }

}
