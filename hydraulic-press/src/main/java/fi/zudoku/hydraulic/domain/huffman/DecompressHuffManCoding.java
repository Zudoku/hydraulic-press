package fi.zudoku.hydraulic.domain.huffman;

import fi.zudoku.hydraulic.domain.Operation;
import fi.zudoku.hydraulic.domain.huffman.data.HuffmanTree;

public class DecompressHuffManCoding implements Operation {

    @Override
    public byte[] execute(byte[] input) {
        // read huffman tree
        HuffmanTree tree = readHuffmanTreeFromInput(input);
        
        // go through input, and transform the compressed data to uncompressed using huffman tree
        return null;
    }

    
    public static HuffmanTree readHuffmanTreeFromInput(byte[] input) {
        return null;
    }
   
}

