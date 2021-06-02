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
        table.addCell(new Paragraph("Log Id", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Tour Name", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Creation Date", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Creation Time", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Distance", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Total Time", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Rating", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Weather", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Seasonal Closure", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Transportation", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Traffic Jam", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Fuel Used", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.addCell(new Paragraph("Average Speed", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        table.completeRow();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return table;
    }
}
