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
}
