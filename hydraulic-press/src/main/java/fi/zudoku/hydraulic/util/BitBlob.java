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
        this.data = new byte[data.length];
        System.arraycopy(data, 0, this.data, 0, data.length);
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
        int byteIndex = (int) Math.floor((numOfBits - 1) / 8);
        byte modifiedByte = appendOneToByte(data[byteIndex], numOfBits % 8);
        data[byteIndex] = modifiedByte;
    }
    
    private byte appendOneToByte(byte input, int index) {
        int unsignedInput = input & 0xFF;
        int mask = 1;
        mask <<= 8 - index;
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
    public static BitBlob append(BitBlob one, BitBlob other) {
        
        int newDataLength =  calculateDataLength(one, other);
        //Copy the first part to the new array
        byte[] newData = new byte[newDataLength];
        System.arraycopy(one.data, 0, newData, 0, one.data.length);
        // Calculate, if last byte is missing bits
        int byteCutPoint = one.numOfBits % 8;
        // if so, add missing bits from the second array
        if (byteCutPoint != 0) {
            newData[one.data.length - 1] = modifyCombinedByte(newData[one.data.length - 1], (other.data.length > 0) ? other.data[0] : 0, byteCutPoint);
        }
        // Go through second array
        for (int index = one.data.length; index < newDataLength; index++) {
            if (byteCutPoint != 0) {
                // get the value that we are now modifying
                int unsignedFirst = other.data[index - one.data.length] & 0xFF;
                //Shift them with the next byte
                unsignedFirst <<= 7 - byteCutPoint;
                int otherIndex = index - one.data.length + 1;
                byte second = (other.data.length > otherIndex) ? other.data[otherIndex] : 0;
                newData[index] = modifyCombinedByte((byte) unsignedFirst, second, byteCutPoint);
            } else {
                newData[index] = other.data[index - one.data.length];
            }
        }
        
        return new BitBlob(one.numOfBits + other.numOfBits, newData);
    }
    
    private static int calculateDataLength(BitBlob one, BitBlob other) {
        int length  = one.data.length + other.data.length;
        int leftOverBits = (one.numOfBits % 8) + (other.numOfBits % 8);
        boolean bothBytesAreIncomplete = (one.numOfBits % 8 != 0) && (other.numOfBits % 8 != 0);
        if (leftOverBits <= 8 && bothBytesAreIncomplete) {
            length -= 1;
        }
        return length;
    }

    private static byte modifyCombinedByte(byte one, byte other, int otherCutPoint) {
        int unsigned = one & 0xFF;
        
        int otherUnsigned = other & 0xFF;
        
        otherUnsigned >>>= otherCutPoint;
        
        int result = unsigned | otherUnsigned;
        return (byte) result;
    }

}
