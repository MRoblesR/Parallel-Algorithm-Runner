import Algorithms.Algorithm;
import Algorithms.exampleAlgorithm;
import io.DataOutput;
import picocli.CommandLine;

import java.io.File;
import java.util.ArrayList;
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
@CommandLine.Command(name = "experiment", mixinStandardHelpOptions = true, version = "1.0",
        description = "Run the experiment with the given configuration")
public class Experiment implements Runnable {

    @CommandLine.Option(names = {"-s", "--sequentialRun"}, description = "Indicates whether to use concurrency or not")
    public static boolean sequentialRun = false;

    @CommandLine.Option(names = {"-n", "--numberOfCores"}, description = "Number of cores used if run in parallel")
    public static int numberOfCores = 100;

    @CommandLine.Option(names = {"-p", "--percentOfCores"}, description = "Percentage of cores available used if run in parallel")
    public static int percentOfCores = 20;

    @CommandLine.Option(names = {"-i", "--instancesFolderName"}, description = "The name of the folder containing the instances for this experiment")
    public static String instancesFolderName = "Instances";

    @CommandLine.Option(names = {"-l", "--loggingLevel"}, description = "The logger level")
    public static String loggingLevel = "INFO";

    @CommandLine.Option(names = {"-r", "--randomSeed"}, description = "The random seed")
    public static int randomSeed = 42;

    static Logger logger = Logger.getLogger("Experiment");
    ExecutorService ex;

    public static void main(String[] args) {
        CommandLine.run(new Experiment(), args);
    }

    @Override
    public void run() {
        int cores = getMaxNumberOfCores();
        ex = Executors.newFixedThreadPool(cores);
        logger.log(Level.INFO, "Using " + cores + " cores");
        startLoggingLevel();

        exampleRunConfiguration1(instancesFolderName);
        exampleRunConfiguration2(instancesFolderName);
    }


    private void exampleRunConfiguration1(String folder) {
        //We give a name to this experiment, that would be saved on "Example_algorithm.csv"
        String experimentName = "Example algorithm";

        //We create an Algorithm with a certain implementation, How?, it's up to you ;)
        Algorithm algorithm = new exampleAlgorithm();
        algorithm.setId("Example algorithm1");

        executeAlgorithm(folder, algorithm, experimentName);
    }

    private void exampleRunConfiguration2(String folder) {
        String experimentName = "Definitive experiment";

        //We create an Algorithm with another experimentation
        Algorithm algorithm = new exampleAlgorithm();
        algorithm.setId("Definitive algorithm2");

        executeAlgorithm(folder, algorithm, experimentName);
    }

    /**
     * Run an experiment that involves one algorithm
     *
     * @param folder         The name of the folder that contains the instances
     * @param algorithm      The algorithm to execute
     * @param experimentName The name of the experiment and CSV
     */
    private void executeAlgorithm(String folder, Algorithm algorithm, String experimentName) {
        List<Algorithm> dummyList = new LinkedList<>();
        dummyList.add(algorithm);
        executeAlgorithm(folder, dummyList, experimentName);
    }

    /**
     * Run an experiment that involves multiple algorithms
     *
     * @param folder         The name of the folder that contains the instances
     * @param algorithmList  The algorithms to execute
     * @param experimentName The name of the experiment and CSV
     */
    private void executeAlgorithm(String folder, List<Algorithm> algorithmList, String experimentName) {
        List<String> files = getFiles(folder);


        //The headers of the CSV
        List<String> headersOfCSV = new LinkedList<>();
        headersOfCSV.add("Algorithm ID");
        headersOfCSV.add("FolderPath");
        headersOfCSV.add("Filename");
        headersOfCSV.add("O.F.");
        headersOfCSV.add("T. CPU (s)");
        DataOutput dataOutput = new DataOutput(experimentName, headersOfCSV);

        for (String file : files) {
            for (Algorithm algorithm : algorithmList) {
                ExperimentAlgorithmRunner experimentAlgorithmRunnerGreedyRCL = new ExperimentAlgorithmRunner(file, algorithm, dataOutput);
                if (sequentialRun) {
                    experimentAlgorithmRunnerGreedyRCL.run();
                } else {
                    ex.submit(experimentAlgorithmRunnerGreedyRCL);
                }
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

    //You probably don't need to modify this function
    private static List<String> getFiles(String folderPath) {
        File folder = new File(folderPath);

        List<String> filePaths = listFiles(folder);
        return filePaths;
    }

    private static List<String> listFiles(File folder) {
        List<String> filePaths = new ArrayList<>();
        if (folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                if (file.isDirectory()) {
                    filePaths.addAll(listFiles(file)); // Recursively add files in subfolders
                } else {
                    filePaths.add(file.getAbsolutePath());
                }
            }
        }
        return filePaths;
    }

    public int getMaxNumberOfCores() {
        if (sequentialRun) {
            return 1;
        } else {
            return Math.min(numberOfCores, percentOfCores * Runtime.getRuntime().availableProcessors() / 100);
        }
    }

    public void startLoggingLevel() {
        Level level = Level.parse(loggingLevel.toUpperCase());
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.setLevel(level);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(level);
        }
    }
}
