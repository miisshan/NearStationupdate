package edu.eec.nearmodel;

import edu.eec.nearutils.Conversion;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SerializedGraph {

    private final Vertex root;
    private final Set < Vertex > vertexSet;
    private final Set < Edge > edgeSet;

    public SerializedGraph(Vertex root, Set < Vertex > vertexSet, Set < Edge > edgeSet) {
        this.root = root;
        this.vertexSet = vertexSet;
        this.edgeSet = edgeSet;
    }

    public Set < Vertex > getVertexSet() {
        return vertexSet;
    }

    public Set < Edge > getEdgeSet() {
        return edgeSet;
    }

    /**
     * Json Conversion Utility.
     */
    @Override
    public String toString() {
        return Conversion.toJson(this);
    }

    /**
     * Factory for the Graph generated from map data.
     */
    public static SerializedGraph from(Vertex root, Map < Vertex, Set < Edge >> nearGraph) {
        return new SerializedGraph(
                root,
                nearGraph.keySet(),
                nearGraph.values().stream().flatMap(Set::stream).collect(Collectors.toSet())
        );
    }
}