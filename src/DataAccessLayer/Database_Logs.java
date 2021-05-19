package DataAccessLayer;

import BuissnessLayer.Handler.PropertyHandler;
import TourPlanner.Log;
import TourPlanner.LogTable;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

public class Database_Logs implements Database{

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    private ArrayList logArray = new ArrayList();

    public Connection connectDatabase(){
        try {
            Properties prop = new PropertyHandler().getConnectionProperty();
            connection = DriverManager.getConnection(prop.getProperty("connectionURL"), prop.getProperty("connectionUser"), prop.getProperty("connectionPW"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void save(ArrayList list){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("saveLog"));
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getLogs() {
        try {
            logArray.clear();
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("allLogs"));
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                LogTable log = new LogTable(rs.getInt(1),
                        rs.getString(2), rs.getString(3) + " " +
                        rs.getString(4), rs.getDouble(5), rs.getString(6),
                        rs.getDouble(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getDouble(13));
                logArray.add(log);

            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logArray;
    }

    public void delete(String name){//name equals id in this case
        try {
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("deleteLog"));
            preparedStatement.setInt(1, Integer.parseInt(name));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void edit(Log log){
        try {
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("editLog"));
            preparedStatement.setString(1, log.totalTime);
            preparedStatement.setInt(2, (int) log.rating);
            preparedStatement.setString(3, log.weather);
            preparedStatement.setBoolean(4, log.seas);
            preparedStatement.setString(5, log.transportation);
            preparedStatement.setBoolean(6, log.traf);
            preparedStatement.setDouble(7, log.fuelUsed);
            preparedStatement.setDouble(8, log.averageSpeed);
            preparedStatement.setString(9, log.logName);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getSearchedTourLogs(String text) {
        try{
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("searchedLogs"));
            preparedStatement.setString(1, "%"+text+"%");
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                LogTable log = new LogTable(rs.getInt(1),
                        rs.getString(2), rs.getString(3) + " " +
                        rs.getString(4), rs.getDouble(5), rs.getString(6),
                        rs.getDouble(7), rs.getString(8), rs.getString(9),
                        rs.getString(10), rs.getString(11), rs.getDouble(12),
                        rs.getDouble(13));
                logArray.add(log);
            }
            connection.close();
        } catch (SQLException throwables) {
        throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logArray;
    }

    public PdfPTable toFileTable(PdfPTable exportTable) {
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("exportLogs"));
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                for (int i = 1; i<=13; i++) {
                    exportTable.addCell(rs.getString(i));
                }
                exportTable.completeRow();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exportTable;
    }

    public String toFile() {
        String output = "";
        try {
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("exportLogs"));
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                for(int i = 1; i <=13; i++){
                    output = output + rs.getString(i) + ";";
                }
                output += "\n";
            }
            connection.close();
        } catch (SQLException e){
            return "SQL Exception";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
