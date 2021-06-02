package DataAccessLayer;

import BusinessLayer.Handler.PropertyHandler;
import BusinessLayer.Logging.LoggingHandler;
import TourPlanner.Log;
import TourPlanner.LogTable;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

public class Database_Logs implements Database{

    private Connection connection = null;
    public PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    private ArrayList logArray = new ArrayList();
    private LoggingHandler log = new LoggingHandler();

    public Connection connectDatabase(){
        try {
            Properties prop = new PropertyHandler().getConnectionProperty();
            connection = DriverManager.getConnection(prop.getProperty("connectionURL"), prop.getProperty("connectionUser"), prop.getProperty("connectionPW"));
            log.logDebug("Database Connection established -Database_Logs-");
        } catch (SQLException e) {
            log.logError("SQL Exception while connecting to database -Database_Logs-");
        } catch (IOException e) {
            log.logError("IOException while connecting to database -Database_Logs-");
        }
        return connection;
    }

    public void save(ArrayList list){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("saveLog"));
            preparedStatement.setString(1, String.valueOf(list.get(0)));
            preparedStatement.setString(2, LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            preparedStatement.setString(3, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            preparedStatement.setFloat(4, Float.valueOf(String.valueOf(list.get(1))));
            preparedStatement.setString(5, (String) list.get(2));
            preparedStatement.setDouble(6, Double.valueOf(String.valueOf(list.get(3))));
            preparedStatement.setString(7, String.valueOf(list.get(4)));
            preparedStatement.setBoolean(8, Boolean.parseBoolean(String.valueOf(list.get(5))));
            preparedStatement.setString(9, String.valueOf(list.get(6)));
            preparedStatement.setBoolean(10, Boolean.parseBoolean(String.valueOf(list.get(7))));
            preparedStatement.setDouble(11, Double.valueOf(String.valueOf(list.get(8))));
            preparedStatement.setDouble(12, Double.valueOf(String.valueOf(list.get(9))));
            preparedStatement.execute();
            connection.close();
            log.logInfo("Tour Log saved successfully to database -Database_Logs-");
        } catch (SQLException e) {
            log.logError("SQL Exception saving Tour Log to database -Database_Logs-");
        } catch (IOException e) {
            log.logError("IO Exception saving Tour Log to database -Database_Logs-");
        }
    }

    public ArrayList getLogs() {
        try {
            logArray.clear();
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getSqlQuery("allLogs"));
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                LogTable tourLog= new LogTable(rs.getInt(1),
                        rs.getString(2), rs.getString(3) + " " +
                        rs.getString(4), rs.getDouble(5), rs.getString(6),
                        rs.getDouble(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getDouble(13));
                logArray.add(tourLog);

            }
            connection.close();
            log.logInfo("Tour Log Data retrieved successfully from database -Database_Logs-");
        } catch (SQLException e) {
            log.logError("SQL Exception retrieving Tour Log Data from database -Database_Logs-");
        } catch (IOException e) {
            log.logError("IO Exception retrieving Tour Log Data from database -Database_Logs-");
        }
        return logArray;
    }

    public void delete(String name){//name equals id in this case
        try {
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getSqlQuery("deleteLog"));
            preparedStatement.setInt(1, Integer.parseInt(name));
            preparedStatement.execute();
            connection.close();
            log.logInfo("Tour Log deleted successfully from database -Database_Logs-");
        } catch (SQLException e) {
            log.logError("SQL Exception deleting Tour Log from database -Database_Logs-");
        } catch (IOException e) {
            log.logError("IO Exception deleting Tour Log from database -Database_Logs-");
        }
    }

    public void edit(Log tourLog){
        try {
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getSqlQuery("editLog"));
            preparedStatement.setString(1, tourLog.totalTime);
            preparedStatement.setInt(2, (int) tourLog.rating);
            preparedStatement.setString(3, tourLog.weather);
            preparedStatement.setBoolean(4, tourLog.seas);
            preparedStatement.setString(5, tourLog.transportation);
            preparedStatement.setBoolean(6, tourLog.traf);
            preparedStatement.setDouble(7, tourLog.fuelUsed);
            preparedStatement.setDouble(8, tourLog.averageSpeed);
            preparedStatement.setString(9, tourLog.logName);
            preparedStatement.execute();
            connection.close();
            log.logInfo("Tour Log saved edited changes successfully to database -Database_Logs-");
        } catch (SQLException e) {
            log.logError("SQL Exception saving edited Tour Log to database -Database_Logs-");
        } catch (IOException e) {
            log.logError("IO Exception saving edited Tour Log to database -Database_Logs-");
        }
    }

    public ArrayList getSearchedTourLogs(String text) {
        try{
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getSqlQuery("searchedLogs"));
            preparedStatement.setString(1, "%"+text+"%");
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                LogTable tourLog= new LogTable(rs.getInt(1),
                        rs.getString(2), rs.getString(3) + " " +
                        rs.getString(4), rs.getDouble(5), rs.getString(6),
                        rs.getDouble(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getDouble(13));
                logArray.add(tourLog);
            }
            connection.close();
            log.logInfo("Retrieved searched Tour Log Data successfully from database -Database_Logs-");
        } catch (SQLException e) {
            log.logError("SQL Exception retrieving searched Tour Log Data from database -Database_Logs-");
        } catch (IOException e) {
            log.logError("IO Exception retrieving searched Tour Log Data from database -Database_Logs-");
        }
        return logArray;
    }

    public PdfPTable toFileTable(PdfPTable exportTable) {
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("exportLogs"));
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                for (int i = 1; i<=13; i++) {
                    exportTable.addCell(new Paragraph(rs.getString(i), FontFactory.getFont(FontFactory.HELVETICA, 10)));
                }
                exportTable.completeRow();
            }
            log.logInfo("Successfully exporting Tour Logs to TableView -Database_Logs-");
        } catch (SQLException e) {
            log.logError("SQL Exception exporting Tour Logs to TableView -Database_Logs-");
        } catch (IOException e) {
            log.logError("IO Exception exporting Tour Logs to TableView -Database_Logs-");
        }
        return exportTable;
    }

    public String toFile() {
        String output = "";
        try {
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getSqlQuery("exportLogs"));
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                for(int i = 1; i <=13; i++){
                    output = output + rs.getString(i) + ";";
                }
                output += "\n";
            }
            connection.close();
            log.logInfo("Successfully exporting Tour Logs to Pdf -Database_Logs-");
        } catch (SQLException e) {
            log.logError("SQL Exception exporting Tour Logs to Pdf -Database_Logs-");
        } catch (IOException e) {
            log.logError("IO Exception exporting Tour Logs to Pdf -Database_Logs-");
        }
        return output;
    }

    public void deleteAllLogs(String name) {
        try {
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getSqlQuery("deleteAllLogs"));
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            connection.close();
            log.logInfo("All Tour Logs deleted successfully from database -Database_Logs-");
        } catch (SQLException e) {
            log.logError("SQL Exception deleting all Tour Logs from database -Database_Logs-");
        } catch (IOException e) {
            log.logError("IO Exception deleting all Tour Logs from database -Database_Logs-");
        }
    }
}
