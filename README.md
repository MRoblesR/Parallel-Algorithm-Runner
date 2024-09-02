# Parallel Algorithm Runner

The **Parallel Algorithm Runner** simplifies running and managing parallel instances of algorithms. Whether you're conducting experiments on a remote Virtual Machine or need to execute your code using one thread per instance, this template has got you covered.

## Features

1. **Parallel Execution**:
   - Given a class implementing the `Algorithm` interface, this template automatically handles parallel execution of instances.
   - No need to worry about managing threads or concurrency – focus on your algorithm's logic.

2. **Structured Experiment Definitions**:
   - Define experiments with ease. Each experiment includes:
     - Algorithm parameters
     - Experiment name
   - Keep your experiments organized and reproducible.
   
3. **CSV Export**:
   - Automatically export your results to CSV for further analysis.
   
4. **SSH Deployment to Virtual Machines**:
   - When you use the command "just" it just:
     - Compile your code into a JAR.
     - Upload it to a virtual machine via SSH.
     - Execute the JAR remotely.

5. **Results Retrieval**:
   - Simply type "just download" to download the results in CSV format and all the other logs or files generated.
   - No manual file transfers – it's all automated.
     
6. **Ready for Irace**:
   - Check the folder with the required files to run Irace.
   - Setup script for Irace included!
    
7. **Hackable**:
   - Change anything as you wish!
    
## Prerequisites

Before getting started, ensure that you have the following software installed:

1. **Java Development Kit (JDK)**:
   - You'll need JDK 8 or later to compile and run your Java code.
   - Download and install it from Oracle or OpenJDK.

2. **SSH Client**:
   - To deploy your JAR to a virtual machine via SSH, make sure you have an SSH client installed.
   - If you're on Windows, consider using PuTTY.
   - Both commands ssh and scp are needed.
   - You must also have a private-public key pair for these commands to be automatic and not request the password (Putting a passphrase on the key would also require prompts).

3. **"just" Software**:
   - Install the "just" software, which simplifies running automatic algorithms.
   - Follow the installation guide [https://github.com/casey/just](https://github.com/casey/just).
4. **Git bash (If you are on Windows)**:
    - To work with "just" it is better to run it from the Git Bash console on Windows since it is a sh console.
    - It works on any sh console, the exceptions are cmd and PowerShell.

### Checklist
-[ ] Java Development Kit (JDK):
  -[ ] Run `java --version` in the console and ensure it returns a version higher than 8.
-[ ] SSH Client:
  -[ ] Run `ssh -V` in the console to check if SSH is installed and verify the version.
  -[ ] Run `scp -V` in the console to check if SCP is installed and verify the version.
  -[ ] Ensure you have a private-public key pair set up by running `ssh-keygen -t rsa -b 4096 -C "your_email@example.com"` if not already done.
  -[ ] Test automatic SSH login by running `ssh your_username@your_host` and ensure it does not prompt for a password.
-[ ] “just” Software:
  -[ ] Run `just --version` in the console to verify that “just” is installed and check the version.
  -[ ] Ensure that running `just` in the console does not produce any errors.
-[ ] Git Bash (If you are on Windows):
  -[ ] Open Git Bash and run `git --version` to verify that Git Bash is installed and check the version.
  -[ ] Ensure that “just” commands work in Git Bash by running a simple just command and verifying it executes correctly.
  
## Usage

1. **Installation**:
   - Clone this repository.
   - Import on Intellij Idea.
   - Set up your Java environment.


2. **Check the TODO**:
   - All you need to set up is to check and solve all the TODO comments (They are not that many ;) ).
   - Take a quick look at the classes if you want to know what is happening (Optional).


4. **Define Your Algorithm**:
   - Implement the `Algorithm` interface.
   - Add your algorithm's logic.
   - Use any file structure you want.


5. **Experiment Configuration**:
   - Create experiment definitions in a structured manner as an Experiment method.
   - Specify algorithm parameters and experiment names.


3. **Configure the parameters**:
    - Configure the following parameters for your virtual machine on the _vm.env_ file:
        - **User**: The username for SSH access to your VM.
        - **Address**: The IP address or hostname of your VM.
        - **Remote Path**: The directory path on the VM where you'll upload your JAR.
        - **Private Key Path**: The path to your private SSH key for authentication.


6. **Compile, upload, run**:
   - Type the command `just run INSTANCES` on the project folder replacing INSTANCES with the name of the folder where your instances are to automatically:
     - Compile your code into a JAR.
     - Use SSH to upload the JAR to your virtual machine with the current timestamp as the folder name.
     - Run the jar on the instances indicated on the parameters.


8. **Retrieve Results**:
   - Type `just download` to download the results and the logs into the Results local folder.


9. **Check the results and repeat from step 4**

## Directory Structure
- `/Instances`: Contains the instances.
- `/Irace`: Contains the files needed to run Irace.
- `/Results`: Contains the results of previous executions (automatically generated).
- `/src`: Contains your Java source code.

## How to run the JAR

You can run the jar by using commands in the console. There are two ways to run the jar:
- *Experiment mode*: You can run your experiments by using the command `java -jar your_jar.jar experiment` which runs the Experiment class.
- *Irace mode*: You can run Irace by using the command `java -jar your_jar.jar irace` which runs the Irace class.

Each of them have their own parameters that you can set up in the `Experiment` and `Irace` classes. In case you need to give other parameters to the jar, you can easily add them to the command.


## FAQ
### Q: How do I run locally?
A: Use idea to run the Experiment class. Instead of passing the arguments through CLI you can set them up in the `Experiment` class.

### Q: I don't want any concurrency. Can I run the instances sequentially?
A: Yes, you can use the command `-s` to run sequentially. For example, `java -jar your_jar.jar experiment -s`.

### Q: How do I change the number of threads?
A: You can change the number of threads by changing the numberOfCores and the percentOfCores variables in the `Experiment` class and in the command. 

## Contributing

Contributions are welcome! Please open an issue.

## License

This project is licensed under the [MIT License](LICENSE).

---

You can customize this template further to match your project's specifics. Happy coding! 🚀
