package DataAccessLayer;

import BusinessLayer.MapQuest.MapQuest;
import TourPlanner.Tour;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Database_EditTours_Test {

    private Database_Tours dbt = new Database_Tours();
    private Database_EditTours dbte = new Database_EditTours();

    @Test
    @Order(1)
    public void createNewTour_saveTourToDB(){
        ArrayList list = new ArrayList();
        list.add("Test Tour for Unit Test");
        list.add("Unit Test for Database");
        list.add("Wien");
        list.add("Graz");
        list.add(108.289);
        list.add(new MapQuest().getDirections("Wien", "Graz"));
        dbt.save(list);
        Assertions.assertEquals( String.valueOf(dbt.preparedStatement),"insert into public.tours values ('Test Tour for Unit Test','Unit Test for Database','Wien','Graz',108.289,?)");
    }

    @Test
    @Order(2)
    public void userEditsTour_saveEditedChanges(){
        ArrayList list = new ArrayList();
        list.add("Test Tour for Unit Test");
        list.add("Unit Test");
        list.add("Wien");
        list.add("Graz");
        list.add("Test Tour for Unit Test");
        dbte.edit(list);
        Assertions.assertEquals(String.valueOf(dbte.preparedStatement), "update public.tours set tourname = 'Test Tour for Unit Test', description = 'Unit Test', startpoint = 'Wien', endpoint = 'Graz' where tourname = 'Test Tour for Unit Test'");
    }

    @Test
    @Order(3)
    public void tourEdited_CheckChanges(){
        String name = "Test Tour for Unit Test";
        Tour t = dbt.specificTour(name);
        Assertions.assertEquals(t.tourName, "Test Tour for Unit Test");
        Assertions.assertEquals(t.tourDescription, "Unit Test");
    }

    @Test
    @Order(4)
    public void userDeletesTour_deleteTourFromDatabase(){
        String name = "Test Tour for Unit Test";
        dbt.delete(name);
        Assertions.assertNotEquals(dbt.specificTour(name), name);
    }
}
