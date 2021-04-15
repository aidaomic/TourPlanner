package DataAccessLayer;

import TourPlanner.Tour;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {

    private Connection connection = null;
    private ResultSet rs = null;
    private PreparedStatement preparedStatement = null;
    private ArrayList nameList, logArray;
    private String tourN, tourS, tourD, tourE;

    public void connectDatabese(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "if20b204");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Tour specificTour(String name){
        try {
            preparedStatement = connection.prepareStatement("select * from public.tours where tourname = ?;");
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                tourN = rs.getString(1);
                tourD = rs.getString(2);
                tourS = rs.getString(3);
                tourE = rs.getString(4);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Tour(tourN, tourD, tourS, tourE);
    }

    public ArrayList getTourNames(){
        try {
            preparedStatement = connection.prepareStatement("select tourname from public.tours;");
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                nameList.add(rs.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nameList;
    }

    public void saveTour(String name, String description, String start, String end, float distance){
        try {
            preparedStatement = connection.prepareStatement("insert into public.tours values (?,?,?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3,start);
            preparedStatement.setString(4,end);
            preparedStatement.setFloat(5, distance);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveLog(ArrayList log){
        try {
            preparedStatement = connection.prepareStatement("inserti into public.logs values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(log.get(0)));
            preparedStatement.setDate(2,Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            preparedStatement.setTimestamp(3,Timestamp.valueOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
            preparedStatement.setString(4, String.valueOf(log.get(1)));
            preparedStatement.setFloat(5, Float.valueOf(String.valueOf(log.get(2))));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(String.valueOf(log.get(3))));
            preparedStatement.setInt(7, Integer.valueOf(String.valueOf(log.get(4))));
            preparedStatement.setFloat(8, Float.valueOf(String.valueOf(log.get(5))));
            preparedStatement.setBoolean(9, Boolean.valueOf(String.valueOf(log.get(6))));
            preparedStatement.setBoolean(10, Boolean.valueOf(String.valueOf(log.get(7))));
            preparedStatement.setBoolean(11, Boolean.valueOf(String.valueOf(log.get(8))));
            preparedStatement.setBoolean(12, Boolean.valueOf(String.valueOf(log.get(9))));
            preparedStatement.setBoolean(13, Boolean.valueOf(String.valueOf(log.get(10))));
            preparedStatement.setBoolean(14, Boolean.valueOf(String.valueOf(log.get(11))));
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Log specificLog(){

        return new Log();
    }

    public ArrayList getLogs() {

        return logArray;
    }

    //From a given File
    public void storeTourData() {

    }
    //From a given File
    public void storeLogs(){

    }
}
