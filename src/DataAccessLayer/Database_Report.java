package DataAccessLayer;

import BusinessLayer.Handler.AverageHandler;
import BusinessLayer.Handler.PropertyHandler;
import BusinessLayer.Logging.LoggingHandler;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Database_Report {

    private Connection connection = null;
    public PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    public int counter = 0;
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
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("specificLogs"));
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

    public PdfPTable getAverage(PdfPTable exportTable, String tourName) {
        try {
            counter = 0;
            double distance = 0, fuel = 0, speed = 0;
            ArrayList weather = new ArrayList(), time = new ArrayList(), seasClos = new ArrayList(), transport = new ArrayList(), traf = new ArrayList();
            int rating = 0;
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("specificLogs"));
            preparedStatement.setString(1, tourName);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                //ignorng column 1,2,3 and 4 => id,tourname,creation date and time
                distance += rs.getDouble(5);
                time.add(rs.getString(6));
                rating += rs.getInt(7);
                //weather.add(rs.getString(8));
                seasClos.add(rs.getString(9));
                //transport.add(rs.getString(10));
                traf.add(rs.getString(11));
                fuel += rs.getDouble(12);
                speed += rs.getDouble(13);
                counter++;
            }
            AverageHandler average = new AverageHandler(counter, distance, time, rating, seasClos, traf, fuel, speed);
            log.logInfo("Successfully exporting Tour Logs to TableView -Database_Report-");
            return average.getAverages(exportTable);
        } catch (SQLException e) {
            log.logError("SQL Exception exporting Tour Logs to TableView -Database_Report-");
        } catch (IOException e) {
            log.logError("IO Exception exporting Tour Logs to TableView -Database_Report-");
        }
        return exportTable;
    }
}
