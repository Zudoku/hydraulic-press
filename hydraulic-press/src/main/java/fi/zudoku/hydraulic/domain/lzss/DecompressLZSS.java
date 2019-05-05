package fi.zudoku.hydraulic.domain.lzss;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.lzss.data.LZChunk;

import fi.zudoku.hydraulic.util.BitBlob;
import fi.zudoku.hydraulic.util.ByteUtils;
import java.util.ArrayList;
import java.util.List;

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
        List<LZChunk> chunks = new ArrayList<>();
        
        DecodingState currentState = DecodingState.Index;
        ImpartialDecodedChunk currentChunk = new ImpartialDecodedChunk();
        
        BitBlob currentBits = new BitBlob();
        for (int index = 0; index < input.length; index++) {
            byte byteToHandle = input[index];
            
            for (byte bit = 0; bit < 8; bit++) {
                int currentBitFromByte = ByteUtils.getNthBitFromInt(bit, byteToHandle);
                if (currentBitFromByte == 0) {
                    currentBits.appendZero();
                } else {
                    currentBits.appendOne();
                }
                
                switch (currentState) {
                    case Index:
                        if (currentBits.getNumOfBits() == CompressLZSS.LOOKAHEAD_BUFFER_BITS) {
                            int shiftAmount = (Byte.SIZE - CompressLZSS.LOOKAHEAD_BUFFER_BITS);
                            int currentChunkIndex = currentBits.getData()[0] >> shiftAmount;
                            currentChunk.index = currentChunkIndex;
                            currentBits = new BitBlob();
                            currentState = DecodingState.Length;
                        }
                        break;
                        
                    case Length:
                        if (currentBits.getNumOfBits() == CompressLZSS.SEARCH_BUFFER_BITS) {
                            int shiftAmount = (Byte.SIZE - CompressLZSS.SEARCH_BUFFER_BITS);
                            int currentChunkLength = currentBits.getData()[0] >> shiftAmount;
                            currentChunk.length = currentChunkLength;
                            currentBits = new BitBlob();
                            currentState = DecodingState.Next;
                        }
                        break;
                            
                    case Next:
                        if (currentBits.getNumOfBits() == Byte.SIZE) {
                            byte nextByte = currentBits.getData()[0];
                            LZChunk parsedChunk = new LZChunk(currentChunk.index, currentChunk.index, nextByte);
                            chunks.add(parsedChunk);
                            currentBits = new BitBlob();
                            currentState = DecodingState.Index;
                        }
                        break;
                }
            }
        }
        return new byte[0];
    }

}
