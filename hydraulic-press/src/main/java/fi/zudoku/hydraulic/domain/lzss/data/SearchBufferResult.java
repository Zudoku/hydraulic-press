package fi.zudoku.hydraulic.domain.lzss.data;

public class SearchBufferResult {

    private final boolean foundMatch;
    private final int index;
    private final int length;

    /**
     * The result of the search buffer search operation.
     * @param foundMatch if a match was found
     * @param index delta of where the match is
     * @param length how many bytes matched
     */
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

    /**
     * If a match was found.
     * @return true or false
     */
    public boolean foundMatch() {
        return foundMatch;
    }
}
