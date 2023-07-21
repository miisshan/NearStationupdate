package edu.eec.nearapp;
import edu.eec.nearmodel.Edge;
import edu.eec.nearmodel.NearGraph;
import edu.eec.nearmodel.Vertex;

public class GraphBuilder {
    private Vertex v1;
    private Vertex v2;
    private Vertex root;
    private Edge e;

    public GraphBuilder(Vertex v1, Vertex v2, Edge e, Vertex root) {
        this.v1 = v1;
        this.v2 = v2;
        this.e = e;
        this.root = root;
    }

    public void buildGraph() {
        NearGraph nearGraph = NearGraph.create()
                .addVertex(v1)
                .addVertex(v2)
                .addEdge(e)
                .setRoot(root);
        System.out.println("Graph: " + nearGraph.toString());
    }
}