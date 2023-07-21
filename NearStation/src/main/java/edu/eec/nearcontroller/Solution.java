package edu.eec.nearcontroller;

import edu.eec.nearapp.VertexUtils;
import edu.eec.nearmodel.Edge;
import edu.eec.nearmodel.NearGraph;
import edu.eec.nearmodel.Vertex;
import edu.eec.nearmodel.WeightCalculator;
import edu.eec.nearutils.Conversion;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    private boolean negativeCycle;

    private Map < String, Double > distances;

    private NearGraph graph;

    public Solution(Map < String, Double > distances, NearGraph graph, boolean negativeCycle) {
        this.distances = distances;
        this.graph = graph;
        this.negativeCycle = negativeCycle;
    }

    /**
     * Check if the solution has negative cycle.
     */
    public boolean hasNegativeCycle() {
        return this.negativeCycle;
    }

    public Map < String, Double > getDistances() {
        return this.distances;
    }

    public List < Edge > listOfEdge = new ArrayList < > ();

    public Boolean isMainRootSet = false;

    /**
     * Returns the minimum distance.
     */
    public Result minimumDistance() {
        //We consider root to not have a staion because if there is a station user wouldn't be using the application
        this.distances.remove(graph.getRoot().code());
        if (!isMainRootSet) {
            this.graph.setMainRoot(this.graph.getRoot());
            isMainRootSet = true;
        }
//        System.out.println("You are here" + this.graph.getMainRoot());
        String label = Collections.min(distances.entrySet(), Map.Entry.comparingByValue()).getKey();
        //        System.out.println("label1:" + label);
        //        System.out.println("source:" + graph.getRoot().getLabel());
        Optional < Double > minWeight = graph.edges().stream()
                .filter(e -> e.getSource().equals(graph.getRoot().getLabel()) && e.getDestination().equals(label))
                .map(e -> e.getWeight()).min(Double::compareTo);
        //        System.out.println("minimumWeight:"+ minWeight.get().toString());
        Optional < Edge > edge = graph.edges().stream().
                filter(e -> e.getDestination().equals(label) && e.getSource().equals(graph.getRoot().getLabel()) && e.getWeight() == minWeight.get()).findAny();
        Optional < Vertex > destination = graph.vertexByLabel(edge.get().getDestination());

        Optional < Vertex > target = graph.vertexByLabel(label);

        boolean isSolutionAvailable = edge.isPresent() && target.isPresent() && !hasNegativeCycle();
        if (isSolutionAvailable) {
            //            Result result = new Result(graph.getMainRoot(), listOfEdge, target.get());
            if (!destination.get().getIsStation()) {
                listOfEdge.add(edge.get());
                this.graph.setRoot(destination.get());
                this.minimumDistance();

            } else {
                listOfEdge.add(edge.get());
                if (listOfEdge.isEmpty()) {
                    return Result.empty();
                }
                System.out.println("You are here" + this.graph.getMainRoot());
                System.out.println("Result:");
                Result result = new Result(graph.getMainRoot(), listOfEdge, target.get());
                System.out.println("Source" + result.source);
                System.out.println("edges" + result.edge);
                System.out.println("destination" + result.destination);
                //                return new Result(graph.getMainRoot(), listOfEdge, target.get());
            }
        }
        return Result.empty();
    }

    /**
     * Factory.
     */
    public static Solution from(Map < String, Double > distances, NearGraph graph, boolean negativeCycle) {
        return new Solution(distances, graph, negativeCycle);
    }

    public static Solution empty() {
        return new Solution(new HashMap < > (), NearGraph.empty(), false);
    }

    @Override
    public String toString() {
        return Conversion.toJson(this);
    }
}