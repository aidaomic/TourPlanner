package BusinessLayer.Report;

import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.Pdf.PdfExportSingleTourReport;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Report {

    private Document doc = new Document(PageSize.A4);
    private LoggingHandler log = new LoggingHandler();

    //wird vllt noch ausgebaut zu nur gewählten Attributen

    public void singleTourReport(String name){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(name+"-Report-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();

            ArrayList names = new ArrayList();
            names.add(name);
            new PdfExportSingleTourReport().singleTourReport(doc, names);

            doc.close();
            log.logInfo("SingleTour Report for Tour '"+name+"' created successfully -Report-");
        } catch (DocumentException e) {
            log.logError("Problem creating Document for Single Tour Report -Report-");
        } catch (FileNotFoundException e) {
            log.logError("Problem creating File for Report -Report-");
        } catch (IOException e) {
            log.logError("IOException -PdfExportSingleTourReport-");
        }
    }

    public void tourReport(ArrayList names){
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Report-"+ new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +".pdf"));
            doc.open();

            new PdfExportSingleTourReport().singleTourReport(doc, names);

            doc.close();
            log.logInfo("Tour Report for all Tours created successfully -Report-");
        } catch (DocumentException e) {
            log.logError("Problem creating Document for Single Tour Report -Report-");
        } catch (FileNotFoundException e) {
            log.logError("Problem creating File for Report -Report-");
        } catch (IOException e) {
            log.logError("IOException -Report-");
        }
    }

}
