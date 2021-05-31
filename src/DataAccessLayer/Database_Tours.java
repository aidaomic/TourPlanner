package DataAccessLayer;

import BusinessLayer.Handler.ImageHandler;
import BusinessLayer.Handler.PropertyHandler;
import TourPlanner.Tour;
import com.itextpdf.text.pdf.PdfPTable;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("specificTour"));
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
            connection.close();
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
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("allTourNames"));
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
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("saveTour"));
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
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getSearchedTours(String searchText) {
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("searchedTours"));
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

    //To a File
    public PdfPTable toFileTable(PdfPTable exportTable) {
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("exportTours"));
            rs = preparedStatement.executeQuery();
            while(rs.next()){

                exportTable.addCell(rs.getString(1));
                exportTable.addCell(rs.getString(2));
                exportTable.addCell(rs.getString(3));
                exportTable.addCell(rs.getString(4));
                exportTable.addCell(rs.getString(5));
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
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("exportTours"));
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                for(int i = 1; i <=5; i++){
                    output = output + rs.getString(i) + ";";
                }
                output += "\n";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public void delete(String name) {
        connection = connectDatabase();
        try {
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("deleteTour"));
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void copy(Tour tour){
        try{
            BufferedImage img;
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("saveTour"));
            preparedStatement.setString(1, String.valueOf(tour.tourName+"_COPY_"+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())));
            preparedStatement.setString(2, String.valueOf(tour.tourDescription));
            preparedStatement.setString(3,String.valueOf(tour.tourSart));
            preparedStatement.setString(4,String.valueOf(tour.tourEnd));
            preparedStatement.setDouble(5, Double.parseDouble(tour.tourDistance));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            if(tour.map == null)
                img = ImageIO.read(new File(tour.tourName+".jpg"));
            else
                img =  SwingFXUtils.fromFXImage(tour.map, null);
            ImageIO.write(img, "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            preparedStatement.setBinaryStream(6, is);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

}
