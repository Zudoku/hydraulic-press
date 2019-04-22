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
            System.out.println("Failed to parse your arguments.");
            ArgumentBuilder.printHelp();
            //return;
            // for easy development, default to these arguments
            arguments = ArgumentBuilder.parseArguments(new String[] {"testdata3.tst", "0"});
        }
        
        System.out.println("Input length (B): " + arguments.getData().length);
        System.out.println("----");
       
        HydraulicPressInstance hydraulicPress = new HydraulicPressInstance();
        long beforeExecutionTimeStamp = System.currentTimeMillis();
        byte[] result = hydraulicPress.run(arguments);
        long afterExecutionTimeStamp = System.currentTimeMillis();
        
        System.out.println("Output length (B): " + result.length);
        System.out.println("----");
        
        System.out.println("Time took (ms): " + (afterExecutionTimeStamp - beforeExecutionTimeStamp));
        System.out.println("----");
    }
}


