package DataAccessLayer;

import TourPlanner.Tour;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Database {

    Connection connectDatabase();
    void save(ArrayList list);
    void delete(String name) throws SQLException;
}
