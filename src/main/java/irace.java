import Algorithms.Algorithm;
import Algorithms.exampleAlgorithm;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * If you want to execute with irace YOU MUST CHANGE THE COMPILED CLASS TO THIS ONE
 */
public class irace {
    /**
     * This method reads the arguments irace passes to the jar
     * TODO You must implement the logic to read that arguments and pass them to the InstanceCase
     *
     * @param args The data received from command line
     */
    public static void main(String[] args) {
        Logger rootLogger = LogManager.getLogManager().getLogger("");

        rootLogger.setLevel(Level.WARNING);
        for (Handler h : rootLogger.getHandlers()) {
            h.setLevel(Level.SEVERE);
        }

        String filename = args[0];


        //This is a example or reading command line parameters, use or modify as needed
        /*
        for (int i = 1; i < args.length; i++) {
            switch (args[i]) {
                case "--const" -> paramsConstructivo.setMethod(Integer.parseInt(args[i + 1]));
                case "--ls" -> {
                    int ls = Integer.parseInt(args[i + 1]);
                    if (ls == 1) {
                        confParamsLSList.add(confParamsInsert);
                    }
                    if (ls == 2) {
                        confParamsLSList.add(confParamsSwap);
                    }
                    if (ls == 3) {
                        confParamsLSList.add(confParamsInsert);
                        confParamsLSList.add(confParamsSwap);
                    }
                    if (ls == 4) {
                        confParamsLSList.add(confParamsSwap);
                        confParamsLSList.add(confParamsInsert);
                    }
                }
                case "--alpha" -> paramsConstructivo.setAlpha(Float.parseFloat(args[i + 1]));
                case "--w1" -> paramsConstructivo.setW1(Float.parseFloat(args[i + 1]));
                case "--w2" -> paramsConstructivo.setW2(Float.parseFloat(args[i + 1]));
                case "--w3" -> paramsConstructivo.setW3(Float.parseFloat(args[i + 1]));
                case "--w4" -> paramsConstructivo.setW4(Float.parseFloat(args[i + 1]));
                case "--resolution" -> paramsConstructivo.setResolution(Float.parseFloat(args[i + 1]));
            }
        }
         */

        //This is your algorithm, you must take the before parameters and create an object
        Algorithm exampleAlgorithm = new exampleAlgorithm();

        IraceAlgorithmRunner experimentAlgorithmRunnerGreedyRCL = new IraceAlgorithmRunner(filename, exampleAlgorithm);
        experimentAlgorithmRunnerGreedyRCL.run();
    }
}