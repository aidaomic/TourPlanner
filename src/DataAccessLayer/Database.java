package DataAccessLayer;

import TourPlanner.Tour;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

public interface Database {

    public Connection connectDatabase();
    public void save(ArrayList list);
    public void store(File file);
    public void delete(String name);
}
