package DataAccessLayer;

import BuissnessLayer.ImageHandler;
import TourPlanner.Tour;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Database_Tours implements Database{

    private Connection connection = null;
    private ResultSet rs = null;
    public PreparedStatement preparedStatement = null;
    private ArrayList nameList = new ArrayList();
    public String tourN, tourS, tourD, tourE;
    private BufferedImage mapImage;

    public Connection connectDatabase(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "if20b204");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public Tour specificTour(String name){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement("select * from public.tours where tourname = ?;");
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                tourN = rs.getString(1);
                tourD = rs.getString(2);
                tourS = rs.getString(3);
                tourE = rs.getString(4);
                mapImage = ImageIO.read( new ByteArrayInputStream(rs.getBytes (5)));
                new ImageHandler().storeOnFS(tourN, mapImage);
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
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement("insert into public.tours values (?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(tour.get(0)));
            preparedStatement.setString(2, String.valueOf(tour.get(1)));
            preparedStatement.setString(3,String.valueOf(tour.get(2)));
            preparedStatement.setString(4,String.valueOf(tour.get(3)));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write((BufferedImage) tour.get(4), "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            preparedStatement.setBinaryStream(5, is);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getSearchedTours(String searchText) throws SQLException {
        connection = connectDatabase();
        preparedStatement = connection.prepareStatement("select tourname from public.tours where tourname like ?");
        preparedStatement.setString(1, "%"+searchText+"%");
        rs = preparedStatement.executeQuery();
        while (rs.next()){
            nameList.add(rs.getString(1));
        }
        connection.close();
        return nameList;
    }

    //From a given File
    public void store(File file) {

    }

    @Override
    public void delete(String name) throws SQLException {
        connection = connectDatabase();
        preparedStatement = connectDatabase().prepareStatement("delete from public.tours where tourname = ?");
        preparedStatement.setString(1, name);
        preparedStatement.execute();
        connection.close();
    }


}
