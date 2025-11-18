package utils;

public class Utils {


    public static boolean isSet(int val, int pos){ return ((val & (1 << pos) ) != 0); }

    public static boolean testMask(int val, int mask, int expected){ return ((val & mask) == expected); }

    public static int max(int a, int b) {return a > b ? a : b;}
}
