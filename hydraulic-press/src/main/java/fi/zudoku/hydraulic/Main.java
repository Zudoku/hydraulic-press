package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.domain.Operations;
import fi.zudoku.hydraulic.util.ArgumentBuilder;
import fi.zudoku.hydraulic.util.HydraulicPressArguments;

public class Main {
    /**
     * This is the main method of the whole program.
     * @param args see *ArgumentBuilder.parseArguments*
     */
    public static void main(String[] args) {
        
        HydraulicPressArguments arguments = ArgumentBuilder.parseArguments(args);
        
        if (arguments == null) {
            System.out.println("Failed to parse your arguments, not doing anything");
            return;
        }
        
        HydraulicPressInstance hydraulicPress = new HydraulicPressInstance();
        byte[] result = hydraulicPress.run(arguments);
        
        HydraulicPressInstance decompresshydraulicPress = new HydraulicPressInstance();
        decompresshydraulicPress.run(new HydraulicPressArguments(Operations.DECOMPRESS_HUFFMAN_CODING, result));
    }
}


