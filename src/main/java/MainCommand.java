import picocli.CommandLine;

//You probably dont need to modify this class
/*
 * This class is the main command of the program
 * It is used to parse the command line arguments
 * Depending on the command, it will call the corresponding class
 * You can call directly the class you want to execute, but this way you can have a better control of the program
 *
 * If you want to get rid of all this, just use Experiment class and change the pom.xml file to run the Experiment class
 */
@CommandLine.Command(subcommands = {Irace.class, Experiment.class})
public class MainCommand implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MainCommand()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        // This method can be empty or contain common logic
    }
}
