package Models;

import BuissnessLayer.MapQuest;
import DataAccessLayer.Database_Tours;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TourViewModelTest {

    Database_Tours dbt = new Database_Tours();

    @Test
    public void addTourTest(){
        ArrayList list = new ArrayList();
        list.add("TestTours_5");
        list.add("Testing first time");
        list.add("Wien");
        list.add("Graz");
        list.add(new MapQuest().getDirections("Wien", "Graz"));
        dbt.save(list);
        Assertions.assertEquals( String.valueOf(dbt.preparedStatement),"insert into public.tours values ('TestTours_5','Testing first time','Wien','Graz',?)");
    }

    @Test
    public void addTourTest2(){
        ArrayList list = new ArrayList();
        list.add("TestTours_5");
        list.add("Testing first time");
        list.add("Zuerich");
        list.add("Graz");
        list.add(new MapQuest().getDirections("Wien", "Graz"));
        dbt.save(list);
        Assertions.assertEquals( String.valueOf(dbt.preparedStatement),"insert into public.tours values ('TestTours_5','Testing first time','Zuerich','Graz',?)");
    }


}
