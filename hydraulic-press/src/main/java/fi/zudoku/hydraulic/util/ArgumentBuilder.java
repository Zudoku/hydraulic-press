package fi.zudoku.hydraulic.util;

import fi.zudoku.hydraulic.domain.Operations;
import java.io.IOException;

public class ArgumentBuilder {
    
    /**
     * This parses the String[] arguments for the program, to a structure that the HydraulicPressInstance understands.
     * 1. = filename of the input file 
     * 2. = operation
     * @param args java command line arguments
     * @return the given arguments as HydraulicPressArguments data structure
     */
    public static HydraulicPressArguments parseArguments(String[] args) {
        if (args.length < 2) {
            return null;
        }
        try {
            String filePathToLoad = args[0];
            Operations operation = Operations.values()[Integer.parseInt(args[1])];
            byte[] testdata = FileUtils.loadFileToByteArray(filePathToLoad);
            
            return new HydraulicPressArguments(operation, testdata);
        } catch (SecurityException | IOException ex) {
            System.out.println("File " + args[0] + " could not be loaded.");
        } catch (NumberFormatException ex) {
            System.out.println("Second argument is invalid.");
        }
        
        return null;
    }
    
    public static void printHelp() {
        System.out.println("Usage: java -jar hydraulic-press.jar [FILENAME] [OPERATION]");
        System.out.println("");
        System.out.println("[FILENAME] = relative filepath to the input file.");
        System.out.println("[OPERATION] = operation code");
        System.out.println("1. = COMPRESS_HUFFMAN_CODING");
        System.out.println("2. = DECOMPRESS_HUFFMAN_CODING");
        System.out.println("");
    }

}
