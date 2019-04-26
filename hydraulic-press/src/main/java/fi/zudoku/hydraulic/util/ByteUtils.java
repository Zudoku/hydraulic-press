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
    
    public static void arrayCopy(byte[] from, int fromIndex, byte[] to, int toIndex, int length) {
        // Replace this with custom implementation if not allowed?
        System.arraycopy(from, fromIndex, to, toIndex, length);
    }
}
