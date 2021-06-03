package BuisnessLayer.Handler;

import BusinessLayer.Handler.AverageCalculator;
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


public class AverageCalculatorTest {


    private Document doc = new Document(PageSize.A4);

    private static int counter, rating;
    private static double distance, fuel, speed;
    private static ArrayList time = new ArrayList();
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
    public void getAveragesDistanceTest(){
        AverageCalculator avg = new AverageCalculator(counter);
        Assertions.assertEquals(String.valueOf(distance/counter), avg.avgDouble(distance));
    }

    @Test
    public void getAveragesTimeTest(){
        AverageCalculator avg = new AverageCalculator(counter);
        Assertions.assertEquals("0:49:19", avg.getAverageTime(time));
    }

    @Test
    public void getAveragesRatingTest(){
        AverageCalculator avg = new AverageCalculator(counter);
        Assertions.assertEquals(String.valueOf(rating/counter), avg.avgInt(rating));
    }

    @Test
    public void getAveragesFuelTest(){
        AverageCalculator avg = new AverageCalculator(counter);
        Assertions.assertEquals(String.valueOf(fuel/counter), avg.avgDouble(fuel));
    }

    @Test
    public void getAveragesSpeedTest(){
        AverageCalculator avg = new AverageCalculator(counter);
        Assertions.assertEquals(String.valueOf(speed/counter), avg.avgDouble(speed));
    }
}
