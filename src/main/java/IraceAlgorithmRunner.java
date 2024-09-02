import Algorithms.Algorithm;
import dataStructures.graph.Graph;
import dataStructures.solution.Solution;
import io.DataOutput;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.DataInput.readDataToGraph;

public class IraceAlgorithmRunner implements Runnable {


    private final File instance;
    private final Algorithm algorithm;


    public IraceAlgorithmRunner(String filePath, Algorithm algorithmToExecute) {
        this.instance = new File(filePath);
        this.algorithm = algorithmToExecute;
    }

    static Logger logger = Logger.getLogger("Instance");

    @Override
    public void run() {
        logger.log(Level.INFO, "Starting file " + instance.getAbsolutePath() + " with algorithm " + algorithm.getId());
        Graph g = readDataToGraph(instance);

        if (g == null) {
            logger.log(Level.SEVERE, "Error opening the file:" + instance.getAbsolutePath());
        }
        try {
            Solution s = algorithm.run(g);
            logger.log(Level.FINE, algorithm.getId() + "::" + instance.getAbsolutePath() + ": Correct end of execution");
            System.out.println(s.evaluateObjectiveFunction()); //This is the input irace would take for taking decisions

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during the execution of the algorithm " + algorithm.getId() + " for the file " + instance.getAbsolutePath());
            logger.log(Level.SEVERE, algorithm.getId() + "::" + instance.getAbsolutePath() + e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
