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
        return new SearchBufferResult(false, 0, 0);
    }

    private int calculateBufferIndex(int realIndex) {
        return realIndex % bufferSize;
    }
}
