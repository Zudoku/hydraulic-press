package fi.zudoku.hydraulic.domain.lzss.data;

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
}
