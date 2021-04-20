package DataAccessLayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class Database_EditTours {

    private Connection connection = null;
    public PreparedStatement preparedStatement = null;
    private ArrayList nameList = new ArrayList();

    public Connection connectDatabase(){
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "if20b204");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
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
