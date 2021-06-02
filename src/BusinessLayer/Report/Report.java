package BusinessLayer.Report;

import BusinessLayer.Pdf.PdfExportSingleTourReport;

public class Report {

    //wird vllt noch ausgebaut zu nur gewählten attrbuten!

    public void singleTourReport(String tourName){
        new PdfExportSingleTourReport().singleTourReport(tourName);
    }
}
