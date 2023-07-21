package edu.eec.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbFunctions {

    /**
     * Connection parameters.
     */
    private final String url;
    private final String userName;
    private final String password;
    private final String dbName;

    public DbFunctions(String url, String userName, String password, String dbName) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.dbName = dbName;
    }

    public Connection getConnection() {
        //Use Option for better handling of Nulls.
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(this.url + this.dbName, this.userName, this.password);
            if (conn != null) {
                System.out.println("Connection established successfully");
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public static List < Station > fetchStations(Connection conn, String table_name) {
        List < Station > stations = new ArrayList < > ();

        try {
            String query = String.format("SELECT * FROM %s", table_name);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String location = rs.getString("Location");
                double latitude = rs.getDouble("Latitude");
                double longitude = rs.getDouble("Longitude");
                boolean isStation = rs.getBoolean("isStation");
                Station station = new Station(id, location, latitude, longitude,isStation);
                stations.add(station);
            }

            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return stations;
    }

    //Fetch from Route database
    public static List < Route > fetchRoutes(Connection conn, String table_name){
        List <Route> routes = new ArrayList<>();

        try{
            String query = String.format("SELECT * from %s",table_name);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()){
                int id = rs.getInt("id");
                String routename = rs.getString("Routename");
                String start = rs.getString("Start");
                String end = rs.getString("End");
                //First passing as string, converting it later to array.
                String routelistString= rs.getString("Routelist");
                String[] routelist = routelistString.split(",");
                Route route = new Route(id,routename,start,end,routelist);
                routes.add(route);
            }
            rs.close();
            statement.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return routes;
    }
}