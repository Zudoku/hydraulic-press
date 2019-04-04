package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.domain.Operations;
import fi.zudoku.hydraulic.util.ArgumentBuilder;
import fi.zudoku.hydraulic.util.HydraulicPressArguments;
import java.nio.charset.StandardCharsets;

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
        byte[] result2 = decompresshydraulicPress.run(new HydraulicPressArguments(Operations.DECOMPRESS_HUFFMAN_CODING, result));
        
        
        System.out.println("RESULTS");
        System.out.println("original: " + arguments.getData().length + "\n" + new String(arguments.getData(), StandardCharsets.UTF_8));
        System.out.println("----");
        System.out.println("compressed: " + result.length + "\n" + new String(result, StandardCharsets.UTF_8));
        System.out.println("----");
        System.out.println("uncompressed:" + result2.length + "\n" + new String(result2, StandardCharsets.UTF_8));
        
    }
}


