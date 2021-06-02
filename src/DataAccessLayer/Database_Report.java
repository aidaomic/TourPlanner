package DataAccessLayer;

import BusinessLayer.Handler.PropertyHandler;
import BusinessLayer.Logging.LoggingHandler;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database_Report {

    private Connection connection = null;
    public PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    private final LoggingHandler log = new LoggingHandler();

    public Connection connectDatabase(){
        try {
            Properties prop = new PropertyHandler().getConnectionProperty();
            connection = DriverManager.getConnection(prop.getProperty("connectionURL"), prop.getProperty("connectionUser"), prop.getProperty("connectionPW"));
            log.logDebug("Database Connection established -Database_Report-");
        } catch (SQLException e) {
            log.logError("SQL Exception while connecting to database -Database_Report-");
        } catch (IOException e) {
            log.logError("IOException while connecting to database -Database_Report-");
        }
        return connection;
    }

    public PdfPTable toFileTable(PdfPTable exportTable, String tourName) {
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("exportLogsSingleTour"));
            preparedStatement.setString(1, tourName);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                for (int i = 1; i<=13; i++) {
                    exportTable.addCell(new Paragraph(rs.getString(i), FontFactory.getFont(FontFactory.HELVETICA, 10)));
                }
                exportTable.completeRow();
            }
            log.logInfo("Successfully exporting Tour Logs to TableView -Database_Report-");
        } catch (SQLException e) {
            log.logError("SQL Exception exporting Tour Logs to TableView -Database_Report-");
        } catch (IOException e) {
            log.logError("IO Exception exporting Tour Logs to TableView -Database_Report-");
        }
        return exportTable;
    }
}
