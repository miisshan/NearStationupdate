package edu.eec.db;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.eec.nearmodel.Coordinate;

public class Station {
    private int id;
    private String location;
    private double latitude;
    private double longitude;

    private boolean isStation;


    public Station(int id, String location, double latitude, double longitude , boolean isStation) {
        this.id = id;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isStation = isStation;
    }

    // Getters and setters for the attributes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isStation(){
        return isStation;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(latitude, longitude);
    }

    @Override
    public String toString() {
        return toJson();
    }

    /**
     * To JSON utility
     **/
    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}