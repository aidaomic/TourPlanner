package DataAccessLayer;

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
                mapImage = ImageIO.read(new ByteArrayInputStream(rs.getBytes (5)));
                //for showing the picture
                //prüfen ob schon existent
                ImageIO.write(mapImage, "jpg", new File(tourN+".jpg"));
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
            ImageIO.write((RenderedImage) tour.get(4), "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            preparedStatement.setBinaryStream(5, is);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //From a given File
    public void store(File file) {

    }

    @Override
    public void delete(String name) {

    }

    public void edit(ArrayList list){
        try {
            connection = connectDatabase();

            preparedStatement = connection.prepareStatement("update public.tours set tourname = ?, description = ?, " +
                    "startpoint = ?, endpoint = ? where tourname = ?");
            preparedStatement.setString(1,String.valueOf(list.get(0)));
            preparedStatement.setString(2,String.valueOf(list.get(1)));
            preparedStatement.setString(3,String.valueOf(list.get(2)));
            preparedStatement.setString(4,String.valueOf(list.get(3)));
            preparedStatement.setString(5,String.valueOf(list.get(4)));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editNewRoute(ArrayList list) {
        try {
            connection = connectDatabase();

            preparedStatement = connection.prepareStatement("update public.tours set tourname = ?, description = ?, " +
                    "startpoint = ?, endpoint = ?, image = ? where tourname = ?");
            preparedStatement.setString(1,String.valueOf(list.get(0)));
            preparedStatement.setString(2,String.valueOf(list.get(1)));
            preparedStatement.setString(3,String.valueOf(list.get(2)));
            preparedStatement.setString(4,String.valueOf(list.get(3)));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage) list.get(4), "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            preparedStatement.setBinaryStream(5, is);
            preparedStatement.setString(6,String.valueOf(list.get(5)));
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
