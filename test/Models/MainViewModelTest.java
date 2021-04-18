package Models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainViewModelTest {

    public MainViewModel mvm = new MainViewModel();

    //will be changed later on
    @Test
    public void showTourTest(){
        String ergebnis = "Description:\nTesting first time\n\nStart: Wien\nZiel: Graz";
        mvm.showTour("TestTour1");
        Assertions.assertEquals(mvm.output, ergebnis);
    }

    @Test
    public void searchForTourTest(){
        mvm.searchForTour();
        //Assertions.assertEquals(mvm.output, "You searched for !");
    }

    @Test
    public void getHelpTest(){

    }

    @Test
    public void doEdit(){

    }

    @Test
    public void addTOurTest(){

    }

    @Test
    public void deleteTourTest(){}

}
