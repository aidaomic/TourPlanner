package DataAccessLayer;

import TourPlanner.Tour;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Database_Tours implements Database{

    private Connection connection = null;
    private ResultSet rs = null;
    private PreparedStatement preparedStatement = null;
    private ArrayList nameList = new ArrayList();
    private String tourN, tourS, tourD, tourE;
    private BufferedImage mapImage;
    private byte [] imgBytes;

    public Connection connectDatabase(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "if20b204");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public Tour specific(String name){
        try {
            preparedStatement = connection.prepareStatement("select * from public.tours where tourname = ?;");
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                tourN = rs.getString(1);
                tourD = rs.getString(2);
                tourS = rs.getString(3);
                tourE = rs.getString(4);
                while (rs.next ()) {
                    imgBytes = rs.getBytes (4);
                }
                mapImage = ImageIO.read(new ByteArrayInputStream(imgBytes));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Tour(tourN, tourD, tourS, tourE, mapImage);
    }

    public ArrayList getTourNames(){
        try {
            connection = connectDatabase();
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

    public void save(ArrayList tour){
        try {

            preparedStatement = connection.prepareStatement("insert into public.tours values (?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(tour.get(1)));
            preparedStatement.setString(2, String.valueOf(tour.get(2)));
            preparedStatement.setString(3,String.valueOf(tour.get(3)));
            preparedStatement.setString(4,String.valueOf(tour.get(4)));
            FileInputStream file = new FileInputStream (String.valueOf(tour.get(5)));
            preparedStatement.setBinaryStream(5, file);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //From a given File
    public void store(File file) {

    }

    @Override
    public void delete(String name) {

    }

}
