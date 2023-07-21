package edu.eec.nearapp;

import edu.eec.nearcontroller.Result;
import edu.eec.nearmodel.Vertex;

import java.util.List;

public class VertexUtils {
    public static Vertex getVertexByLocationString(List < Vertex > vertices, String location) {
        for (Vertex vertex: vertices) {
            if (vertex.getLocation().equalsIgnoreCase(location)) {
                return vertex;
            }
        }
        return null; // Handle if vertex with the given location is not found
    }

    public static Vertex getVertexByLabel(List < Vertex > vertices, String label) {
        for (Vertex vertex: vertices) {
            if (vertex.getLabel().equalsIgnoreCase(label)) {
                return vertex;
            }
        }
        return null; // Handle if vertex with the given location is not found
    }
}