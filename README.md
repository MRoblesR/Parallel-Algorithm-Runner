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
   - 
3. **CSV Export**:
   - Export your results to CSV for further analysis.
   - 
4. **SSH Deployment to Virtual Machines**:
   - When you use the command "just" it just:
     - Compile your code into a JAR.
     - Upload it to a virtual machine via SSH.
     - Execute the JAR remotely.

5. **Results Retrieval**:
   - Simply type "just download" to download the results in CSV format and all the other logs or files generated.
   - No manual file transfers – it's all automated.
     
6. **Ready for Irace**:
  - Check the folder with the required files to run irace.
  - Setup script for irace included!
    
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

## Usage

1. **Installation**:
   - Clone this repository.
   - Import on Intellij Idea.
   - Set up your Java environment.

2. **Check the TODO**:
  - All you need to set up is to check and solve all the TODO comments (They are not that many ;) ).
  - Take a quick look at the classes if you want to know what is happening (Optional).
  - 
3. **Configure the parameters**:
   - Configure the number of cores used in the Experiment class.
   - Configure the following parameters for your virtual machine on the _vm.env_ file:
     - **User**: The username for SSH access to your VM.
     - **Address**: The IP address or hostname of your VM.
     - **Remote Path**: The directory path on the VM where you'll upload your JAR.
     - **Private Key Path**: The path to your private SSH key for authentication.

4. **Define Your Algorithm**:
   - Implement the `Algorithm` interface.
   - Add your algorithm's logic.
   - Use any file structure you want.

5. **Experiment Configuration**:
   - Create experiment definitions in a structured manner as an Experiment method.
   - Specify algorithm parameters and experiment names.


6. **Compile, upload, run**:
   - type the command "just run INSTANCES" on the project folder replacing INSTANCES with the name of the folder where your instances are to automatically:
     - Compile your code into a JAR.
     - Use SSH to upload the JAR to your virtual machine with the current timestamp as the folder name.
     - Run the jar on the instances indicated on the parameters.

8. **Retrieve Results**:
   - Type "just download" to download the results and the logs into the Results local folder.

9. **Check the results and repeat from step 4**

## Directory Structure
- `/Instances`: Contains the instances.
- `/irace`: Contains the files needed to run irace.
- `/Results`: Contains the results of previous executions (automatically generated).
- `/src`: Contains your Java source code.

## Contributing

Contributions are welcome! Please open an issue.

## License

This project is licensed under the [MIT License](LICENSE).

---

You can customize this template further to match your project's specifics. Happy coding! 🚀
