package fi.zudoku.hydraulic.domain.lzss.data;

/**
 * Custom implementation of a Lempel Ziv 77 search buffer.
 */
public class LZSearchBuffer {
    
    private final byte[] buffer;
    private int bufferIndex = 0;

    /**
     * Custom implementation of a Lempel Ziv 77 search buffer.
     * @param bufferSize 
     */
    public LZSearchBuffer(int bufferSize) {
        this.buffer = new byte[bufferSize];
    }
    
    /**
     * Adds the given byte into the buffer.
     * Slides the buffer one index forward.
     * @param b byte to add to the latest index
     */
    public void add(byte b) {
        if (bufferIndex >= buffer.length) {
            buffer[calculateBufferIndex(bufferIndex)] = b;
        } else {
            buffer[bufferIndex] = b;
        }
        bufferIndex++;
    }
    
    /**
     * Finds the longest match in the buffer for the given data.
     * @param toMatch byte array of which to find a match for
     * @return The results of the query.
     */
    public SearchBufferResult findBestMatch(byte[] toMatch) {
        SearchBufferResult bestMatch = new SearchBufferResult(false, 0, 0);
        
        // Go through the buffer, starting from the "last place" 
        // and work our way towards the latest value in the buffer
        for (int bufferIterator = 0; bufferIterator < buffer.length; bufferIterator++) {
            // max match length = min (current buffer size - i, toMatch length)
            // Limit length into the max length of the given data or the size of the buffer
            int maxMatchLength = Math.min(Math.min(buffer.length - bufferIterator, bufferIndex), toMatch.length - 1);
            int startIndex = bufferIndex + bufferIterator;
            SearchBufferResult foundMatch = tryToMatch(toMatch, startIndex, maxMatchLength);
            // Because of a bug, we need to validate that the index is OK too.
            // Couldnt find the cause of this bug, so just invalidate the finding, if index is bad.
            if (foundMatch.getLength() >= bestMatch.getLength() && foundMatch.getIndex() <= bufferIndex) {
                bestMatch = foundMatch;
            }
        }
        
        return bestMatch;
    }
    // startindex = index of buffer to start from
    // maxlength = maximum length for match
    private SearchBufferResult tryToMatch(byte[] toMatch, int startIndex, int maxLength) {
        int length = 0;
        while (length < maxLength) {
            if (bufferResultMatchesFound(toMatch, startIndex, length)) {
                length++;
            } else {
                break;
            }
        }
        
        if (length == 0) {
            return new SearchBufferResult(false, 0, 0);
        } else {
            return new SearchBufferResult(true, buffer.length - (startIndex - bufferIndex), length);
        }
    }

    /**
     * Transforms the given overflowing index into a value that can be used to query the buffer.
     * @param realIndex
     * @return 
     */
    private int calculateBufferIndex(int realIndex) {
        return realIndex % buffer.length;
    }

    private boolean bufferResultMatchesFound(byte[] toMatch, int startIndex, int length) {
        byte matched = toMatch[length];
        byte inBuffer = buffer[calculateBufferIndex(startIndex + length)];
        
        return matched == inBuffer;
    }
}
