import picocli.CommandLine;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@CommandLine.Command(name = "irace", mixinStandardHelpOptions = true, version = "irace 1.0",
        description = "Runs the irace algorithm with the specified parameters.")
public  class Irace implements Runnable {

    @CommandLine.Parameters(index = "0", description = "The filename to process.")
    private String filename;

    @CommandLine.Option(names = "--const", description = "Set the method for paramsConstructivo.")
    private Integer constMethod;

    @CommandLine.Option(names = "--ls", description = "Set the local search method.")
    private Integer ls;

    @CommandLine.Option(names = "--alpha", description = "Set the alpha value.")
    private Float alpha;

    @CommandLine.Option(names = "--w1", description = "Set the w1 value.")
    private Float w1;

    @CommandLine.Option(names = "--w2", description = "Set the w2 value.")
    private Float w2;

    @CommandLine.Option(names = "--w3", description = "Set the w3 value.")
    private Float w3;

    @CommandLine.Option(names = "--w4", description = "Set the w4 value.")
    private Float w4;

    @CommandLine.Option(names = "--resolution", description = "Set the resolution value.")
    private Float resolution;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Irace()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {


        // Process the command line arguments
            /*
            List<Object> confParamsLSList = new ArrayList<>();
            ParamsConstructivo paramsConstructivo = new ParamsConstructivo();

            if (constMethod != null) {
                paramsConstructivo.setMethod(constMethod);
            }

            if (ls != null) {
                switch (ls) {
                    case 1 -> confParamsLSList.add(new ConfParamsInsert());
                    case 2 -> confParamsLSList.add(new ConfParamsSwap());
                    case 3 -> {
                        confParamsLSList.add(new ConfParamsInsert());
                        confParamsLSList.add(new ConfParamsSwap());
                    }
                    case 4 -> {
                        confParamsLSList.add(new ConfParamsSwap());
                        confParamsLSList.add(new ConfParamsInsert());
                    }
                }
            }

            if (alpha != null) {
                paramsConstructivo.setAlpha(alpha);
            }
            if (w1 != null) {
                paramsConstructivo.setW1(w1);
            }
            if (w2 != null) {
                paramsConstructivo.setW2(w2);
            }
            if (w3 != null) {
                paramsConstructivo.setW3(w3);
            }
            if (w4 != null) {
                paramsConstructivo.setW4(w4);
            }
            if (resolution != null) {
                paramsConstructivo.setResolution(resolution);
            }

            // Your algorithm logic here
            Algorithm exampleAlgorithm = new ExampleAlgorithm();

            IraceAlgorithmRunner experimentAlgorithmRunnerGreedyRCL = new IraceAlgorithmRunner(filename, exampleAlgorithm);
            experimentAlgorithmRunnerGreedyRCL.run();
             */

        System.out.println("Hello World!");
    }
    public void startLoggingLevel() {
        Logger rootLogger = LogManager.getLogManager().getLogger("");
        rootLogger.setLevel(Level.SEVERE);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(Level.SEVERE);
        }
    }
}
