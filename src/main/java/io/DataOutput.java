package io;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class exports the given data to a CSV file
 * TODO modify as needed.
 */
public class DataOutput {
    private final String filename;
    private final Lock lock;
    static final Logger logger = Logger.getLogger("dataOutput");

    public DataOutput(String filename, List<String> headers) {
        this.filename = filename.replace(" ","_");
        this.lock = new ReentrantLock();

        File file = new File(filename + ".csv");
        lock.lock();
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            writer.writeNext(Arrays.copyOf(headers.toArray(), headers.size(), String[].class));

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "The file couldn't be created");
        } finally {
            lock.unlock();
        }
    }


    /**
     * Export a list of String to a CSV
     * @param data The data to export
     */
    public void exportCSV(List<String> data) {
        File file = new File(filename + ".csv");
        lock.lock();
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file, true);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            writer.writeNext(Arrays.copyOf(data.toArray(), data.size(), String[].class));

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "The file couldn't be opened");
        } finally {
            lock.unlock();
        }
    }
}
