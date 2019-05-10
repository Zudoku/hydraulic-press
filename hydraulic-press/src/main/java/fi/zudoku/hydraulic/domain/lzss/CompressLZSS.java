package fi.zudoku.hydraulic.domain.lzss;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.generic.LinkedList;
import fi.zudoku.hydraulic.domain.huffman.CompressHuffManCoding;
import fi.zudoku.hydraulic.domain.lzss.data.LZChunk;
import fi.zudoku.hydraulic.domain.lzss.data.LZSearchBuffer;
import fi.zudoku.hydraulic.domain.lzss.data.SearchBufferResult;
import fi.zudoku.hydraulic.util.BitBlob;
import fi.zudoku.hydraulic.util.ByteUtils;

public class CompressLZSS implements Operation {
    
    public static final int SEARCH_BUFFER_BITS = 7;
    public static final int LOOKAHEAD_BUFFER_BITS = 3;

    public static final int SEARCH_BUFFER_SIZE = (1 << SEARCH_BUFFER_BITS) - 1;
    public static final int LOOKAHEAD_BUFFER_SIZE = (1 << LOOKAHEAD_BUFFER_BITS) - 1;
    
    @Override
    public byte[] execute(byte[] input) {
        
        // Parse input into chunks
        LinkedList<LZChunk> chunks = parseInputIntoChunks(input);
        
        // chunks into bytes
        BitBlob blob = chunksToBitBlob(chunks);
        return blob.getData();
    }
    
    private BitBlob chunksToBitBlob(LinkedList<LZChunk> chunks) {
        BitBlob compressedData = new BitBlob();
        // To improve performance, we append smaller bitblobs together first
        // before appending the larger blobs together
        BitBlob compressedDataBuffer = new BitBlob();
        
        for (int i = 0; i < chunks.size(); i++) {
            if (i % CompressHuffManCoding.COMPRESSED_DATA_BUFFER_SIZE == 0) {
                compressedData = BitBlob.append(compressedData, compressedDataBuffer);
                compressedDataBuffer = new BitBlob();
            }
            BitBlob compressed = chunks.get(i).toBitBlob();
            compressedDataBuffer = BitBlob.append(compressedDataBuffer, compressed);
        }
        compressedData = BitBlob.append(compressedData, compressedDataBuffer);
        
        return compressedData;
    }
    
    
    
    private LinkedList<LZChunk> parseInputIntoChunks(byte[] input) {
        LinkedList<LZChunk> list = new LinkedList<>();
        
        LZSearchBuffer searchBuffer = new LZSearchBuffer(SEARCH_BUFFER_SIZE);
        byte[] lookaheadBuffer;
        
        // Loop through byte one at a time
        for (int i = 0; i < input.length; i++) {
            byte currentByte = input[i];
            
            // move lookahead buffer
            lookaheadBuffer = new byte[LOOKAHEAD_BUFFER_SIZE];
            ByteUtils.arrayCopy(input, i, lookaheadBuffer, 0, Math.min(LOOKAHEAD_BUFFER_SIZE, input.length - i));
            
            // Check if can be found in searchbuffer, if yes, encode that as chunk
            SearchBufferResult searchBufferResult = searchBuffer.findBestMatch(lookaheadBuffer);
            LZChunk chunk;
            if (searchBufferResult.foundMatch()) {
                boolean lastChunk = i + searchBufferResult.getLength() >= (input.length);
                byte nextByte = (lastChunk) ? 0 : input[i + searchBufferResult.getLength()];
                chunk = new LZChunk(searchBufferResult.getIndex(), searchBufferResult.getLength(), nextByte);
                
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
