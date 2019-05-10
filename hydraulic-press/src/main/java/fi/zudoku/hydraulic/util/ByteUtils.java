package fi.zudoku.hydraulic.util;

public class ByteUtils {

    /**
     * Parses int from the byte array.
     * Reads 4 bytes starting from i.
     * Might throw exception if array length is less than i + 3.
     * @param bytes array to parse it from.
     * @param i index where to start.
     * @return parsed int.
     */
    public static int intFromByteArray(byte[] bytes, int i) {
        return bytes[i] << 24 | (bytes[i + 1] & 0xFF) << 16 | (bytes[i + 2] & 0xFF) << 8 | (bytes[i + 3] & 0xFF);
    }
    
    /**
     * Increases the length of byte array by 1 
     * and copies the old data to the beginning of that new array.
     * @param bytes array to increment.
     * @return incremented byte array.
     */
    public static byte[] growByteArrayWithOne(byte[] bytes) {
        byte[] newData = new byte[bytes.length + 1];
        arrayCopy(bytes, 0, newData, 0, bytes.length);
        return newData;
    }
    
    /**
     * Calculates the n:th bit in the given byte.
     * n = 0 -> right most bit
     * n = 7 -> left most bit
     * @param n number of bit 
     * @param input byte to calculate bits from
     * @return 1 or 0
     */
    public static int getNthBitFromByte(int n, byte input) {
        int a = input & 0xFF;
        a >>= n;
        a <<= 7;
        byte temporary = (byte) (a); 
        
        int result = temporary & 0xFF;
        
        result >>= 7;
        
        return result;
    }

    /**
     * Stores integer into byte array and returns it.
     * Might throw exception if bytes length is less than i + 3.
     * @param bytes array to store int to.
     * @param number int to store.
     * @param index what array index to store it.
     * @return result of the method.
     */
    public static byte[] putIntegerIntoByteArray(byte[] bytes, int number, int index) {
        bytes[index] = (byte) (number >> 24);
        bytes[index + 1] = (byte) (number >> 16);
        bytes[index + 2] = (byte) (number >> 8);
        bytes[index + 3] = (byte) (number);
        return bytes;
    }
    
    /**
     * Copies the contents of one array into another
     * Since this can be done easily with for loops, 
     * I am going to assume that this utility function provided by java platform is ok to use.
     * @param from array which to copy from
     * @param fromIndex index of where to start copying from
     * @param to array which to copy to
     * @param toIndex index of where to start copying to
     * @param length of how many elements to copy
     */
    public static void arrayCopy(byte[] from, int fromIndex, byte[] to, int toIndex, int length) {
        if (length != 0) {
            try {
                System.arraycopy(from, fromIndex, to, toIndex, length);
            } catch (Exception e) {
                System.out.println("Tried to perform arrayCopy with bad parameters. This might cause data to be corrupted.");
            }
        }
    }
}
