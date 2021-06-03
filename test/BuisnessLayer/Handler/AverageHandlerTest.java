package BuisnessLayer.Handler;

import BusinessLayer.Handler.AverageHandler;
import BusinessLayer.Handler.PdfTableHandler;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class AverageHandlerTest {


    private Document doc = new Document(PageSize.A4);

    private static int counter, rating;
    private static double distance, fuel, speed;
    private static ArrayList time = new ArrayList(), seasonalClos = new ArrayList(), trafficJam = new ArrayList();

    @BeforeAll
    public static void setUp(){
        counter = 5;
        rating = 18;
        distance = 1282.38;
        fuel = 128.2;
        speed = 883.0;
        time.clear();
        time.add("48:32");
        time.add("34:44");
        time.add("1:00:02");
        time.add("55:43");
        time.add("47:34");
    }


    @Test
    public void getAveragesTest_seasTrue_trafFalse(){
        seasonalClos.clear();
        trafficJam.clear();

        seasonalClos.add("t");
        seasonalClos.add("t");
        seasonalClos.add("f");
        seasonalClos.add("t");
        seasonalClos.add("t");

        trafficJam.add("f");
        trafficJam.add("t");
        trafficJam.add("f");
        trafficJam.add("t");
        trafficJam.add("f");

        AverageHandler avg = new AverageHandler(counter, distance, time, rating, seasonalClos, trafficJam, fuel, speed);

        Assertions.assertEquals(String.valueOf(distance/counter), avg.avgDistance());
        Assertions.assertEquals("0:49:19", avg.getAverageTime());
        Assertions.assertEquals(String.valueOf(rating/counter), avg.avgRating());
        Assertions.assertEquals("t", avg.avgSeasClos());
        Assertions.assertEquals("f", avg.avgTrafJam());
        Assertions.assertEquals(String.valueOf(fuel/counter), avg.avgFuel());
        Assertions.assertEquals(String.valueOf(speed/counter), avg.avgSpeed());
    }

    @Test
    public void getAveragesTest_seasFalse_trafTrue() {
        seasonalClos.clear();
        trafficJam.clear();

        seasonalClos.add("f");
        seasonalClos.add("f");
        seasonalClos.add("f");
        seasonalClos.add("t");
        seasonalClos.add("t");

        trafficJam.add("t");
        trafficJam.add("t");
        trafficJam.add("f");
        trafficJam.add("t");
        trafficJam.add("t");

        AverageHandler avg = new AverageHandler(counter, distance, time, rating, seasonalClos, trafficJam, fuel, speed);

        Assertions.assertEquals("f", avg.avgSeasClos());
        Assertions.assertEquals("t", avg.avgTrafJam());
    }
}
