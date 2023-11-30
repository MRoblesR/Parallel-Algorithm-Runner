package Algorithms;

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
    Solution run();

    /**
     * Run the method with a given timeLimit
     *
     * @param timeLimit The timeLimit
     * @return The resulting Solution generated
     */
    Solution run(Long timeLimit);

    String getId();

    void setId(String id);
}
