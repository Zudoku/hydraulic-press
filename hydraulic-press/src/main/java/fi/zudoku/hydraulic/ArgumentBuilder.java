package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.domain.Operations;
import fi.zudoku.hydraulic.util.FileUtils;
import fi.zudoku.hydraulic.util.HydraulicPressArguments;
import java.io.IOException;

public class ArgumentBuilder {
    
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
