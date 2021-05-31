package DataAccessLayer;

import BusinessLayer.MapQuest.MapQuest;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Database_LogsTest {

    private Database_Logs dbtl = new Database_Logs();
    private Database_Tours dbt = new Database_Tours();
    private String name = "Test Tour for Unit Test";

    @Test
    @Order(1)
    public void createNewTour_saveTourToDB(){
        ArrayList list = new ArrayList();
        list.add(name);
        list.add("Unit Test for Database");
        list.add("Wien");
        list.add("Graz");
        list.add(108.289);
        list.add(new MapQuest().getDirections("Wien", "Graz"));
        dbt.save(list);
        Assertions.assertEquals( String.valueOf(dbt.preparedStatement),"insert into public.tours values ('Test Tour for Unit Test','Unit Test for Database','Wien','Graz',108.289,?)");
    }

    //could fail bc of timestamp
    @Test@Order(2)
    public void createLog_saveLogToDB(){
        ArrayList tourLog = new ArrayList();
        tourLog.add(name);
        tourLog.add(108.289);
        tourLog.add("3:20");
        tourLog.add(3);
        tourLog.add("rainy");
        tourLog.add(false);
        tourLog.add("car");
        tourLog.add(true);
        tourLog.add(83.21);
        tourLog.add(30);
        String query = "insert into public.logs (tourname, creationdate, creationtime, distance, totaltime, rating, weather, seasonalclosure, transportation, traffic_jam, fuel_used, average_speed) values " +
                "('Test Tour for Unit Test','" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "','" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
                "',108.289,'3:20',3.0,'rainy','FALSE','car','TRUE',83.21,30.0)";

        dbtl.save(tourLog);
        Assertions.assertEquals(String.valueOf(dbtl.preparedStatement), query);
    }

    @Test@Order(3)
    public void deleteTourLog_deleteFromDatabase(){
        dbtl.deleteAllLogs(name);
        Assertions.assertEquals(dbtl.getSearchedTourLogs(name).size(), 0);
    }

    @Test
    @Order(4)
    public void userDeletesTour_deleteTourFromDatabase(){
        dbt.delete(name);
        Assertions.assertNotEquals(dbt.specificTour(name), name);
    }
}
