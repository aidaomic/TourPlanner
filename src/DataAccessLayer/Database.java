package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection = null;

    public void connectDatabese(String methode){

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "if20b204");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(methode.equals("getTourData")){
            getTourData();
        } else if(methode.equals("storeTourData")){
            storeTourData();
        } else if(methode.equals("getLogs")){
            getLogs();
        } else if(methode.equals("storeLogs")){
            storeLogs();
        }

    }

    public void getTourData(){

    }

    public void storeTourData(){

    }

    public void getLogs(){

    }

    public void storeLogs(){

    }
}
