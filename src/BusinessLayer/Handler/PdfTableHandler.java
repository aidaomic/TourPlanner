package BusinessLayer.Handler;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfTableHandler {

    public PdfPTable createTableHeader_TourLogs(){

        PdfPTable table = new PdfPTable(13);
        try {
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        //Set Column widths
        float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,};
        table.setWidths(columnWidths);
        FontHandler font = new FontHandler();
        table.addCell(new Paragraph("Log Id", font.helvetica(10)));
        table.addCell(new Paragraph("Tour Name", font.helvetica(10)));
        table.addCell(new Paragraph("Creation Date",font.helvetica(10)));
        table.addCell(new Paragraph("Creation Time", font.helvetica(10)));
        table.addCell(new Paragraph("Distance", font.helvetica(10)));
        table.addCell(new Paragraph("Total Time", font.helvetica(10)));
        table.addCell(new Paragraph("Rating", font.helvetica(10)));
        table.addCell(new Paragraph("Weather", font.helvetica(10)));
        table.addCell(new Paragraph("Seasonal Closure", font.helvetica(10)));
        table.addCell(new Paragraph("Transportation",font.helvetica(10)));
        table.addCell(new Paragraph("Traffic Jam",font.helvetica(10)));
        table.addCell(new Paragraph("Fuel Used", font.helvetica(10)));
        table.addCell(new Paragraph("Average Speed",font.helvetica(10)));
        table.completeRow();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return table;
    }

    public PdfPTable createTableHeader_AverageLog(){

        PdfPTable table = new PdfPTable(9);
        try {
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f,};
            table.setWidths(columnWidths);
            FontHandler font = new FontHandler();
            table.addCell(new Paragraph("Distance", font.helvetica(10)));
            table.addCell(new Paragraph("Total Time", font.helvetica(10)));
            table.addCell(new Paragraph("Rating", font.helvetica(10)));
            table.addCell(new Paragraph("Weather", font.helvetica(10)));
            table.addCell(new Paragraph("Seasonal Closure", font.helvetica(10)));
            table.addCell(new Paragraph("Transportation", font.helvetica(10)));
            table.addCell(new Paragraph("Traffic Jam", font.helvetica(10)));
            table.addCell(new Paragraph("Fuel Used", font.helvetica(10)));
            table.addCell(new Paragraph("Average Speed", font.helvetica(10)));
            table.completeRow();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return table;
    }
}
