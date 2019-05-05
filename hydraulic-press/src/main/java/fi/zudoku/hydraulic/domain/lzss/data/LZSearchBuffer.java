package fi.zudoku.hydraulic.domain.lzss.data;

public class LZSearchBuffer {
    
    private final int bufferSize;
    private final byte[] buffer;
    private int index = 0;

    public LZSearchBuffer(int bufferSize) {
        this.bufferSize = bufferSize;
        this.buffer = new byte[bufferSize];
    }
    
    public void add(byte b) {
        if (index >= bufferSize) {
            buffer[calculateBufferIndex(index)] = b;
        } else {
            buffer[index] = b;
        }
        index++;
    }
    
    public SearchBufferResult findBestMatch(byte[] searchBuffer) {
        SearchBufferResult bestMatch = new SearchBufferResult(false, 0, 0);
        
        // For now, use this inefficient impelementation of searchbuffer
        for (int i = 0; i < bufferSize; i++) {
            int maxMatchLength = Math.min(searchBuffer.length - 1, bufferSize - i);
            int startIndex = index + i;
            SearchBufferResult foundMatch = tryToMatch(searchBuffer, startIndex, maxMatchLength);
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
            if (bufferSizeIsBigEnoughForMatch(startIndex, length) && bufferResultMatchesFound(toMatch, startIndex, length)) {
                length++;
            } else {
                break;
            }
        }
        
        if (length == 0) {
            return new SearchBufferResult(false, 0, 0);
        } else {
            return new SearchBufferResult(true, bufferSize - calculateBufferIndex(startIndex) + 1, length);
        }
    }

    private boolean bufferSizeIsBigEnoughForMatch(int startIndex, int length) {
        return startIndex + length >= index;
    }

    private int calculateBufferIndex(int realIndex) {
        return realIndex % bufferSize;
    }

    private boolean bufferResultMatchesFound(byte[] toMatch, int startIndex, int length) {
        byte matched = toMatch[length];
        byte inBuffer = buffer[calculateBufferIndex(startIndex + length)];
        
        return matched == inBuffer;
    }
}
