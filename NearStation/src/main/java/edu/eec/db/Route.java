package edu.eec.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

//Work in progress

public class Route {
    private int id;
    private String routename;
    private String start;
    private String end;

    private String[] routeList;

    public Route(int id, String route, String start, String end, String[] routeList) {
        this.id = id;
        this.routename = route;
        this.start = start;
        this.end = end;
        this.routeList = routeList;
    }

    // Getters and setters for the attributes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

    public String getStart() {
        return start;
    }

    public void setStart() {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String[] getRouteList() {
        return routeList;
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