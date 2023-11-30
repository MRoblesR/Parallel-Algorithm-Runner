package io;

import dataStructures.graph.Graph;

import java.io.File;
import java.io.IOException;

public class DataInput {

    /**
     * Generate a Graph object from a file
     *
     * @param instanceFile The File object where the graph is
     * @return A graph object
     */
    public static Graph readDataToGraph(File instanceFile) {
        try {
            //TODO implement the logic to read a file and create a graph
            throw new IOException();
        } catch (IOException e) {//In case the file doesn't exist
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
}
