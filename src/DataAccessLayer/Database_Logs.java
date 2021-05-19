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
