package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.util.ArgumentBuilder;
import fi.zudoku.hydraulic.util.HydraulicPressArguments;

public class Main {
    public static void main(String[] args) {
        
        HydraulicPressArguments arguments = ArgumentBuilder.parseArguments(args);
        
        if (arguments == null) {
            System.out.println("Failed to parse your arguments, not doing anything");
            return;
        }
        
        HydraulicPressInstance hydraulicPress = new HydraulicPressInstance(arguments);
        hydraulicPress.run();
    }
}


