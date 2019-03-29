package fi.zudoku.hydraulic.util;

/**
 * This class represents a chunk of bits that can be any length.
 * This is used to help dealing with bits in Java. 
 * For example if we want to append less than 8 bits at a time to some byte array.
 * @author arska
 */
public class BitBlob {
    private int numOfBits = 0;
    private byte[] data = new byte[0];

    public BitBlob() {}
    
    private BitBlob(int numOfBits, byte[] data) {
        this.numOfBits = numOfBits;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
    /**
     * Appends the bit '1' to the end of data
     */
    public void appendOne() {
        checkIfNeedToExtendData();
        numOfBits++;
        int byteIndex = (numOfBits - (numOfBits % 8)) / 8;
        byte modifiedByte = appendOneToByte(data[byteIndex], numOfBits % 8);
        data[byteIndex] = modifiedByte;
    }
    
    private byte appendOneToByte(byte input, int index) {
        int unsignedInput = input;
        if (unsignedInput < 0) {
            unsignedInput += 127;
        }
        int mask = 1;
        mask <<= 7 - index;
        int result = (unsignedInput | mask);
        return (byte) result;
    }
    /**
     * Appends the bit '0' to the end of data
     */
    public void appendZero() {
        checkIfNeedToExtendData();
        numOfBits++;
    }
    
    private void checkIfNeedToExtendData() {
        int bitsFreeInData = (data.length * Byte.SIZE) - numOfBits;
       
        if (bitsFreeInData == 0) {
            byte[] newData = new byte[data.length + 1];
            // IS THIS ALLOWED?
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }
    
    public BitBlob copy() {
        return new BitBlob(numOfBits, data);
    }
    
}
