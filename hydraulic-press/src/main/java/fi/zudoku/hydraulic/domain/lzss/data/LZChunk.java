package fi.zudoku.hydraulic.domain.lzss.data;

import fi.zudoku.hydraulic.domain.lzss.CompressLZSS;
import fi.zudoku.hydraulic.util.BitBlob;
import fi.zudoku.hydraulic.util.ByteUtils;

/**
 * LZ77 Triple
 * This represents the triples that are parsed from the input for LZ77 compression
 */
public class LZChunk {
    private final int index;
    private final int length;
    private final byte next;
    
    private boolean nextByteInvalid = false;

    /**
     * 
     * @param index delta into the last occurence of the chunk, 1 = the previous byte, 0 = no occurence found
     * @param length how many bytes to copy
     * @param next next byte 
     */
    public LZChunk(int index, int length, byte next) {
        this.index = index;
        this.length = length;
        this.next = next;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    public byte getNext() {
        return next;
    }

    public void setNextByteInvalid(boolean nextByteInvalid) {
        this.nextByteInvalid = nextByteInvalid;
    }

    public boolean isNextByteInvalid() {
        return nextByteInvalid;
    }
    /**
     * Encodes this into bits.
     * @return Bitblob that is encoded.
     */
    public BitBlob toBitBlob() {
        BitBlob result = new BitBlob();
        
        // index bits
        appendBitsToBitBlob(CompressLZSS.SEARCH_BUFFER_BITS, (byte) index, result);
        
        // length bits
        appendBitsToBitBlob(CompressLZSS.LOOKAHEAD_BUFFER_BITS, (byte) length, result);
        
        // next value
        if (!isNextByteInvalid()) {
            appendBitsToBitBlob(Byte.SIZE, next, result);
        }
        
        return result;
    }
    
    /**
     * Appends the byte into the given bitblob
     * @param bitLength how many bits from the byte should we append to the bitblob
     * @param input byte to append
     * @param blob BitBlob to append to
     */
    private void appendBitsToBitBlob(int bitLength, byte input, BitBlob blob) {
        for (int n = 0; n < bitLength; n++) {
            int currentBit = ByteUtils.getNthBitFromByte(bitLength - n - 1, input);
            if (currentBit == 0) {
                blob.appendZero();
            } else {
                blob.appendOne();
            }
        }
    }
}
