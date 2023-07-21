package edu.eec.nearapp;
import edu.eec.nearcontroller.Algorithm;
import edu.eec.nearcontroller.BellmanFordAlgorithm;
import edu.eec.nearcontroller.Result;
import edu.eec.nearcontroller.Solution;
import edu.eec.nearmodel.*;
import edu.eec.nearutils.RoutingUtils;
import edu.eec.db.Config;
import edu.eec.db.DbFunctions;
import edu.eec.db.Station;
import edu.eec.nearutils.RoutingUtils$;

import java.sql.SQLOutput;
import java.util.*;

public class testApp {
    public static void main(String[] args) {
        DbFunctions db = new DbFunctions(Config.url, Config.userName, Config.password, Config.dbName);
        List<Station> stations = db.fetchStations(db.getConnection(), Config.coordinateTable);
        List<Vertex> vertices = new ArrayList<>();

        for (Station station : stations) {
            int id = station.getId();
            double latitude = station.getLatitude();
            double longitude = station.getLongitude();
            String location = station.getLocation();
            // Putting the values of station into a vertex
            Vertex vertex = new Vertex(id, latitude, longitude, location);
            vertices.add(vertex);
        }

        // Selected Stations near Jawlakhel Area:
        String[] locationStrings = {
                "Jawlakhel",
                "Manbhawan",
                "Kumaripati",
                "Lagankhel",
                "Satdobato",
                "Pulchowk",
                "Jwagal",
                "Sanepa",
                "Mahalaxmi",
                "Thasikhel",
                "Kupondole",
                "Balkhu",
                "Satdobato",
                "Ekantakuna",
                "Dhobighat",
                "Kuleshwor",
                "Khasibazar",
                "Thapathali",
        };

        Vertex[] verticesArray = new Vertex[locationStrings.length];

        // Getting the vertices from the locations
        for (int i = 0; i < locationStrings.length; i++) {
            verticesArray[i] = VertexUtils.getVertexByLocationString(vertices, locationStrings[i]);
        }

        Map<String, Set<String>> neighborsMap = new HashMap<>();

        // Define the neighbors for each vertex
        {
            addNeighbor(neighborsMap, "Jawlakhel", "Manbhawan", "Ekantakuna", "Pulchowk");
            addNeighbor(neighborsMap, "Manbhawan", "Kumaripati");
            addNeighbor(neighborsMap, "Kumaripati", "Lagankhel", "Manbhawan");
            addNeighbor(neighborsMap, "Lagankhel", "Satdobato", "Mahalaxmi", "Kumaripati");
            addNeighbor(neighborsMap, "Satdobato", "Lagankhel", "Mahalaxmi");
            addNeighbor(neighborsMap, "Pulchowk", "Jwagal", "Jawlakhel");
            addNeighbor(neighborsMap, "Jwagal", "Kupondole", "Pulchowk");
            addNeighbor(neighborsMap, "Sanepa", "Balkhu", "Dhobighat");
            addNeighbor(neighborsMap, "Mahalaxmi", "Satdobato", "Lagankhel");
            addNeighbor(neighborsMap, "Thasikhel", "Mahalaxmi", "Ekantakuna");
            addNeighbor(neighborsMap, "Balkhu", "Sanepa", "Kuleshwor", "Khasibazar");
            addNeighbor(neighborsMap, "Kupondole", "Jwagal", "Thapathali");
        }

        // Print the vertices and their immediate neighbors
        System.out.println("Vertex\t\tNeighbors");
        for (String vertex : locationStrings) {
            System.out.print(vertex + "\t\t");
            Set<String> neighbors = neighborsMap.get(vertex);
            if (neighbors != null && !neighbors.isEmpty()) {
                System.out.println(neighbors);
            } else {
                System.out.println("No neighbors");
            }
        }

        NearGraph graph = NearGraph.create();
        System.out.println("Enter your location");
        Scanner rootInput = new Scanner(System.in);
        String root = rootInput.nextLine();

        // Add vertices to the graph
        for (Vertex vertex : verticesArray) {
            graph.addVertex(vertex);
        }

        // Add edges to the graph for each vertex and its neighbors
        for (Vertex vertex : verticesArray) {
            String location = vertex.getLocation();
            Set<String> neighbors = neighborsMap.get(location);
            if (neighbors != null && !neighbors.isEmpty()) {
                for (String neighbor : neighbors) {
                    Vertex neighborVertex = VertexUtils.getVertexByLocationString(vertices, neighbor);
                    if (neighborVertex != null) {
                        double distance = RoutingUtils.distanceInKilometers(
                                vertex.getLat(), vertex.getLon(),
                                neighborVertex.getLat(), neighborVertex.getLon());
                        WeightCalculator weightCalculator = new WeightCalculator(distance);
                        double weight = weightCalculator.getWeightByDistance();
                        Edge edge = Edge.from(
                                vertex.code(),
                                vertex.getLocation() + " to " + neighborVertex.getLocation(),
                                weight,
                                neighborVertex.code()
                        );
                        graph.addEdge(edge);
                    }
                }
            }
        }

        // Set the root vertex
        Vertex rootVertex = VertexUtils.getVertexByLocationString(vertices, root);
        if (rootVertex != null) {
            graph.setRoot(rootVertex);
        } else {
            System.out.println("Invalid root location");
            return;
        }

        // Print the graph
        System.out.println(graph);

        Algorithm algorithm = new BellmanFordAlgorithm();
        Solution solution = algorithm.execute(graph);
        Result result = solution.minimumDistance();
        System.out.println("Result:\n" + result.toString());
    }

    private static void addNeighbor(Map<String, Set<String>> neighborsMap, String vertex, String... neighbors) {
        Set<String> vertexNeighbors = neighborsMap.getOrDefault(vertex, new HashSet<>());
        vertexNeighbors.addAll(Arrays.asList(neighbors));
        neighborsMap.put(vertex, vertexNeighbors);

        for (String neighbor : neighbors) {
            Set<String> neighborNeighbors = neighborsMap.getOrDefault(neighbor, new HashSet<>());
            neighborNeighbors.add(vertex);
            neighborsMap.put(neighbor, neighborNeighbors);
        }
    }
}
