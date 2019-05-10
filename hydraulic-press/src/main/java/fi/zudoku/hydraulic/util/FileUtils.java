package fi.zudoku.hydraulic.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    
    /**
    * Loads file contents to a byte array.
    * @param filepath path of the file to read.
    * @return the file contents.
    * @throws SecurityException if we do not have permission to read that file.
    * @throws IOException if the file does not exist or we have some other problem.
    */
    public static byte[] loadFileToByteArray(String filepath) throws SecurityException, IOException {
        File fileToLoad = new File(filepath);
        byte[] result = new byte[(int) fileToLoad.length()];
        FileInputStream fileInputStream = new FileInputStream(fileToLoad);
        fileInputStream.read(result);
        fileInputStream.close();
        
        return result;
    }
    
    /**
     * Saves the contents of the byte array into a file.
     * @param data contents of the file
     * @param filepath where to save the file
     * @throws IOException if no permissions for the file or we have some other problem.
     */
    public static void saveByteArrayIntoFile(byte[] data, String filepath) throws IOException {
        File fileToLoad = new File(filepath);
        fileToLoad.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(fileToLoad);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }
    
}
