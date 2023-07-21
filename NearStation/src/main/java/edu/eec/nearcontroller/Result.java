package edu.eec.nearcontroller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.eec.nearmodel.Edge;
import edu.eec.nearmodel.Vertex;
import edu.eec.nearutils.Conversion;

import java.util.List;

public class Result {

    /**
     * Source vertex.
     */
     final Vertex source;

     final List<Edge> edge;

     final Vertex destination;

    /**
     * Default constructor.
     */
    public Result(Vertex source, List<Edge> edge, Vertex destination) {
        this.source = source;
        this.edge = edge;
        this.destination = destination;
    }



    /**
     * To Json representation.
     */
    @Override
    public String toString() {
        return Conversion.toJson(this);
    }

    public Vertex getDestination() {
        return this.destination;
    }

    public void printEdge() {
        System.out.println(this.edge);
    }
    public Boolean addEdge(Edge edge){
        return this.edge.add(edge);
    }

    /**
     * Empty Result.
     */
    public static Result empty() {
        return new Result(Vertex.empty(), List.of(Edge.empty()), Vertex.empty());
    }
}