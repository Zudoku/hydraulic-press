package fi.zudoku.hydraulic.domain.lzss.data;

public class SearchBufferResult {

    private final boolean foundMatch;
    private final int index;
    private final int length;

    public SearchBufferResult(boolean foundMatch, int index, int length) {
        this.foundMatch = foundMatch;
        this.index = index;
        this.length = length;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    public boolean foundMatch() {
        return foundMatch;
    }
}
