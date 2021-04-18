package DataAccessLayer;

import BuissnessLayer.MapQuest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Database_ToursTest {

    Database_Tours dbt = new Database_Tours();

    @Test
    public void specificTourTest_Name(){
        dbt.specificTour("TestTour1");
        Assertions.assertEquals(dbt.tourN, "TestTour1");
    }

    @Test
    public void specificTourTest_Description(){
        dbt.specificTour("TestTour1");
        Assertions.assertEquals(dbt.tourD, "Testing first time");
    }

    @Test
    public void specificTourTest_Start(){
        dbt.specificTour("TestTour1");
        Assertions.assertEquals(dbt.tourS, "Wien");
    }

    @Test
    public void specificTourTest_End(){
        dbt.specificTour("TestTour1");
        Assertions.assertEquals(dbt.tourE, "Graz");
    }

    @Test
    public void saveTest(){
        ArrayList list = new ArrayList();
        list.add("TestTour2");
        list.add("Testing second time");
        list.add("Wien");
        list.add("Graz");
        list.add(new MapQuest().getDirections("Wien", "Graz"));
        dbt.save(list);
        Assertions.assertEquals( String.valueOf(dbt.preparedStatement),"insert into public.tours values ('TestTour2','Testing second time','Wien','Graz',?)");
    }

}
