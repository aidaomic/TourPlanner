package DataAccessLayer;

import BuissnessLayer.ImageHandler;
import BuissnessLayer.PropertyHandler;
import TourPlanner.Tour;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Database_Tours implements Database{

    private Connection connection = null;
    private ResultSet rs = null;
    public PreparedStatement preparedStatement = null;
    private ArrayList nameList = new ArrayList();
    public String tourN, tourS, tourD, tourE, tourDist;
    private BufferedImage mapImage;

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

    public Tour specificTour(String name){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("specificTour"));
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                tourN = rs.getString(1);
                tourD = rs.getString(2);
                tourS = rs.getString(3);
                tourE = rs.getString(4);
                tourDist = rs.getString(5);
                mapImage = ImageIO.read( new ByteArrayInputStream(rs.getBytes (6)));
                new ImageHandler().storeOnFS(tourN, mapImage);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Tour(tourN, tourD, tourS, tourE, tourDist, mapImage);
    }

    public ArrayList getTourNames(){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("allTourNames"));
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                nameList.add(rs.getString(1));
            }

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        return nameList;
    }

    public void save(ArrayList tour){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("saveTour"));
            preparedStatement.setString(1, String.valueOf(tour.get(0)));
            preparedStatement.setString(2, String.valueOf(tour.get(1)));
            preparedStatement.setString(3,String.valueOf(tour.get(2)));
            preparedStatement.setString(4,String.valueOf(tour.get(3)));
            preparedStatement.setDouble(5, (Double) tour.get(4));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write((BufferedImage) tour.get(5), "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            preparedStatement.setBinaryStream(6, is);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getSearchedTours(String searchText) {
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("searchedTours"));
            preparedStatement.setString(1, "%" + searchText + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                nameList.add(rs.getString(1));
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameList;
    }

    //From a given File
    public void store(File file) {

    }

    @Override
    public void delete(String name) {
        connection = connectDatabase();
        try {
            preparedStatement = connection.prepareStatement(new PropertyHandler().getConnectionProperty().getProperty("deleteTour"));
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
