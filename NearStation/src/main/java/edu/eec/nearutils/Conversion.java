package edu.eec.nearutils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Conversion {
    
    /**
     * Unit mile for unit time, in Minutes.
     *
     * @return timeTakenForUnitMile
     */
    public static double unitTimeMile() {
        return ((double) Constants.IN_MINUTES / (double) Constants.MILES_PER_HOUR);
    }
    
    /**
     * Calculation for the time taken between two nodes, the approximated miles per hour is used.
     *
     * @param distance, in miles.
     * @return timeInMinutes
     */
    public static int timeTakenInMinutes(int distance) {
        return (int) (distance * unitTimeMile());
    }
    
    
    /**
     * Json Writer Utility.
     */
    public static String toJson(Object data) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);
    }
    
}
