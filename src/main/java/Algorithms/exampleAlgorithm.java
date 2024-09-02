package Algorithms;

import dataStructures.graph.Graph;
import dataStructures.solution.Solution;

/**
 * This is an example of an algorithm structure, you can remove it
 */
public class exampleAlgorithm implements Algorithm {
    private String id;

    @Override
    public Solution run(Graph graph) {
        return run(graph,Long.MAX_VALUE);
    }

    @Override
    public Solution run(Graph graph,Long timeLimit) {
        //You should mplement your algorithm here
        return null;
    }

    @Override
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }
}
