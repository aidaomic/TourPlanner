package BuisnessLayer.Handler;

import BusinessLayer.Handler.Averages.CounterHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CounterHandlerTest {

    private ArrayList toCount = new ArrayList();
    private String result = "";

    @Test
    public void counterTrue(){
        toCount.clear();
        toCount.add("t");
        toCount.add("t");
        toCount.add("f");
        toCount.add("t");
        toCount.add("f");
        toCount.add("t");

        result = new CounterHandler().countTrueFalse(toCount);

        Assertions.assertEquals("t", result);
    }

    @Test
    public void counterFalse(){
        toCount.clear();
        toCount.add("t");
        toCount.add("t");
        toCount.add("f");
        toCount.add("f");
        toCount.add("f");

        result = new CounterHandler().countTrueFalse(toCount);

        Assertions.assertEquals("f", result);
    }

    @Test
    public void counterSame(){
        toCount.clear();
        toCount.add("f");
        toCount.add("f");
        toCount.add("f");
        toCount.add("t");
        toCount.add("t");
        toCount.add("t");

        result = new CounterHandler().countTrueFalse(toCount);

        Assertions.assertEquals("t/f", result);
    }
}
