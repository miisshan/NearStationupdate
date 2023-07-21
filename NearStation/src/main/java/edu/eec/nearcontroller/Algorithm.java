package edu.eec.nearcontroller;

import edu.eec.nearmodel.NearGraph;

public interface Algorithm {

    /**
     * Perform the execution algorithmic operation on the given near graph.
     *
     * @param nearGraph, a graph represented from the near station locations.
     * @return solutionOutput
     */
    public Solution execute(NearGraph nearGraph);

}