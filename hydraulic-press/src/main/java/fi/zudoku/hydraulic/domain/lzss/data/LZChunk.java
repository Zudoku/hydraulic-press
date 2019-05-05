package fi.zudoku.hydraulic.domain.lzss.data;

import fi.zudoku.hydraulic.domain.lzss.CompressLZSS;
import fi.zudoku.hydraulic.util.BitBlob;
import fi.zudoku.hydraulic.util.ByteUtils;

public class LZChunk {
    private final int index;
    private final int length;
    private final byte next;
    
    private boolean nextByteInvalid = false;

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
