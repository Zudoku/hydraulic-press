package fi.zudoku.hydraulic.domain.lzss;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.generic.LinkedList;
import fi.zudoku.hydraulic.domain.lzss.data.LZChunk;

import fi.zudoku.hydraulic.util.BitBlob;
import fi.zudoku.hydraulic.util.ByteUtils;

public class DecompressLZSS implements Operation {
    
    enum DecodingState {
        Index, Length, Next;
    }
    
    class ImpartialDecodedChunk {
        int index;
        int length;
    }

    @Override
    public byte[] execute(byte[] input) {
        LinkedList<LZChunk> chunks = new LinkedList<>();
        
        DecodingState currentState = DecodingState.Index;
        ImpartialDecodedChunk currentChunk = new ImpartialDecodedChunk();
        
        BitBlob currentBits = new BitBlob();
        for (int index = 0; index < input.length; index++) {
            byte byteToHandle = input[index];
            
            for (byte bit = 0; bit < 8; bit++) {
                // Go through input one bit at a time
                // and parse values
                int currentBitFromByte = ByteUtils.getNthBitFromByte(7 - bit, byteToHandle);
                if (currentBitFromByte == 0) {
                    currentBits.appendZero();
                } else {
                    currentBits.appendOne();
                }
                // check if the state is finished
                switch (currentState) {
                    case Index:
                        if (currentBits.getNumOfBits() == CompressLZSS.SEARCH_BUFFER_BITS) {
                            int shiftAmount = (8 - CompressLZSS.SEARCH_BUFFER_BITS);
                            int currentChunkIndex = currentBits.getData()[0] & 0xFF;
                            currentChunkIndex >>= shiftAmount;
                            
                            currentChunk.index = currentChunkIndex;
                            currentBits = new BitBlob();
                            currentState = DecodingState.Length;
                        }
                        break;
                        
                    case Length:
                        if (currentBits.getNumOfBits() == CompressLZSS.LOOKAHEAD_BUFFER_BITS) {
                            int shiftAmount = (8 - CompressLZSS.LOOKAHEAD_BUFFER_BITS);
                            int currentChunkLength = currentBits.getData()[0] & 0xFF;
                            currentChunkLength >>= shiftAmount;
                            
                            currentChunk.length = currentChunkLength;
                            currentBits = new BitBlob();
                            currentState = DecodingState.Next;
                        }
                        break;
                            
                    case Next:
                        if (currentBits.getNumOfBits() == Byte.SIZE) {
                            byte nextByte = currentBits.getData()[0];
                            LZChunk parsedChunk = new LZChunk(currentChunk.index, currentChunk.length, nextByte);
                            chunks.add(parsedChunk);
                            currentBits = new BitBlob();
                            currentState = DecodingState.Index;
                            currentChunk = new ImpartialDecodedChunk();
                        }
                        break;
                }
            }
        }
        // If the last chunk is a partial chunk with no "next" byte, take that into consideration
        if (currentChunk.index != 0 && currentChunk.length != 0) {
            LZChunk partialChunk = new LZChunk(currentChunk.index, currentChunk.length, (byte) 0);
            partialChunk.setNextByteInvalid(true);
            chunks.add(partialChunk);
        }
        
        return chunksToBytes(chunks);
    }
    
    private static byte[] chunksToBytes(LinkedList<LZChunk> chunks) {
        byte[] result = new byte[0];
        // Go trought chunks and decode them one by one, into byte array
        for (int i = 0; i < chunks.size(); i++) {
            LZChunk chunk = chunks.get(i);
            int growAmount = chunk.getLength() + (chunk.isNextByteInvalid() ? 0 : 1);
            
            byte[] newResult = new byte[result.length + growAmount];
            ByteUtils.arrayCopy(result, 0, newResult, 0, result.length);
            ByteUtils.arrayCopy(result, result.length - chunk.getIndex(), newResult, result.length, chunk.getLength());
            
            if (!chunk.isNextByteInvalid()) {
                newResult[newResult.length - 1] = chunk.getNext();
            }
            result = newResult;
        }
        
        return result;
    }

}
