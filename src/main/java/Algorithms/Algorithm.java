package Algorithms;

import dataStructures.graph.Graph;
import dataStructures.solution.Solution;

/**
 * This interface must be implemented for all the methods you want to execute
 * TODO check your methods implement this interface, modify as needed.
 */
public interface Algorithm {
    /**
     * Run the method
     *
     * @return the resulting Solution generated
     */
    Solution run(Graph graph);

    /**
     * Run the method with a given timeLimit
     *
     * @param graph     The graph
     * @param timeLimit The timeLimit
     * @return The resulting Solution generated
     */
    Solution run(Graph graph,Long timeLimit);

    String getId();

    void setId(String id);
}
