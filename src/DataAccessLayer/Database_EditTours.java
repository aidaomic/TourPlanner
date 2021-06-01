package DataAccessLayer;

import BusinessLayer.Handler.PropertyHandler;
import BusinessLayer.Logging.LoggingHandler;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Database_EditTours {

    private Connection connection = null;
    public PreparedStatement preparedStatement = null;
    private ArrayList nameList = new ArrayList();
    private LoggingHandler log = new LoggingHandler();

    public Connection connectDatabase(){
        try {
            Properties prop = new PropertyHandler().getConnectionProperty();
            connection = DriverManager.getConnection(prop.getProperty("connectionURL"), prop.getProperty("connectionUser"), prop.getProperty("connectionPW"));
            log.logDebug("Database Connection established -Database_EditTours-");
        } catch (SQLException e) {
            log.logError("SQL Exception while connecting to database -Database_EditTours-");
        } catch (IOException e) {
            log.logError("IOException while connecting to database -Database_EditTours-");
        }
        return connection;
    }

    public void edit(ArrayList list){
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("editTour"));
            preparedStatement.setString(1,String.valueOf(list.get(0)));
            preparedStatement.setString(2,String.valueOf(list.get(1)));
            preparedStatement.setString(3,String.valueOf(list.get(2)));
            preparedStatement.setString(4,String.valueOf(list.get(3)));
            preparedStatement.setString(5,String.valueOf(list.get(4)));
            preparedStatement.execute();
            connection.close();
            log.logInfo("Tour editing saved to database successfully -Database_EditTours-");
        } catch (SQLException throwables) {
            log.logError("SQL Exception while editing Tour -Database_EditTours-");
        } catch (IOException e) {
            log.logError("IOException while editing Tour -Database_EditTours-");
        }
    }

    public void editNewRoute(ArrayList list) {
        try {
            connection = connectDatabase();
            preparedStatement = connection.prepareStatement(new PropertyHandler().getSqlQuery("editTourRoute"));
            preparedStatement.setString(1,String.valueOf(list.get(0)));
            preparedStatement.setString(2,String.valueOf(list.get(1)));
            preparedStatement.setString(3,String.valueOf(list.get(2)));
            preparedStatement.setString(4,String.valueOf(list.get(3)));
            preparedStatement.setDouble(5, (Double) list.get(4));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage) list.get(5), "jpg", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            preparedStatement.setBinaryStream(6, is);
            preparedStatement.setString(7,String.valueOf(list.get(6)));
            preparedStatement.execute();
            connection.close();
            log.logInfo("Tour editing saved to database successfully -Database_EditTours-");
        } catch (SQLException throwables) {
            log.logError("SQL Exception while editing Tour -Database_EditTours-");
        } catch (IOException e) {
            log.logError("IOException while editing Tour -Database_EditTours-");
        }
    }
}
