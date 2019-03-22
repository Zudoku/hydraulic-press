package fi.zudoku.hydraulic.util;

import fi.zudoku.hydraulic.domain.Operations;
import java.io.IOException;

public class ArgumentBuilder {
    
    /**
     * This parses the String[] arguments for the program, to a structure that the HydraulicPressInstance understands.
     * @param args java command line arguments
     * @return the given arguments as HydraulicPressArguments data structure
     */
    public static HydraulicPressArguments parseArguments(String[] args) {
        //TODO: actually read arguments instead of hardcoded testvalues
        String filePathToLoad = "testdata.tst";
        Operations operation = Operations.COMPRESS_HUFFMAN_CODING;
        
        try {
            byte[] testdata = FileUtils.loadFileToByteArray(filePathToLoad);
            
            return new HydraulicPressArguments(operation, testdata);
        } catch (SecurityException | IOException ex) {
            System.out.println("File " + filePathToLoad + " could not be loaded.");
        }
        
        return null;
    }

}
