package models;

public class InputArgs {
    private String inputFile;
    private String outputFile;

    public InputArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i") && i + 1 < args.length) {
                inputFile = args[i + 1];
                i++;
            } else if (args[i].equals("-o") && i + 1 < args.length) {
                outputFile = args[i + 1];
                i++;
            }
        }
    }

    public String getInputFile() { return inputFile; }
    public String getOutputFile() { return outputFile; }
    public boolean hasOutputFile() { return outputFile != null && !outputFile.isEmpty(); }
    public boolean isValid() { return inputFile != null && !inputFile.isEmpty(); }
}