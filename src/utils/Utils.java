package utils;

import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class Utils {


    public static boolean isSet(int val, int pos){ return ((val & (1 << pos) ) != 0); }

    public static boolean testMask(int val, int mask, int expected){ return ((val & mask) == expected); }

    public static void sleep(int millis, Runnable runnable){
        Timer t = new Timer(millis, e1 -> {
          runnable.run();
        });
        t.setRepeats(false);
        t.start();
    }

    public static int setPos(int val, int pos){return (val | (1 << pos));}
}
