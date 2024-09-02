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

public class ExperimentAlgorithmRunner implements Runnable {


    private final File instance;
    private final String instancePath;
    private final Algorithm algorithm;

    private final DataOutput dataOutput;

    public ExperimentAlgorithmRunner(String filePath, Algorithm algorithmToExecute, DataOutput dataOutput) {
        this.instance = new File(filePath);
        this.algorithm = algorithmToExecute;
        this.dataOutput = dataOutput;
        this.instancePath = filePath;
    }

    static Logger logger = Logger.getLogger("Instance");

    @Override
    public void run() {
        logger.log(Level.INFO, "Starting file " + instance.getAbsolutePath() + " with algorithm " + algorithm.getId());
        Graph g = readDataToGraph(instance);

        if (g == null) {
            logger.log(Level.SEVERE, "Error opening the file:" + instance.getAbsolutePath());
        }
        long startTime = System.nanoTime();
        try {
            Solution s = algorithm.run(g);
            float time = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
            //The data to export to CSV after the execution is over
            List<String> dataToExport = new LinkedList<>();
            dataToExport.add(algorithm.getId());
            dataToExport.add(instancePath);
            dataToExport.add(String.valueOf(s.evaluateObjectiveFunction()));
            dataToExport.add(String.valueOf(time));

            dataOutput.exportCSV(dataToExport);
            logger.log(Level.FINE, algorithm.getId() + "::" + instance.getAbsolutePath() + ": Correct end of execution");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during the execution of the algorithm " + algorithm.getId() + " for the file " + instance.getAbsolutePath());
            logger.log(Level.SEVERE, algorithm.getId() + "::" + instance.getAbsolutePath() + e.getMessage());
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
