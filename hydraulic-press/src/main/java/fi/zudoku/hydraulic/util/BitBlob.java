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

    /**
     * Initialize a new empty bitblob.
     */
    public BitBlob() {}
    
    /**
     * Initialize a new bitlob with data.
     * @param numOfBits number of bits in this blob.
     * @param data bits as byte array.
     */
    private BitBlob(int numOfBits, byte[] data) {
        this.numOfBits = numOfBits;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
    /**
     * Appends the bit '1' to the end of data.
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
     * Appends the bit '0' to the end of data.
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
    
    /**
     * Copies this bitblob values to a new one.
     * @return new instance of Bitblob with same values.
     */
    public BitBlob copy() {
        return new BitBlob(numOfBits, data);
    }
    
    /**
     * appends two biblobs after each other.
     * @param one first bitblob in sequence.
     * @param other second bitblob in sequence.
     * @return new Bitblob containing the two given bitblobs concatenated.
     */
    public BitBlob append(BitBlob one, BitBlob other) {
        
        int newDataLength =  calculateDataLength(one, other);
        
        byte[] newData = new byte[newDataLength];
        System.arraycopy(one.data, 0, newData, 0, one.data.length);
        
        int byteCutPoint = one.numOfBits % 8;
        
        if (byteCutPoint != 0) {
            newData[one.data.length - 1] = modifyCombinedByte(newData[one.data.length - 1], other.data[0], byteCutPoint);
        }
        
        for (int index = one.data.length; index < newDataLength; index++) {
            int unsignedFirst = other.data[index - one.data.length];
            
            if (unsignedFirst < 0) {
                unsignedFirst += 127;
            }
            unsignedFirst <<= 7 - byteCutPoint;
            int otherIndex = index - one.data.length + 1;
            byte second = (other.data.length > otherIndex) ? other.data[otherIndex] : 0;
            newData[index] = modifyCombinedByte((byte) unsignedFirst, second, byteCutPoint);
        }
        
        return new BitBlob(one.numOfBits + other.numOfBits, newData);
    }
    
    private int calculateDataLength(BitBlob one, BitBlob other) {
        int length  = one.data.length + other.data.length;
        int leftOverBits = (one.numOfBits % 8) + (other.numOfBits % 8);
        if (leftOverBits < 8) {
            length -= 1;
        } else if (leftOverBits > 8) {
            length += 1;
        }
        return length;
    }

    private byte modifyCombinedByte(byte one, byte other, int otherCutPoint) {
        int unsigned = one;
        if (unsigned < 0) {
            unsigned += 127;
        }
        
        int otherUnsigned = other;
        if (otherUnsigned < 0) {
            otherUnsigned += 127;
        }
        
        otherUnsigned >>>= otherCutPoint;
        
        int result = unsigned | otherUnsigned;
        return (byte) result;
    }

}
