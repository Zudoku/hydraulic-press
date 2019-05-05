package fi.zudoku.hydraulic.domain.lzss.data;

public class LZSearchBuffer {
    
    private final byte[] buffer;
    private int bufferIndex = 0;

    public LZSearchBuffer(int bufferSize) {
        this.buffer = new byte[bufferSize];
    }
    
    public void add(byte b) {
        if (bufferIndex >= buffer.length) {
            buffer[calculateBufferIndex(bufferIndex)] = b;
        } else {
            buffer[bufferIndex] = b;
        }
        bufferIndex++;
    }
    
    public SearchBufferResult findBestMatch(byte[] toMatch) {
        SearchBufferResult bestMatch = new SearchBufferResult(false, 0, 0);
        
        for (int bufferIterator = 0; bufferIterator < buffer.length; bufferIterator++) {
            // max match length = min (current buffer size - i, toMatch length)
            int maxMatchLength = Math.min(Math.min(buffer.length - bufferIterator, bufferIndex), toMatch.length - 1);
            int startIndex = bufferIndex + bufferIterator;
            SearchBufferResult foundMatch = tryToMatch(toMatch, startIndex, maxMatchLength);
            if (foundMatch.getLength() >= bestMatch.getLength()){
                bestMatch = foundMatch;
            }
        }
        
        return bestMatch;
    }
    // startindex = index of buffer to start from
    // maxlength = maximum length for match
    private SearchBufferResult tryToMatch(byte[] toMatch, int startIndex, int maxLength) {
        int length = 0;
        while(length < maxLength) {
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

    private boolean bufferSizeIsBigEnoughForMatch(int startIndex, int length) {
        return startIndex + length < bufferIndex;
    }

    private int calculateBufferIndex(int realIndex) {
        return realIndex % buffer.length;
    }

    private boolean bufferResultMatchesFound(byte[] toMatch, int startIndex, int length) {
        byte matched = toMatch[length];
        byte inBuffer = buffer[calculateBufferIndex(startIndex + length)];
        
        return matched == inBuffer;
    }
}
