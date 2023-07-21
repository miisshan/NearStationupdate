package edu.eec.nearmodel;
import edu.eec.nearutils.RoutingUtils;
import java.lang.Math;
public class WeightCalculator {
    private static double bus_fare;
    public static double weight;

    public static double distance;
//    public static double waitingtime;

    public WeightCalculator(double distance) {

        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        this.distance = distance;
        if (distance > 0 && distance < 5) {
            bus_fare = 19;
        } else if (distance >= 5 && distance < 10) {
            bus_fare = 25;
        } else if (distance >= 10 && distance < 15) {
            bus_fare = 30;
        } else if (distance >= 15 && distance < 20) {
            bus_fare = 33;
        } else if (distance >= 20 && distance < 25) {
            bus_fare = 38;
        } else {
            bus_fare = distance * 2;
        }

    }

    public double getWeightByFare() {

        weight = bus_fare * distance;

        return weight;
    }
//    public double getWeightByWaitingTime() {
//        weight = waitingtime;
//        return weight;
//    }
// Take time and frequency of bus in the station
// Put the time of the day, frequency column in coordinates table in a keyvalue pair
    public double getWeightByDistance() {
        weight = distance;
        return weight;
    }

}