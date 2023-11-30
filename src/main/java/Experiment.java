import Algorithms.Algorithm;
import Algorithms.exampleAlgorithm;
import io.DataOutput;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This is the start point for regular experiments
 * You must configure:
 * - Number of cores: the amount or percent of cores that the experiment is going to use, running one instance/algorithm on each
 * - Folder: the folder that contains the instances that are going to be used for the experiment
 * - Algorithm: the algorithm that is going to be executed
 * - Logger level: the messages that are going to be output, by default warning, error, severe...
 * It is your decision how to configure those, you can read them from command line, from a file, or just hardcode them in this class or
 * in a specific method.
 *
 * TODO modify as needed
 */
public class Experiment {
    static Logger logger = Logger.getLogger("Experiment");

    // select the minimum between a given number and a percentage ot the total cores
    static int cores = Math.min(1, 50 * Runtime.getRuntime().availableProcessors() / 100);

    static ExecutorService ex = Executors.newFixedThreadPool(cores);

    public static void main(String[] args) {
        String folder;
        Logger rootLogger = LogManager.getLogManager().getLogger("");

        // TODO change these 2 levels to configure all loggers on the project.
        rootLogger.setLevel(Level.WARNING);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(Level.WARNING);
        }

        try {
            folder = args[0]; //This is if the folder name comes from command line
            //It is used when running the jar
        } catch (ArrayIndexOutOfBoundsException e) { //If running from Idea
            folder = "Instances/small_instances"; //This is the chosen folder

            //Since running in Idea is usually for debug the Info level is set
            rootLogger = LogManager.getLogManager().getLogger("");
            rootLogger.setLevel(Level.INFO);
            for (Handler h : rootLogger.getHandlers()) {
                h.setLevel(Level.INFO);
            }
        }
        logger.log(Level.INFO, "Using " + cores + " cores");
        Experiment exp = new Experiment();

        //Run all the algorithms you need, they will be executed automatically
        exp.exampleRunConfiguration1(folder);
        exp.exampleRunConfiguration2(folder);
    }


    private void exampleRunConfiguration1(String folder){
        //We give a name to this experiment, that would be saved on "Example_algorithm.csv"
        String experimentName = "Example algorithm";

        //We create an Algorithm with a certain implementation, How?, it's up to you ;)
        Algorithm algorithm = new exampleAlgorithm();
        algorithm.setId("Example algorithm1");

        executeAlgorithm(folder,algorithm,experimentName);
    }

    private void exampleRunConfiguration2(String folder){
        String experimentName = "Definitive experiment";

        //We create an Algorithm with another experimentation
        Algorithm algorithm = new exampleAlgorithm();
        algorithm.setId("Definitive algorithm2");

        executeAlgorithm(folder,algorithm,experimentName);
    }

    /**
     * Run an experiment that involves one algorithm
     * @param folder The name of the folder that contains the instances
     * @param algorithm The algorithm to execute
     * @param experimentName The name of the experiment and CSV
     */
    private void executeAlgorithm(String folder, Algorithm algorithm, String experimentName) {
        List<Algorithm> dummyList = new LinkedList<>();
        dummyList.add(algorithm);
        executeAlgorithm(folder,dummyList,experimentName);
    }

    /**
     * Run an experiment that involves multiple algorithms
     * @param folder The name of the folder that contains the instances
     * @param algorithmList The algorithms to execute
     * @param experimentName The name of the experiment and CSV
     */
    private void executeAlgorithm(String folder, List<Algorithm> algorithmList, String experimentName) {
        String[] files = getFiles(folder);


        //The headers of the CSV
        List<String> headersOfCSV = new LinkedList<>();
        headersOfCSV.add("Algorithm ID");
        headersOfCSV.add("Filename");
        headersOfCSV.add("O.F.");
        headersOfCSV.add("T. CPU (s)");
        DataOutput dataOutput = new DataOutput(experimentName, headersOfCSV);

        for (String file : files) {
            for (Algorithm algorithm : algorithmList) {
                ExperimentAlgorithmRunner experimentAlgorithmRunnerGreedyRCL = new ExperimentAlgorithmRunner(file, algorithm, dataOutput);
                ex.submit(experimentAlgorithmRunnerGreedyRCL);
            }
        }

        ex.shutdown();
        try {
            if (!ex.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS)) {
                ex.shutdownNow();
            }
        } catch (InterruptedException e) {
            ex.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static String[] getFiles(String path) {
        File d = new File(path);
        return d.list();
    }
}