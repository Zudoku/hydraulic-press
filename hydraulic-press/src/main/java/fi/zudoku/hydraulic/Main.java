package fi.zudoku.hydraulic;

import fi.zudoku.hydraulic.util.FileUtils;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        
        try {
            byte[] testdata = FileUtils.loadFileToByteArray("testdata.tst");
            System.out.println(Arrays.toString(testdata));
        } catch (SecurityException | IOException ex) {
            System.out.println("not found");
        }
    }
}


