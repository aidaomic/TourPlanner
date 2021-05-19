package DataAccessLayer;

import BuissnessLayer.Handler.PropertyHandler;
import TourPlanner.Log;
import TourPlanner.LogTable;

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
            preparedStatement = connection.prepareStatement("insert into public.logs values (?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(list.get(0)));
            preparedStatement.setString(2, LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            preparedStatement.setString(3, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            preparedStatement.setFloat(4, Float.valueOf(String.valueOf(list.get(1))));
            preparedStatement.setString(5, (String) list.get(2));
            preparedStatement.setDouble(6, (Double) list.get(3));
            preparedStatement.setString(7, String.valueOf(list.get(4)));
            preparedStatement.setBoolean(8, Boolean.parseBoolean(String.valueOf(list.get(5))));
            preparedStatement.setString(9, String.valueOf(list.get(6)));
            preparedStatement.setBoolean(10, Boolean.parseBoolean(String.valueOf(list.get(7))));
            preparedStatement.setDouble(11, (Double) list.get(8));
            preparedStatement.setDouble(12, (Double) list.get(9));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Log specificLog(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Log();
    }

    public ArrayList getLogs() {
        try {
            logArray.clear();
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement("select * from public.logs;");
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                LogTable log = new LogTable(
                        rs.getString(1), rs.getString(2) + " " +
                        rs.getString(3), rs.getDouble(4), rs.getString(5),
                        rs.getDouble(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getDouble(11),
                        rs.getDouble(12));
                logArray.add(log);

            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return logArray;
    }

    public void delete(String name){
        try {
            connection = connectDatabase();
            StringTokenizer token = new StringTokenizer(name, ";");
            String log = token.nextToken();
            String dateAndTime = token.nextToken();
            String date = dateAndTime.substring(0, 10);
            String time = dateAndTime.substring(11);
            preparedStatement = connectDatabase().prepareStatement("delete from public.logs where tourname = ? and creationdate = ? and creationtime = ?;");
            preparedStatement.setString(1, log);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, time);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void edit(Log log){
        try {
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement("Update public.logs set totaltime = ?, rating = ?, weather = ?," +
                    "seasonalclosure = ?, transportation = ?, traffic_jam = ?, fuel_used = ?, average_speed = ?" +
                    "where tourname = ?");
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
        }
    }

    //From a given File
    public void store(File file){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList getSearchedTourLogs(String text) {
        try{
            connection = connectDatabase();
            preparedStatement = connectDatabase().prepareStatement("select * from public.logs where tourname like ?;");
            preparedStatement.setString(1, "%"+text+"%");
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                LogTable log = new LogTable(
                        rs.getString(1), rs.getString(2) + " " +
                        rs.getString(3), rs.getDouble(4), rs.getString(5),
                        rs.getDouble(6), rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getDouble(11),
                        rs.getDouble(12));
                logArray.add(log);
            }
            connection.close();
        } catch (SQLException throwables) {
        throwables.printStackTrace();
        }
        return logArray;
    }
}
