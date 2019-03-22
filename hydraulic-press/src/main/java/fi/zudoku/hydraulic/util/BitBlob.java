package fi.zudoku.hydraulic.util;

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
    
    public void appendOne() {
        numOfBits++;
    }
    
    public void appendZero() {
        numOfBits++;
    }
    
    public BitBlob copy() {
        return new BitBlob(numOfBits, data);
    }
    
}
