package DataAccessLayer;

import BusinessLayer.Handler.ImageHandler;
import BusinessLayer.Handler.Properties.PropertyHandlerDatabase;
import BusinessLayer.Logging.LoggingHandler;
import BusinessLayer.Notification.Alert;
import BusinessLayer.Notification.AlertWarning;
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

public class Database_Tours implements Database, DatabaseConnect{

    private Connection connection = null;
    private ResultSet rs = null;
    public PreparedStatement preparedStatement = null;
    private ArrayList nameList = new ArrayList();
    public String tourN, tourS, tourD, tourE, tourDist;
    private BufferedImage mapImage;
    private LoggingHandler log = new LoggingHandler();

    public Connection connectDatabase(){
        try {
            Properties prop = new PropertyHandlerDatabase().getConnectionProperty();
            connection = DriverManager.getConnection(prop.getProperty("connectionURL"), prop.getProperty("connectionUser"), prop.getProperty("connectionPW"));
            log.logDebug("Database Connection established -Database_Tours-");
        } catch (SQLException e) {
            log.logError("SQL Exception while connecting to database -Database_Tours-");
        } catch (IOException e) {
            log.logError("IOException while connecting to database -Database_Tours-");
        }
        return connection;
    }

    public Tour specificTour(String name){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandlerDatabase().getSqlQuery("specificTour"));
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
            log.logInfo("Retrieved specific Tour Data successfully from database -Database_Tours-");
        } catch (SQLException e) {
            log.logError("SQL Exception specific searched Tour Data from database -Database_Tours-");
        } catch (IOException e) {
            log.logError("IO Exception specific searched Tour Data from database -Database_Tours-");
        }
        return new Tour(tourN, tourD, tourS, tourE, tourDist, mapImage);
    }

    public ArrayList getTourNames(){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandlerDatabase().getSqlQuery("allTourNames"));
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                nameList.add(rs.getString(1));
            }

            log.logInfo("Tour Data retrieved successfully from database -Database_Tours-");
        } catch (SQLException e) {
            log.logError("SQL Exception retrieving Tour Data from database -Database_Tours-");
        } catch (IOException e) {
            log.logError("IO Exception retrieving Tour Data from database -Database_Tours-");
        }
        return nameList;
    }

    public void save(ArrayList tour){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandlerDatabase().getSqlQuery("saveTour"));
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
            log.logInfo("Tour saved successfully to database -Database_Tours-");
        } catch (SQLException e) {
            new AlertWarning().duplicatedName();
            log.logError("SQL Exception saving Tour to database -Database_Tours-");
        } catch (IOException e) {
            log.logError("IO Exception saving Tour to database -Database_Tours-");
        }
    }

    public ArrayList getSearched(String searchText) {
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandlerDatabase().getSqlQuery("searchedTours"));
            preparedStatement.setString(1, "%" + searchText + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                nameList.add(rs.getString(1));
            }
            connection.close();
            log.logInfo("Retrieved searched Tour Data successfully from database -Database_Tours-");
        } catch (SQLException e) {
            log.logError("SQL Exception retrieving searched Tour Data from database -Database_Tours-");
        } catch (IOException e) {
            log.logError("IO Exception retrieving searched Tour Data from database -Database_Tours-");
        }
        return nameList;
    }

    //To a File
    public PdfPTable toFileTable(PdfPTable exportTable) {
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandlerDatabase().getSqlQuery("exportTours"));
            rs = preparedStatement.executeQuery();
            while(rs.next()){

                exportTable.addCell(rs.getString(1));
                exportTable.addCell(rs.getString(2));
                exportTable.addCell(rs.getString(3));
                exportTable.addCell(rs.getString(4));
                exportTable.addCell(rs.getString(5));
                exportTable.completeRow();
            }
            log.logInfo("Successfully exporting Tours to TableView -Database_Tours-");
        } catch (SQLException e) {
            log.logError("SQL Exception exporting Tours to TableView -Database_Tours-");
        } catch (IOException e) {
            log.logError("IO Exception exporting Tours to TableView -Database_Tours-");
        }
        return exportTable;
    }

    public String toFile() {
        String output = "";
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandlerDatabase().getSqlQuery("exportTours"));
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                for(int i = 1; i <=5; i++){
                    output = output + rs.getString(i) + ";";
                }
                output += "\n";
            }
            log.logInfo("Successfully exporting Tours to Pdf -Database_Tours-");
        } catch (SQLException e) {
            log.logError("SQL Exception exporting Tours to Pdf -Database_Tours-");
        } catch (IOException e) {
            log.logError("IO Exception exporting Tours to Pdf -Database_Tours-");
        }
        return output;
    }

    @Override
    public void delete(String name) {
        connection = connectDatabase();
        try {
            preparedStatement = connection.prepareStatement(new PropertyHandlerDatabase().getSqlQuery("deleteTour"));
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            connection.close();
            log.logInfo("Tour deleted successfully from database -Database_Tours-");
        } catch (SQLException e) {
            log.logError("SQL Exception deleting Tour from database -Database_Tours-");
        } catch (IOException e) {
            log.logError("IO Exception deleting Tour from database -Database_Tours-");
        }

    }

    public void copy(Tour tour){
        try{
            BufferedImage img;
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandlerDatabase().getSqlQuery("saveTour"));
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
            log.logInfo("Tour copied successfully -Database_Tours-");
        } catch (SQLException e) {
            log.logError("SQL Exception copying Tour -Database_Tours-");
        } catch (IOException e) {
            log.logError("IO Exception copying Tour -Database_Tours-");
        }
    }

}
