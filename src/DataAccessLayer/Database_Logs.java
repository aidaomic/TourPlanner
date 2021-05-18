package DataAccessLayer;

import BuissnessLayer.Handler.PropertyHandler;
import TourPlanner.Log;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

public class Database_Logs implements Database{

    private Connection connection = null;
    private ResultSet rs = null;
    private PreparedStatement preparedStatement = null;
    private ArrayList logArray;

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

    public void save(Log list){
        try {
            preparedStatement = connection.prepareStatement("insert into public.logs values (?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(list.logName));
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            preparedStatement.setFloat(4, Float.valueOf(String.valueOf(list.distance)));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(list.totalTime));
            preparedStatement.setInt(6, (int) list.rating);
            preparedStatement.setString(7, list.weather);
            preparedStatement.setBoolean(8, Boolean.parseBoolean(list.seasonalClosure));
            preparedStatement.setString(9, list.transportation);
            preparedStatement.setBoolean(10, Boolean.parseBoolean(list.trafficJam));
            preparedStatement.setFloat(11, (float) list.fuelUsed);
            preparedStatement.setFloat(12, (float) list.averageSpeed);
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
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return logArray;
    }

    public void delete(String name){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void edit(String name){
        try {
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
}
