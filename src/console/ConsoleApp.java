package console;

import core.DataProcessor;
import models.InputArgs;
import java.io.*;
import java.util.*;

public class ConsoleApp {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("CONSOLE APPLICATION - VARIANT 1");
        System.out.println("Move negative numbers to front");
        System.out.println("========================================");
        System.out.println();

        InputArgs inputArgs = new InputArgs(args);

        if (!inputArgs.isValid()) {
            System.out.println("ERROR: Input file not specified!");
            System.out.println();
            System.out.println("USAGE: java ConsoleApp -i <input_file> [-o <output_file>]");
            System.out.println("EXAMPLE: java ConsoleApp -i test/input01.txt -o output/result.txt");
            return;
        }

        try {
            // Read file
            System.out.println("Input file: " + inputArgs.getInputFile());
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(inputArgs.getInputFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            System.out.println("Loaded " + lines.size() + " lines");
            System.out.println();

            // Process data
            System.out.println("Processing data...");
            DataProcessor processor = new DataProcessor();
            String[] result = processor.processPipeline(lines.toArray(new String[0]));

            // Display result
            System.out.println("RESULT:");
            for (String r : result) {
                System.out.println(r);
            }

            // Save to file if specified
            if (inputArgs.hasOutputFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(inputArgs.getOutputFile()));
                for (String r : result) {
                    writer.write(r);
                    writer.newLine();
                }
                writer.close();
                System.out.println();
                System.out.println("Saved to: " + inputArgs.getOutputFile());
            }

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}