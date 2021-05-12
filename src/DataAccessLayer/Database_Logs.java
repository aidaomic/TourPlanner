package DataAccessLayer;

import BuissnessLayer.Handler.PropertyHandler;

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

    public void save(ArrayList list){
        try {
            preparedStatement = connection.prepareStatement("inserti into public.logs values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(list.get(0)));
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            preparedStatement.setString(4, String.valueOf(list.get(1)));
            preparedStatement.setFloat(5, Float.valueOf(String.valueOf(list.get(2))));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(String.valueOf(list.get(3))));
            preparedStatement.setInt(7, Integer.valueOf(String.valueOf(list.get(4))));
            preparedStatement.setFloat(8, Float.valueOf(String.valueOf(list.get(5))));
            preparedStatement.setBoolean(9, Boolean.valueOf(String.valueOf(list.get(6))));
            preparedStatement.setBoolean(10, Boolean.valueOf(String.valueOf(list.get(7))));
            preparedStatement.setBoolean(11, Boolean.valueOf(String.valueOf(list.get(8))));
            preparedStatement.setBoolean(12, Boolean.valueOf(String.valueOf(list.get(9))));
            preparedStatement.setBoolean(13, Boolean.valueOf(String.valueOf(list.get(10))));
            preparedStatement.setBoolean(14, Boolean.valueOf(String.valueOf(list.get(11))));
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
