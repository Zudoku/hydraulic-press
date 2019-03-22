package fi.zudoku.hydraulic.util;

/**
 * This class represents a chunk of bits that can be any length.
 * This is used to help dealing with bits in Java. 
 * For example if we want to append less than 8 bits at a time to some byte array.
 * @author arska
 */
public class BitBlob {
    private int numOfBits = 0;
    private byte[] data = new byte[1];

    public BitBlob() {
        
    }
    
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
        numOfBits++;
        //TODO
    }
    /**
     * Appends the bit '0' to the end of data
     */
    public void appendZero() {
        numOfBits++;
        //TODO
    }
    
    public BitBlob copy() {
        return new BitBlob(numOfBits, data);
    }
    
}
