package fi.zudoku.hydraulic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {
    
    public static byte[] loadFileToByteArray(String filepath) throws SecurityException, IOException {
        File fileToLoad = new File(filepath);
        byte[] result = new byte[(int) fileToLoad.length()];
        FileInputStream fileInputStream = new FileInputStream(fileToLoad);
        fileInputStream.read(result);
        fileInputStream.close();
        
        return result;
    }
    
}
