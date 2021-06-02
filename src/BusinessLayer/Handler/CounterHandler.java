package BusinessLayer.Handler;

import java.util.ArrayList;

public class CounterHandler {
    private int yes, no, rainy, sunny;

    public String countTrueFalse(ArrayList data){
        yes = 0;
        no = 0;
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).equals("t"))
                yes++;
            else
                no++;
        }
        if (yes>no)
            return "t";
        if (yes==no)
            return "t/f";
        return "f";
    }

}
