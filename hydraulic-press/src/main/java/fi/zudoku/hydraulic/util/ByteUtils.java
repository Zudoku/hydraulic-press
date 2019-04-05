/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.zudoku.hydraulic.util;

/**
 * Created Apr 3, 2019
 * @author arska
 */
public class ByteUtils {

    public static int intFromByteArray(byte[] bytes, int i) {
        return bytes[i] << 24 | (bytes[i + 1] & 0xFF) << 16 | (bytes[i + 2] & 0xFF) << 8 | (bytes[i + 3] & 0xFF);
    }
    
    public static byte[] growByteArrayWithOne(byte[] bytes) {
        byte[] newData = new byte[bytes.length + 1];
        // IS THIS ALLOWED?
        System.arraycopy(bytes, 0, newData, 0, bytes.length);
        return newData;
    }
    
    public static byte[] putIntegerIntoByteArray(byte[] bytes, int number, int index) {
        bytes[index] = (byte) (number >> 24);
        bytes[index + 1] = (byte) (number >> 16);
        bytes[index + 2] = (byte) (number >> 8);
        bytes[index + 3] = (byte) (number );
        return bytes;
    }
}
