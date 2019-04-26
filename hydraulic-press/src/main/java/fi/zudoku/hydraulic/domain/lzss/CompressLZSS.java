package fi.zudoku.hydraulic.domain.lzss;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.lzss.data.LZChunk;
import fi.zudoku.hydraulic.domain.lzss.data.LZSearchBuffer;
import fi.zudoku.hydraulic.domain.lzss.data.SearchBufferResult;
import fi.zudoku.hydraulic.util.ByteUtils;
import java.util.ArrayList;
import java.util.List;

public class CompressLZSS implements Operation {

    private final int searchBufferSize = 1 << 4;
    private final int lookaheadBufferSize = 1 << 3;
    
    @Override
    public byte[] execute(byte[] input) {
        
        // Parse input into chunks
        List<LZChunk> chunks = parseInputIntoChunks(input);
        
        // Headers
        
        // chunks into bytes
        
        return null;
    }
    
    
    
    private List<LZChunk> parseInputIntoChunks(byte[] input) {
        ArrayList<LZChunk> list = new ArrayList<>(); // Replace with custom implementation
        
        LZSearchBuffer searchBuffer = new LZSearchBuffer(searchBufferSize);
        byte[] lookaheadBuffer = new byte[lookaheadBufferSize];
        
        // Loop through byte one at a time
        for(int i = 0; i < input.length; i++) {
            byte currentByte = input[i];
            
            // move lookahead buffer
            ByteUtils.arrayCopy(input, i, lookaheadBuffer, 0, Math.min(lookaheadBufferSize, input.length - i));
            
            // Check if can be found in searchbuffer, if yes, encode that as chunk
            SearchBufferResult searchBufferResult = searchBuffer.findBestMatch(lookaheadBuffer);
            LZChunk chunk;
            if (searchBufferResult.foundMatch()) {
                boolean lastChunk = i + searchBufferResult.getLength() == input.length;
                byte nextByte = (lastChunk) ? 0 : input[i + searchBufferResult.getLength()];
                chunk = new LZChunk(searchBufferResult.getIndex(), searchBufferResult.getIndex(), nextByte);
                
                if (lastChunk) {
                    chunk.setNextByteInvalid(true);
                }
                list.add(chunk);
            } else {
                chunk = new LZChunk(0, 0, currentByte);
                list.add(chunk);
            }
            
            // move search buffer 
            searchBuffer.add(currentByte);
            if (!chunk.isNextByteInvalid()) {
                for (int o = 0; o < searchBufferResult.getLength(); o++) {
                    i++;
                    searchBuffer.add(input[i]);
                }
            }
        }
        return list;
    }

}
